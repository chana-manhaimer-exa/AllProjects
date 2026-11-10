# Code Changes for microsoft-evsystem-kv-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_name', 'dest_host', 'event_code', 'event_name', 'host', 'process_command_line', 'process_dir', 'process_name', 'process_path', 'service_command_line', 'service_name', 'service_type', 'time', 'user', 'user_sid'] | ['dest_host', 'dest_user', 'event_code', 'event_name', 'host', 'process_command_line', 'process_dir', 'process_name', 'process_path', 'service_command_line', 'service_name', 'service_type', 'time', 'user', 'user_sid'] |
| removed_regex_field | account_name | ['Service Account:\s+({account_name}.+?)\s*(\w+=|$)'] | [] |
| added_regex_field | dest_user | [] | ['Service Account:\s+({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\s*(\w+=|$)'] |