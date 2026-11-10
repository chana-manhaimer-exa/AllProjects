#!/usr/bin/python3
import json
import requests
import shutil
import os
from collections import defaultdict
from lxml import etree as ET

def get_latest_file_from_github(owner, repo, file_path, branch, token):
    if not token:
        raise RuntimeError("Environment Var GITHUB_TOKEN does not exist. Search your Evernote to find it. intitle:github")

    url = f"https://api.github.com/repos/{owner}/{repo}/contents/{file_path}?ref={branch}"
    headers = {
        'Authorization': f'token {token}',
        'Accept': 'application/vnd.github.v3.raw'
    }

    response = requests.get(url, headers=headers)

    try:
        response.raise_for_status()  # Raise an exception for HTTP errors
        with open("rbac.json", 'w') as file:  # Save the file with a fixed name
            file.write(response.text)
        print("File downloaded successfully")
    except requests.exceptions.RequestException as e:
        print(f"Error fetching file: {e}")
        raise

# Function to capitalize the first letter of a string
def capitalize_first_letter(s):
    return s[0].upper() + s[1:]

# Function to compare JSON files and display differences
def compare_json(old_data, new_data):
    if old_data == new_data:
        print("No differences found between the old and new JSON files.")
        return
    
    print("Differences found:")
    old_set = set(json.dumps(old_data, sort_keys=True).splitlines())
    new_set = set(json.dumps(new_data, sort_keys=True).splitlines())

    added = new_set - old_set
    removed = old_set - new_set

    if added:
        print("Added:")
        for item in added:
            print(item)

    if removed:
        print("Removed:")
        for item in removed:
            print(item)

# Function to pretty print XML
def pretty_print_xml(elem):
    rough_string = ET.tostring(elem, pretty_print=True, encoding='unicode')
    return rough_string

############# MAIN SCRIPT #############

# JIRA server configuration
keys_file = '../../keys.json'
with open(keys_file, 'r') as file:
    keys = json.load(file)
owner = keys["github-rbac"]["owner"]
repo = keys["github-rbac"]["repo"]
token = keys["github-rbac"]["token"]
file_path = "src/main/resources/rbac.json"  # Replace with the path to the file you want to download
branch = "master"  # Replace with the branch name if different from 'main'

# Define the filename
filename = "rbac.json"
old_filename = "old_rbac.json"

# Copy the current rbac.json to old_rbac.json if it exists
if os.path.exists(filename):
    shutil.copyfile(filename, old_filename)

# Read the old JSON file if it exists
if os.path.exists(old_filename):
    with open(old_filename, 'r') as file:
        old_data = json.load(file)
else:
    old_data = None

# Download the JSON file from GitHub
try:
    get_latest_file_from_github(owner, repo, file_path, branch, token)
except requests.exceptions.RequestException as e:
    print(f"Error fetching file: {e}")

# Read the new JSON file
try:
    with open(filename, 'r') as file:
        new_data = json.load(file)
        # print("New JSON Data:")
        # print(json.dumps(new_data, indent=4))
except json.JSONDecodeError as e:
    print(f"Error parsing JSON: {e}")

# Compare old and new JSON data
if old_data is not None:
    compare_json(old_data, new_data)
else:
    print("No old JSON data to compare against.")

# Create a dictionary to organize permissions by category
permissions_by_category = defaultdict(list)

# Group permissions by category and filter by visibility
permissions = new_data.get("permissions", [])
for permission in permissions:
    if permission.get("visibility", "").lower() == "hidden":
        continue
    category = permission.get("category", "Other")
    permissions_by_category[category].append(permission)

# Create a dictionary to store roles' displayNames for each permission
roles_dict = defaultdict(list)

# Iterate through permissions and roles to populate the roles_dict
for permission in permissions:
    if permission.get("visibility", "").lower() == "hidden":
        continue
    permission_name = permission.get("name")
    for role in new_data.get("roles", []):
        if permission_name in role.get("permissions", []):
            roles_dict[permission_name].append(role.get("displayName"))

# Main processing and XML generation
for category, permissions in permissions_by_category.items():
    # Read the rbac-topics.json file
    try:
        with open("rbac-topics.json", 'r') as file:
            topics_data = json.load(file)
    except json.JSONDecodeError as e:
        print(f"Error parsing JSON: {e}")

    original_category = category  # Retain original case
    category_lower = category.lower()  # Convert category to lowercase for lookup
    resource_id = None

    for topic in topics_data.get("topics", []):
        if topic.get("category", "").lower() == category_lower:
            resource_id = topic.get("id")
            break

    nsmap = {
        None: "http://docbook.org/ns/docbook",
        'xinfo': "http://ns.expertinfo.se/cms/xmlns/1.0"
    }

    doc = ET.Element("section", nsmap=nsmap, version="5.0", xml_lang="en", **{"{http://ns.expertinfo.se/cms/xmlns/1.0}resource-id": str(resource_id)})

    title = ET.SubElement(doc, "title")
    title.text = f"{original_category} Permissions"  # Use original case for the title

    remark = ET.SubElement(doc, "remark", **{"{http://ns.expertinfo.se/cms/xmlns/1.0}translate": "no"})
    remark.text = "THIS TABLE IS GENERATED VIA SCRIPT. DO NOT MAKE EDITS DIRECTLY. Contact Charissa for details."

    informaltable = ET.SubElement(doc, "informaltable", width="100%")

    thead = ET.SubElement(informaltable, "thead")
    tr_head = ET.SubElement(thead, "tr")

    headers = ["Application", "Platform", "Permission", "Permission Description", "Access", "Roles"]

    for header in headers:
        th = ET.SubElement(tr_head, "th")
        para = ET.SubElement(th, "para")
        para.text = header

    tbody = ET.SubElement(informaltable, "tbody")

    permissions.sort(key=lambda permission: (permission.get("application", ""), permission.get("platform", "")))

    for permission in permissions:
        if permission.get("visibility", "").lower() == "hidden":
            continue
        tr = ET.SubElement(tbody, "tr")

        for header in headers:
            td = ET.SubElement(tr, "td")
            para = ET.SubElement(td, "para")

            if header == "Application":
                para.text = permission.get("application", "")
            elif header == "Platform":
                para.text = permission.get("platform", "")
            elif header == "Permission":
                para.text = permission.get("displayName", "")
            elif header == "Permission Description":
                para.text = permission.get("description", "")
            elif header == "Access":
                access_values = permission.get("access", [])
                if len(access_values) == 1:
                    para.text = capitalize_first_letter(access_values[0])
                elif len(access_values) > 1:
                    itemizedlist = ET.SubElement(para, "itemizedlist")
                    for access_value in access_values:
                        listitem = ET.SubElement(itemizedlist, "listitem")
                        para_access = ET.SubElement(listitem, "para")
                        para_access.text = capitalize_first_letter(access_value)

            elif header == "Roles":
                permission_name = permission.get("name")
                role_names = roles_dict.get(permission_name, [])
                if len(role_names) == 1:
                    para.text = role_names[0]
                elif len(role_names) > 1:
                    itemizedlist = ET.SubElement(para, "itemizedlist")
                    for role_name in role_names:
                        listitem = ET.SubElement(itemizedlist, "listitem")
                        para_role = ET.SubElement(listitem, "para")
                        para_role.text = role_name

    xml_content = pretty_print_xml(doc)
    with open(f"{category}.xml", "w", encoding="utf-8") as xml_file:
        xml_file.write(xml_content)

    print(f"Category: {category}, Permissions: {len(permissions)}")
