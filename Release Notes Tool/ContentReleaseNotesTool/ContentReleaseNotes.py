import json
import sys
from pathlib import Path
from collections import defaultdict
from zipfile import ZipFile, ZIP_DEFLATED
from xml.sax.saxutils import escape
from datetime import datetime

# === CONFIG ===
input_json = ""

input_json = "input/exa_content_diff_2026.7.1_2026.8.1.json"

# Check if a file path was provided when the script was run
if (len(input_json)==0 & len(sys.argv) < 2) :
    print("Error: Please provide the path to the input JSON file (input/exa_content_diff_2026.1.1_2026.2.1.json):")
    input("Press Enter to exit...") # Keeps the window open on error
    sys.exit(1) # Exit the script
if ( len(sys.argv) > 1) :
    input_json = sys.argv[1]

# Load JSON first to get current version
with open(input_json, "r", encoding="utf-8") as f:
    data = json.load(f)

current_ver = data.get("Release Version", "N/A")

# "Previous Version" is absent from some input files; derive it from the
# data entry keys by finding the version key that isn't current_ver.
prev_ver = data.get("Previous Version") or None
if not prev_ver:
    _known_keys = {"Type of Change", "Changed Field", "Vendor"}
    for _change in data.get("code_changes", []):
        for _entry in _change.get("Data", []):
            _ver_keys = [k for k in _entry if k not in _known_keys and k != current_ver]
            if _ver_keys:
                prev_ver = _ver_keys[0]
                break

        if prev_ver:
            break
if not prev_ver:
    prev_ver = "N/A"

# Folder names with version
md_root = Path(f"output/MD_RN_{current_ver}")
xml_root = Path(f"output/XML_RN_{current_ver}")
md_root.mkdir(parents=True, exist_ok=True)
xml_root.mkdir(parents=True, exist_ok=True)

def format_release_date(date_str: str) -> str:
    try:
        return datetime.strptime(date_str, "%Y-%m-%d").strftime("%d %b %Y")
    except Exception:
        return date_str

CSS = "body{font-family:sans-serif;margin:2em}h1,h2,h3{color:#2e7d32}ul{margin:.5em 0 1em 1.5em}table{border-collapse:collapse;width:100%;margin-top:.5em}th,td{border:1px solid #ddd;padding:6px 10px;text-align:left;vertical-align:top}th{background:#f5f5f5}tr:nth-child(even){background:#fafafa}details{margin-top:1.2em}details>summary{font-size:1.4em;font-weight:bold;color:#2e7d32;cursor:pointer;user-select:none;list-style:none;padding:.2em 0}details>summary::-webkit-details-marker{display:none}details>summary::before{content:\"\\25B6\\00A0\";font-size:.75em}details[open]>summary::before{content:\"\\25BC\\00A0\";font-size:.75em}.wb{max-width:400px;word-wrap:break-word;overflow-wrap:break-word}.vn{vertical-align:top;font-weight:bold}.dt{width:auto;table-layout:auto}"

def _html_page(title, body):
    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>{title}</title>
