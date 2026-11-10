# Code Changes for crowdstrike-falcon-json-process-create-success-processrollup (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_process_command_line | ['"CommandLine":\s*"\s*({dest_process_command_line}({process_command_line}[^\n]+?))\s*"?,"'] | ['"CommandLine":\s*"\s*({dest_process_command_line}({process_command_line}[^,"]+?))\s*"?,"', 'exa_regex="CommandLine":\s*"\s*({dest_process_command_line}({process_command_line}[^,"]+?))\s*"?,"'] |
| edit_regex_field | process_command_line | ['"CommandLine":\s*"\s*({dest_process_command_line}({process_command_line}[^\n]+?))\s*"?,"'] | ['"CommandLine":\s*"\s*({dest_process_command_line}({process_command_line}[^,"]+?))\s*"?,"', 'exa_regex="CommandLine":\s*"\s*({dest_process_command_line}({process_command_line}[^,"]+?))\s*"?,"'] |