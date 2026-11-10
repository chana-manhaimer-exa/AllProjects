# Code Changes for microsoft-windows-json-service-create-success-4697 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_domain', 'account_name', 'event_code', 'event_name', 'process_command_line', 'process_dir', 'process_name', 'process_path', 'service_command_line', 'service_name'] | ['dest_domain', 'dest_user', 'event_code', 'event_name', 'process_command_line', 'process_dir', 'process_name', 'process_path', 'service_command_line', 'service_name'] |
| removed_regex_field | account_domain | ['exa_regex=Service Account:\s*(|(({account_domain}[^\s"]+)\\)?({account_name}.+?))\s*(\"|$|<)'] | [] |
| removed_regex_field | account_name | ['exa_regex=Service Account:\s*(|(({account_domain}[^\s"]+)\\)?({account_name}.+?))\s*(\"|$|<)'] | [] |
| added_regex_field | dest_domain | [] | ['exa_regex=Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] |
| added_regex_field | dest_user | [] | ['exa_regex=Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] |