import json
import os
import zipfile
from xml.sax.saxutils import escape
from collections import defaultdict

# --- Constants for file paths ---
INPUT_FILE = os.path.join('input', 'vendor-product.json')
CATEGORIES_FILE = os.path.join('input', 'categories.json')
OUTPUT_DIR = os.path.join('output', 'vendorslist')
PALIGO_ZIP_FILENAME = "VendorsProductsPaligoImport.zip"
PALIGO_XML_FILENAME = "vendors_products.xml"


def load_categories(filename):
    """Loads product categories from the given JSON file."""
    try:
        with open(filename, 'r', encoding='utf-8') as f:
            data = json.load(f)
    except FileNotFoundError:
        print(f"Warning: Categories file '{filename}' not found.")
        return {}

    product_to_category = {}
    categories_data = data.get("Product Category", {}).get("category", {})
    for category, products in categories_data.items():
        for product in products:
            product_to_category[product.lower()] = category.title()
    return product_to_category


def create_vendor_product_files(vendor_data, output_dir, categories):
    """
    Creates a grouped Markdown file and DocBook 5 XML zip.
    Products are displayed as bulleted lists.
    The 'Other' category is always placed last.
    """
    if not isinstance(vendor_data, dict):
        raise TypeError("Input vendor data must be a dictionary.")

    # --- 1. Restructure and Group Data ---
    grouped_data = defaultdict(lambda: defaultdict(list))
    for vendor_name, data in vendor_data.items():
        for product_info in data.get('products', []):
            p_name = product_info.get('product_name')
            if not p_name: continue
            cat = categories.get(p_name.lower(), 'Other')
            grouped_data[vendor_name][cat].append(p_name)

    # --- 2. Initialize Contents ---
    md_rows = ""
    xml_table_rows = ""

    # --- 3. Process Data Grouped by Vendor ---
    for vendor_name in sorted(grouped_data.keys()):
        cats_in_vendor = grouped_data[vendor_name]

        # Sort categories alphabetically, 'Other' last
        all_categories = list(cats_in_vendor.keys())
        if 'Other' in all_categories:
            all_categories.remove('Other')
            all_categories.sort()
            all_categories.append('Other')
        else:
            all_categories.sort()

        num_cats = len(all_categories)

        for i, category in enumerate(all_categories):
            products = sorted(cats_in_vendor[category])

            products_md_list = "".join([f"<li>{p}</li>" for p in products])
            products_md = f"<ul>{products_md_list}</ul>" if products_md_list else ""

            # --- Markdown Logic (HTML table for rowspan support) ---
            if i == 0:
                rowspan = f' rowspan="{num_cats}"' if num_cats > 1 else ""
                valign = ' style="vertical-align:top"' if num_cats > 1 else ""
                md_rows += f'<tr><td{rowspan}{valign}>{vendor_name}</td><td>{products_md}</td><td>{category}</td></tr>\n'
            else:
                md_rows += f'<tr><td>{products_md}</td><td>{category}</td></tr>\n'

            # --- DocBook XML Logic (morerows for rowspan) ---
            xml_table_rows += '                <row>\n'
            if i == 0:
                morerows = f' morerows="{num_cats - 1}"' if num_cats > 1 else ""
                valign = ' valign="top"' if num_cats > 1 else ""
                xml_table_rows += f'                    <entry{morerows}{valign}>{escape(vendor_name)}</entry>\n'
            xml_table_rows += '                    <entry>\n'
            if products:
                xml_table_rows += '                        <itemizedlist>\n'
                for product in products:
                    xml_table_rows += f'                            <listitem><para>{escape(product)}</para></listitem>\n'
                xml_table_rows += '                        </itemizedlist>\n'
            xml_table_rows += '                    </entry>\n'
            xml_table_rows += f'                    <entry>{escape(category)}</entry>\n'
            xml_table_rows += '                </row>\n'

    md_content = "# Supported Vendors and Products\n\n"
    md_content += "Exabeam supports data sources from the following vendors and products for log ingestion.\n\n"
    md_content += "> **Note:** This list is updated monthly, so there is a possibility that a newly supported product hasn't been added yet. Contact your account team to request new product additions.\n\n"
    md_content += "<table>\n<thead><tr><th>Vendor</th><th>Products</th><th>Category</th></tr></thead>\n<tbody>\n"
    md_content += md_rows
    md_content += "</tbody>\n</table>\n"

    # --- 4. Write Markdown File ---
    md_filename = os.path.join(output_dir, "vendors_products.md")
    with open(md_filename, 'w', encoding='utf-8') as f:
        f.write(md_content)
    print(f"Created Markdown: {md_filename}")

    # --- 5. Write DocBook 5 XML and ZIP ---
    docbook_xml_content = f"""<?xml version="1.0" encoding="UTF-8"?>
<article xmlns="http://docbook.org/ns/docbook" version="5.0">
    <info><title>Supported Vendors and Products</title></info>
    <section>
        <title>Supported Vendors and Products</title>
        <para>Exabeam supports data sources from the following vendors and products for log ingestion.</para>
        <note><para>This list is updated monthly, so there is a possibility that a newly supported product hasn't been added yet. Contact your account team to request new product additions.</para></note>
        <table frame="all">
            <title>List of Vendors and Their Products</title>
            <tgroup cols="3">
                <colspec colname="col1" colwidth="1*"/>
                <colspec colname="col2" colwidth="1.5*"/>
                <colspec colname="col3" colwidth="1.5*"/>
                <thead>
                    <row>
                        <entry>Vendor</entry>
                        <entry>Products</entry>
                        <entry>Category</entry>
                    </row>
                </thead>
                <tbody>
{xml_table_rows.rstrip()}
                </tbody>
            </tgroup>
        </table>
    </section>
</article>
"""
    temp_xml_path = os.path.join(output_dir, PALIGO_XML_FILENAME)
    with open(temp_xml_path, 'w', encoding='utf-8') as f:
        f.write(docbook_xml_content)

    zip_path = os.path.join(output_dir, PALIGO_ZIP_FILENAME)
    with zipfile.ZipFile(zip_path, 'w', zipfile.ZIP_DEFLATED) as zipf:
        zipf.write(temp_xml_path, arcname=PALIGO_XML_FILENAME)

    os.remove(temp_xml_path)
    print(f"Created Paligo ZIP (DocBook 5): {zip_path}")


if __name__ == "__main__":
    os.makedirs(OUTPUT_DIR, exist_ok=True)

    if os.path.exists(INPUT_FILE) and os.path.exists(CATEGORIES_FILE):
        with open(INPUT_FILE, 'r', encoding='utf-8') as f:
            v_data = json.load(f)
        prod_cats = load_categories(CATEGORIES_FILE)
        create_vendor_product_files(v_data, OUTPUT_DIR, prod_cats)
    else:
        print("Error: Required input files in 'input/' folder are missing.")
