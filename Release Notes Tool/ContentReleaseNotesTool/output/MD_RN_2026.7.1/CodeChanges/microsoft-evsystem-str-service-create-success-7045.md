# Code Changes for microsoft-evsystem-str-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_domain', 'account_name', 'channel', 'dest_host', 'dest_ip', 'domain', 'event_code', 'event_name', 'host', 'process_command_line', 'process_dir', 'process_name', 'process_path', 'service_command_line', 'service_name', 'service_type', 'time', 'user'] | ['channel', 'dest_domain', 'dest_host', 'dest_ip', 'dest_user', 'domain', 'event_code', 'event_name', 'host', 'process_command_line', 'process_dir', 'process_name', 'process_path', 'service_command_line', 'service_name', 'service_type', 'time', 'user'] |
| removed_regex_field | account_domain | ['Service Account:\s*(|(({account_domain}[^\s"]+)\\)?({account_name}.+?))\s*(\"|$|<)'] | [] |
| removed_regex_field | account_name | ['Service Account:\s*(|(({account_domain}[^\s"]+)\\)?({account_name}.+?))\s*(\"|$|<)'] | [] |
| added_regex_field | dest_domain | [] | ['Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] |
| added_regex_field | dest_user | [] | ['Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] |