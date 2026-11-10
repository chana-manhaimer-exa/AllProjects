import csv
import os
import zipfile
from collections import defaultdict
from xml.sax.saxutils import escape

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
INPUT_CSV = os.path.join(SCRIPT_DIR, "Input/legacyParserNames.csv")
OUTPUT_PATH = os.path.join(SCRIPT_DIR, "output/Legacy")
PARSERS_LEGACY_PATH = os.path.join(OUTPUT_PATH, "ParsersLegacy/")
PALIGO_ZIP_FILENAME = "ParserNamesPaligoImport.zip"
PALIGO_XML_FILENAME = "ParserNamesMatrix.xml"
NAMING_CONVENTIONS_URL = (
    "https://docs.exabeam.com/en/content/all/exabeam-security-content-cim"
    "/exabeam-parsers/parser-naming-conventions.html"
)


def md_link(text, url):
    return f"[{text}]({url})"


def md_heading1(text):
    return f"{text}\n{'=' * len(text)}"


def md_table(headers, rows, alignments=None):
    col_count = len(headers)
    if alignments is None:
        alignments = ["left"] * col_count

    all_rows = [headers] + rows
    col_widths = [
        max(max(len(str(r[i])) for r in all_rows if i < len(r)), 3)
        for i in range(col_count)
    ]

    def fmt(text, width, align):
        text = str(text)
        if align == "center":
            return text.center(width)
        if align == "right":
            return text.rjust(width)
        return text.ljust(width)

    def sep(width, align):
        if align == "center":
            return ":" + "-" * (width - 2) + ":"
        if align == "right":
            return "-" * (width - 1) + ":"
        return "-" * width

    def fmt_row(row):
        padded = list(row) + [""] * (col_count - len(row))
        return "| " + " | ".join(fmt(padded[i], col_widths[i], alignments[i]) for i in range(col_count)) + " |"

    lines = [
        fmt_row(headers),
        "| " + " | ".join(sep(col_widths[i], alignments[i]) for i in range(col_count)) + " |",
    ]
    lines += [fmt_row(row) for row in rows]
    return "\n".join(lines)


def load_legacy_parser_names():
    parser_map = {}
    with open(INPUT_CSV, newline="", encoding="utf-8") as f:
        reader = csv.reader(f)
        next(reader)  # skip header row
        for row in reader:
            if len(row) >= 2:
                old_name = row[0].strip()
                new_name = row[1].strip()
                if old_name and old_name not in parser_map:
                    parser_map[old_name] = new_name
    return dict(sorted(parser_map.items()))


def build_groups(sorted_parsers):
    groups = defaultdict(list)
    for old_name, new_name in sorted_parsers.items():
        groups[old_name[0].lower()].append((old_name, new_name))
    return groups


# ---------------------------------------------------------------------------
# Markdown — single file
# ---------------------------------------------------------------------------

def create_matrix_page(existing_letters, groups):
    letters = [chr(c) for c in range(ord("A"), ord("Z") + 1)]
    col_count = 13  # A–M as header row, N–Z as data row

    def header_cell(l):
        if l.lower() in existing_letters:
            return md_link(l, f"#{l.lower()}")
        return l

    def data_cell(l):
        if l.lower() in existing_letters:
            return md_link(f"**{l}**", f"#{l.lower()}")
        return f"**{l}**"

    header_row = [header_cell(l) for l in letters[:col_count]]
    data_row = [data_cell(l) for l in letters[col_count:]]

    link_to = md_link("Parser Naming Conventions", NAMING_CONVENTIONS_URL)
    heading = md_heading1(" Legacy Parsers Map")
    description = (
        " Parser names follow a standardized set of conventions that ensure consistency across Exabeam products. "
        f"For more information, see {link_to}. If you've been using Exabeam products prior to the introduction of "
        "this parser-naming convention, consult the alphabetic tables below to find the new name for existing parsers."
    )
    nav_table = md_table(header_row, [data_row], alignments=["center"] * col_count)

    sections = []
    for l in letters:
        if l.lower() not in existing_letters:
            continue
        rows = [[old, new] for old, new in groups[l.lower()]]
        letter_table = md_table(["Old Parser Name", "New-Scale Parser Name"], rows)
        sections.append(f"## {l}\n\n{letter_table}")

    content = f"{heading}\n\n{description}\n\n{nav_table}\n\n" + "\n\n".join(sections) + "\n"

    os.makedirs(OUTPUT_PATH, exist_ok=True)
    with open(os.path.join(OUTPUT_PATH, "ParserNamesMatrix.md"), "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Created Markdown: {os.path.join(OUTPUT_PATH, 'ParserNamesMatrix.md')}")


