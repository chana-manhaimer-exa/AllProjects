import os
import re
from bs4 import BeautifulSoup
from markdownify import markdownify as md

def clean_html(html_content):
    """Parses Confluence HTML and extracts only the relevant article content."""
    soup = BeautifulSoup(html_content, 'html.parser')
    main_content = soup.find(id='main-content') or soup.find(id='content') or soup.body
    if not main_content:
        return ""
    for element in main_content.find_all(['style', 'script', 'nav']):
        element.decompose()
    return str(main_content)

def convert_to_markdown(html_string):
    """Converts HTML string to GitBook-compatible Markdown."""
    markdown = md(html_string, heading_style="ATX", bullets="-")
    markdown = re.sub(r'\n{3,}', '\n\n', markdown)
    markdown = markdown.replace('\xa0', ' ')
    return markdown.strip()

def generate_summary(output_dir):
    """Creates the SUMMARY.md file for GitBook hierarchy."""
    summary_path = os.path.join(output_dir, 'SUMMARY.md')
    with open(summary_path, 'w', encoding='utf-8') as f:
        f.write('# Table of contents\n\n')
        for root, dirs, files in os.walk(output_dir):
            dirs.sort()
            files.sort()
            rel_path = os.path.relpath(root, output_dir)
            depth = 0 if rel_path == '.' else len(rel_path.split(os.sep))
            indent = '  ' * depth

            if depth > 0 and rel_path != '.':
                folder_name = os.path.basename(root).replace('-', ' ').replace('_', ' ').title()
                f.write(f"{indent}* {folder_name}\n")

            for file in files:
                if file.endswith('.md') and file not in ['SUMMARY.md', 'README.md']:
                    file_path = os.path.relpath(os.path.join(root, file), output_dir)
                    url_path = file_path.replace(os.sep, '/')
                    title = os.path.splitext(file)[0].replace('-', ' ').replace('_', ' ').title()
                    file_indent = '  ' * (depth + 1) if depth > 0 else '  '
                    f.write(f"{file_indent}* [{title}]({url_path})\n")

def process_migration(input_path):
    """Detects paths and runs the conversion."""
    # 1. Handle macOS tildes and absolute paths
    input_dir = os.path.abspath(os.path.expanduser(input_path))

    # 2. Automatically set output folder to the same location as input
    parent_dir = os.path.dirname(input_dir)
    folder_name = os.path.basename(input_dir)
    output_dir = os.path.join(parent_dir, f"{folder_name}-gitbook")

    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    print(f"🚀 Source: {input_dir}")
    print(f"📂 Creating output at: {output_dir}\n")

    for root, dirs, files in os.walk(input_dir):
        rel_path = os.path.relpath(root, input_dir)
        current_out_dir = os.path.join(output_dir, rel_path)

        if not os.path.exists(current_out_dir):
            os.makedirs(current_out_dir)

        for file in files:
            if file.endswith(('.html', '.htm')):
                in_path = os.path.join(root, file)
                clean_name = file.replace(' ', '-').replace('.html', '.md').replace('.htm', '.md')
                out_path = os.path.join(current_out_dir, clean_name)

                try:
                    with open(in_path, 'r', encoding='utf-8') as f:
                        content = f.read()
                    cleaned = clean_html(content)
                    final_md = convert_to_markdown(cleaned)
                    with open(out_path, 'w', encoding='utf-8') as f:
                        f.write(final_md)
                    print(f"✅ Converted: {clean_name}")
                except Exception as e:
                    print(f"❌ Error converting {file}: {e}")

    print("\n📝 Generating SUMMARY.md...")
    generate_summary(output_dir)
    print(f"\n🎉 Done! Result folder: {output_dir}")

if __name__ == "__main__":
    # --- JUST SET YOUR INPUT FOLDER HERE ---
    # Example: If this is on your Desktop, the output will appear on your Desktop.
    SOURCE_FOLDER = "confluence-export"

    process_migration(SOURCE_FOLDER)