<style>{CSS}</style>
</head>
<body>
{body}
</body>
</html>"""

def _get_resource_type(change):
    return (change.get("Resource Type") or change.get("only code ", "") or "").strip() or "Unknown"

def _html_section_dict(heading, section_dict):
    """Render a dict-based section (e.g. Enhancements or Addressed Issues) as HTML."""
    parts = [f"<h2>{escape(heading)}</h2>"]
    for sub_key, items in section_dict.items():
        parts.append(f"<h3>{escape(sub_key)}</h3><ul>")
        for item in items:
            parts.append(f"<li>{escape(item)}</li>")
        parts.append("</ul>")
    return "\n".join(parts)

def _html_section_list(heading, items):
    """Render a flat list section (e.g. Announcements) as HTML."""
    parts = [f"<h2>{escape(heading)}</h2><ul>"]
    for item in items:
        parts.append(f"<li>{escape(item)}</li>")
    parts.append("</ul>")
    return "\n".join(parts)

def generate_enhancements_issues_html(data, current_ver):
    release_ver = data.get("Release Version") or current_ver
    release_date_raw = data.get("Release Date", "")
    release_date_pretty = format_release_date(release_date_raw) if release_date_raw else ""

    body_parts = [f"<h1>Content Package {escape(release_ver)}</h1>"]
    if release_date_pretty:
        body_parts.append(
            f"<p>These release notes contain information about content package "
            f"{escape(release_ver)}, released on {escape(release_date_pretty)}.</p>"
        )

    enhancements = data.get("Enhancements", [])
    if enhancements:
        body_parts.append(_html_section_list("Enhancements", enhancements))

    addressed = data.get("Addressed Issues", [])
    if addressed:
        body_parts.append(_html_section_list("Addressed Issues", addressed))

    announcements = data.get("Announcements", [])
    if announcements:
        body_parts.append(_html_section_list("Announcements", announcements))

    html = _html_page(
        f"Enhancements & Addressed Issues — {release_ver}",
        "\n".join(body_parts),
    )
    out_file = md_root / f"CodeChanges{release_ver}_Enhancements&AddressesIssues.html"
    out_file.write_text(html, encoding="utf-8")
    print("✅ Enhancements & Addressed Issues HTML saved to:", out_file)
    return out_file

def generate_resource_type_htmls(data, prev_ver, current_ver):
    release_ver = data.get("Release Version") or current_ver
    code_changes = data.get("code_changes", [])

    # Map display name → filename suffix (no spaces)
    resource_type_map = {
        "Parser": "Parser",
        "ParserTemplate": "ParserTemplate",
        "Event Builder": "EventBuilder",
        "Event Enricher": "EventEnricher",
    }

    resource_groups = defaultdict(list)
    for change in code_changes:
        rt = _get_resource_type(change)
        resource_groups[rt].append(change)

    generated = []
    for display_name, filename_suffix in resource_type_map.items():
        items = resource_groups.get(display_name, [])

        # Group entries by normalised Type of Change, sorted alphabetically by Resource Id within each group
        type_groups = defaultdict(list)
        for change in sorted(items, key=lambda x: x.get("Resource Id", "")):
            resource_id = change.get("Resource Id", "Unknown ID")
            for entry in change.get("Data", []):
                raw_type = str(entry.get("Type of Change", ""))
                normalised_type = raw_type.replace("_", " ").title()
                type_groups[normalised_type].append((resource_id, entry))

        def _clean(val):
            s = str(val)
            return "" if s.strip() == "N/A" else escape(s)

        def _vendor(resource_id):
            return resource_id.split("-")[0].capitalize()

        if type_groups:
            sections_html = []
            split_sections = {}  # group_type -> section html, for types that get their own file
            for group_type in sorted(type_groups):
                if group_type == "Removed Regex Field":
                    # Collapse to one row per parser: join all removed field names
                    seen = {}
                    for resource_id, entry in type_groups[group_type]:
                        vendor = entry.get("Vendor", "") if display_name == "Parser" else _vendor(resource_id)
                        field = entry.get("Changed Field", "")
                        if resource_id not in seen:
                            seen[resource_id] = (vendor, resource_id, [field], "", "")
                        else:
                            seen[resource_id][2].append(field)
                    rows_data = [
                        (vendor, rid, escape(", ".join(fields)), bef, aft)
                        for vendor, rid, fields, bef, aft in seen.values()
                    ]
                else:
                    rows_data = [
                        (
                            entry.get("Vendor", "") if display_name == "Parser" else _vendor(resource_id),
                            resource_id,
                            _clean(entry.get("Changed Field", "")),
                            _clean(entry.get(prev_ver, "")),
                            _clean(entry.get(current_ver, "")),
                        )
                        for resource_id, entry in type_groups[group_type]
                    ]

                show_vendor = display_name not in ("Event Enricher", "Event Builder")
                show_changed_field = display_name != "Event Enricher" and group_type not in ("Added Parser", "Changed Parsed Fields", "Removed Parser", "Added Event Builder", "Removed Event Builder", "Removed Enricher", "Added Parser Template", "Removed Parser Template")
                show_before = display_name != "Event Enricher" and group_type not in ("Added Parser", "Added Regex Field", "Removed Regex Field", "Added Event Builder", "Removed Event Builder", "Removed Enricher", "Added Parser Template", "Removed Parser Template")
                show_after = group_type not in ("Removed Parser", "Removed Regex Field", "Added Event Builder", "Removed Event Builder", "Removed Enricher", "Added Parser Template", "Removed Parser Template")

                def _td(content):
                    if len(content) > 50:
                        return f'<td class="wb">{content}'
                    return f"<td>{content}"

                def _rid_cell(rid, rspan=1):
                    esc = escape(rid)
                    rs = f' rowspan="{rspan}"' if rspan > 1 else ""
                    return f'<td{rs} class="wb">{esc}' if len(esc) > 50 else f'<td{rs}>{esc}'

                def _data_cells(cf, bef, aft):
                    cells = ""
                    if show_changed_field:
                        cells += _td(cf)
                    if show_before:
                        cells += _td(bef)
                    if show_after:
                        cells += _td(aft)
                    return cells

                def _row_cells(rid, cf, bef, aft):
                    return _rid_cell(rid) + _data_cells(cf, bef, aft)

                # For Regex sections with multiple fields per parser, group by parser within vendor
                group_by_parser = group_type in ("Added Regex Field", "Edit Regex Field")

                row_html = []
                i = 0
                while i < len(rows_data):
                    _, rid, cf, bef, aft = rows_data[i]
                    if show_vendor:
                        vendor = rows_data[i][0]
                        vspan = 1
                        while i + vspan < len(rows_data) and rows_data[i + vspan][0] == vendor:
                            vspan += 1
                        vrs = f' rowspan="{vspan}"' if vspan > 1 else ""
                        vendor_cell = f'<td{vrs} class="vn">{escape(vendor)}'
                        if group_by_parser:
                            j = 0
                            first_in_vendor = True
                            while j < vspan:
                                cur_rid = rows_data[i + j][1]
                                rspan = 1
                                while j + rspan < vspan and rows_data[i + j + rspan][1] == cur_rid:
                                    rspan += 1
                                rc = _rid_cell(cur_rid, rspan)
                                for k in range(rspan):
                                    _, _r, cf2, bef2, aft2 = rows_data[i + j + k]
                                    data = _data_cells(cf2, bef2, aft2)
                                    if first_in_vendor and k == 0:
                                        row_html.append(f"<tr>{vendor_cell}{rc}{data}")
                                        first_in_vendor = False
                                    elif k == 0:
                                        row_html.append(f"<tr>{rc}{data}")
                                    else:
                                        row_html.append(f"<tr>{data}")
                                j += rspan
                        else:
                            row_html.append(f"<tr>{vendor_cell}{_row_cells(rid, cf, bef, aft)}")
                            for j in range(1, vspan):
                                _, rid, cf, bef, aft = rows_data[i + j]
                                row_html.append(f"<tr>{_row_cells(rid, cf, bef, aft)}")
                        i += vspan
                    else:
                        row_html.append(f"<tr>{_row_cells(rid, cf, bef, aft)}")
                        i += 1

                header = ("<th>Vendor" if show_vendor else "")
                header += f"<th>{escape(display_name)}"
                if show_changed_field:
                    header += "<th>Changed Field"
                if show_before:
                    header += f"<th>Before ({escape(prev_ver)})"
                if show_after:
                    header += f"<th>After ({escape(current_ver)})"
                section = (
                    f"<details><summary>{escape(group_type)} ({len(rows_data)})</summary>"
                    f'<table class="dt"><tr>{header}</tr>{"".join(row_html)}</table></details>'
                )

                # All Parser Regex sections go to their own file
                if display_name == "Parser" and "Regex" in group_type:
                    split_sections[group_type] = section
                else:
                    sections_html.append(section)

            table = "\n".join(sections_html) if sections_html else "<p>No changes for this resource type.</p>"
        else:
            table = "<p>No changes for this resource type.</p>"
            split_sections = {}

        body = (
            f"<h1>Code Changes — {escape(display_name)} — Content Package {escape(release_ver)}</h1>"
            f"\n{table}"
        )
        html = _html_page(f"Code Changes {release_ver} — {display_name}", body)
        out_file = md_root / f"CodeChanges{release_ver}_{filename_suffix}.html"
        out_file.write_text(html, encoding="utf-8")
        print(f"✅ {display_name} HTML saved to:", out_file)
        generated.append(out_file)

        # Write combined Regex file for Parser
        if split_sections:
            regex_body = (
                f"<h1>Code Changes — {escape(display_name)} — Regex — Content Package {escape(release_ver)}</h1>\n"
                + "\n".join(split_sections.values())
            )
            regex_html = _html_page(f"Code Changes {release_ver} — {display_name} — Regex", regex_body)
            regex_file = md_root / f"CodeChanges{release_ver}_{filename_suffix}_Regex.html"
            regex_file.write_text(regex_html, encoding="utf-8")
            print(f"✅ {display_name} Regex HTML saved to:", regex_file)
            generated.append(regex_file)

    return generated

def generate_xml_release_notes(data, current_ver):
    xml_root = Path(f"output/XML_RN_{current_ver}")
    xml_root.mkdir(parents=True, exist_ok=True)

    release_ver = data.get("Release Version") or current_ver
    release_date_raw = data.get("Release Date", "")
    release_date_pretty = format_release_date(release_date_raw) if release_date_raw else ""

    enhancements = data.get("Enhancements", [])
    addressed = data.get("Addressed Issues", [])
    announcements = data.get("Announcements", [])

    lines = []
    lines.append('<?xml version="1.0" encoding="UTF-8"?>')
    lines.append('<article xmlns="http://docbook.org/ns/docbook" xmlns:xlink="http://www.w3.org/1999/xlink" version="5.0">')

    # SINGLE TITLE + DATE
    lines.append(f"<title>Content Package {escape(release_ver)}</title>")


    # SINGLE SECTION
    lines.append(f"<section><title>Content Package {escape(release_ver)}</title>")
    if release_date_pretty:
        lines.append(f"<para>These release notes contain information about content package {escape(release_ver)}, released on {escape(release_date_pretty)}.</para>")

    # Enhancements
    if enhancements:
        lines.append("<para><emphasis role='bold'>Enhancements</emphasis></para>")
        lines.append("<itemizedlist>")
        for item in enhancements:
            lines.append(f"<listitem><para>{escape(item)}</para></listitem>")
        lines.append("</itemizedlist>")

    # Addressed Issues
    if addressed:
        lines.append("<para><emphasis role='bold'>Addressed Issues</emphasis></para>")
        lines.append("<itemizedlist>")
        for item in addressed:
            lines.append(f"<listitem><para>{escape(item)}</para></listitem>")
        lines.append("</itemizedlist>")

    # Announcements
    if announcements:
        lines.append("<para><emphasis role='bold'>Announcements</emphasis></para>")
        lines.append("<itemizedlist>")
        for item in announcements:
            lines.append(f"<listitem><para>{escape(item)}</para></listitem>")
        lines.append("</itemizedlist>")

    lines.append("</section>")
    lines.append("</article>")

    xml_file = xml_root / f"release_notes_{release_ver}.xml"
    xml_file.write_text("\n".join(lines), encoding="utf-8")

    # ZIP for Paligo import
    zip_path = xml_root / f"release_notes_{release_ver}.zip"
    with ZipFile(zip_path, "w", ZIP_DEFLATED) as zf:
        zf.write(xml_file, arcname=xml_file.name)

    print("✅ XML saved to:", xml_file)
    print("✅ ZIP for Paligo import:", zip_path)
    return xml_file, zip_path

# === GENERATE FILES ===
generate_enhancements_issues_html(data, current_ver)
generate_resource_type_htmls(data, prev_ver, current_ver)
xml_file, zip_file = generate_xml_release_notes(data, current_ver)