# ---------------------------------------------------------------------------
# XML (DocBook 5) — single file, zipped
# ---------------------------------------------------------------------------

def build_xml(existing_letters, groups):
    letters = [chr(c) for c in range(ord("A"), ord("Z") + 1)]

    def nav_entry(l):
        if l.lower() in existing_letters:
            return f'                    <entry><link linkend="letter-{l.lower()}">{escape(l)}</link></entry>\n'
        return f"                    <entry>{escape(l)}</entry>\n"

    row1_entries = "".join(nav_entry(l) for l in letters[:12]) + "                    <entry/>\n" * 2
    row2_entries = "".join(nav_entry(l) for l in letters[12:])

    nav_table = f"""\
        <table frame="all">
            <title>Alphabetic Index</title>
            <tgroup cols="14">
                {"".join(f'<colspec colname="col{i}" colwidth="1*"/>' for i in range(1, 15))}
                <tbody>
                    <row>
{row1_entries}                    </row>
                    <row>
{row2_entries}                    </row>
                </tbody>
            </tgroup>
        </table>"""

    letter_sections = ""
    for l in letters:
        if l.lower() not in existing_letters:
            continue
        rows_xml = ""
        for old, new in groups[l.lower()]:
            rows_xml += (
                "                <row>\n"
                f"                    <entry>{escape(old)}</entry>\n"
                f"                    <entry>{escape(new)}</entry>\n"
                "                </row>\n"
            )
        letter_sections += f"""
        <section xml:id="letter-{l.lower()}">
            <title>Parser Names — {l.upper()}</title>
            <table frame="all">
                <title>Old vs. New-Scale Parser Names ({l.upper()})</title>
                <tgroup cols="2">
                    <colspec colname="col1" colwidth="1*"/>
                    <colspec colname="col2" colwidth="1*"/>
                    <thead>
                        <row>
                            <entry>Old Parser Name</entry>
                            <entry>New-Scale Parser Name</entry>
                        </row>
                    </thead>
                    <tbody>
{rows_xml.rstrip()}
                    </tbody>
                </tgroup>
            </table>
        </section>"""

    return f"""<?xml version="1.0" encoding="UTF-8"?>
<article xmlns="http://docbook.org/ns/docbook"
         xmlns:xlink="http://www.w3.org/1999/xlink"
         xmlns:xml="http://www.w3.org/XML/1998/namespace"
         version="5.0">
    <info><title>Legacy Parsers Map</title></info>
    <section>
        <title>Legacy Parsers Map</title>
        <para>
            Parser names follow a standardized set of conventions that ensure consistency
            across Exabeam products. For more information, see
            <link xlink:href="{escape(NAMING_CONVENTIONS_URL)}">Parser Naming Conventions</link>.
            If you've been using Exabeam products prior to the introduction of this
            parser-naming convention, consult the alphabetic tables below to find the
            new name for existing parsers.
        </para>
{nav_table}
{letter_sections}
    </section>
</article>
"""


def create_paligo_zip(existing_letters, groups):
    zip_path = os.path.join(OUTPUT_PATH, PALIGO_ZIP_FILENAME)
    with zipfile.ZipFile(zip_path, "w", zipfile.ZIP_DEFLATED) as zf:
        zf.writestr(PALIGO_XML_FILENAME, build_xml(existing_letters, groups))
    print(f"Created Paligo ZIP (DocBook 5): {zip_path}")


# ---------------------------------------------------------------------------
# Legacy per-letter .md files (kept for backwards compatibility)
# ---------------------------------------------------------------------------

def create_letter_pages(groups):
    os.makedirs(PARSERS_LEGACY_PATH, exist_ok=True)
    for letter, entries in groups.items():
        rows = [[old, new] for old, new in entries]
        content = md_table(["Old Parser Name", "New-Scale Parser Name"], rows)
        with open(os.path.join(PARSERS_LEGACY_PATH, f"{letter}_parsers.md"), "w", encoding="utf-8") as f:
            f.write(content)


# ---------------------------------------------------------------------------

def create_parser_names_matrix():
    sorted_parsers = load_legacy_parser_names()
    groups = build_groups(sorted_parsers)
    existing_letters = set(groups.keys())
    create_letter_pages(groups)
    create_matrix_page(existing_letters, groups)
    create_paligo_zip(existing_letters, groups)


if __name__ == "__main__":
    create_parser_names_matrix()
