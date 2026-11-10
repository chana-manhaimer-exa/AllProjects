#!/bin/bash
cd "$(dirname "$0")"

# Prompt the user for the file path
read -p "Please enter the path for the input JSON file in the following format folder name/filename.json :  " input_path

# Run the python script and pass the path to it
python3 "ContentReleaseNotes.py" "$input_path"
