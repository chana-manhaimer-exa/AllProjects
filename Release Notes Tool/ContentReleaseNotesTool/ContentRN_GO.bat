@echo off

REM Prompt the user for the file path
echo Please enter the path for the input JSON file in the following format folder name/filename.json .
set /p input_path="File Path: "

REM Run the python script and pass the path to it
python "ContentReleaseNotes.py" "%input_path%"

pause

