import csv
import os
from collections import defaultdict

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
INPUT_CSV = os.path.join(SCRIPT_DIR, "InputCONF/CIM2.0/legacyParserNames.csv")
OUTPUT_PATH = os.path.join(SCRIPT_DIR, "OutputMD/")
PARSERS_LEGACY_PATH = os.path.join(OUTPUT_PATH, "ParsersLegacy/")
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


def create_letter_pages(sorted_parsers):
    groups = defaultdict(list)
    for old_name, new_name in sorted_parsers.items():
        groups[old_name[0].lower()].append((old_name, new_name))

    os.makedirs(PARSERS_LEGACY_PATH, exist_ok=True)
    for letter, entries in groups.items():
        rows = [[old, new] for old, new in entries]
        content = md_table(["Old Parser Name", "New-Scale Parser Name"], rows)
        with open(os.path.join(PARSERS_LEGACY_PATH, f"{letter}_parsers.md"), "w", encoding="utf-8") as f:
            f.write(content)


def create_matrix_page():
    letters = [chr(c) for c in range(ord("A"), ord("Z") + 1)]
    row1 = [md_link(l, f"ParsersLegacy/{l.lower()}_parsers.md") for l in letters[:12]] + ["", ""]
    row2 = [md_link(l, f"ParsersLegacy/{l.lower()}_parsers.md") for l in letters[12:]]

    link_to = md_link("Parser Naming Conventions", NAMING_CONVENTIONS_URL)
    heading = md_heading1(" Matrix of Old vs. New-Scale Parser Names")
    description = (
        " Parser names follow a standardized set of conventions that ensure consistency across Exabeam products. "
        f"For more information, see {link_to}. If you've been using Exabeam products prior to the introduction of "
        "this parser-naming convention, consult the alphabetic tables below to find the new name for existing parsers."
    )
    table = md_table(row1, [row2], alignments=["center"] * 14)

    os.makedirs(OUTPUT_PATH, exist_ok=True)
    with open(os.path.join(OUTPUT_PATH, "ParserNamesMatrix.md"), "w", encoding="utf-8") as f:
        f.write(f"{heading}\n\n{description}\n\n{table}\n")


def create_parser_names_matrix():
    sorted_parsers = load_legacy_parser_names()
    create_letter_pages(sorted_parsers)
    create_matrix_page()


if __name__ == "__main__":
    create_parser_names_matrix()