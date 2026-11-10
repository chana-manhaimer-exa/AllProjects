# Code Changes for microsoft-evsystem-xml-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_domain', 'account_name', 'arg', 'channel', 'event_code', 'event_name', 'host', 'process_command_line', 'process_dir', 'process_id', 'process_name', 'process_path', 'run_level', 'service_name', 'service_type', 'src_host', 'time', 'user_sid'] | ['arg', 'channel', 'dest_domain', 'dest_user', 'event_code', 'event_name', 'host', 'process_command_line', 'process_dir', 'process_id', 'process_name', 'process_path', 'run_level', 'service_name', 'service_type', 'src_host', 'time', 'user_sid'] |
| removed_regex_field | account_domain | ['<Data Name\\*=(\'|")AccountName(\'|")>(({account_domain}[^<>\\\/]+)[\\\/]+)?({account_name}[^<]+?)</Data>'] | [] |
| removed_regex_field | account_name | ['<Data Name\\*=(\'|")AccountName(\'|")>(({account_domain}[^<>\\\/]+)[\\\/]+)?({account_name}[^<]+?)</Data>'] | [] |
| added_regex_field | dest_domain | [] | ['<Data Name\\*=(\'|")AccountName(\'|")>(({dest_domain}[^<>\\\/]+)[\\\/]+)?({dest_user}[^<]+?)</Data>'] |
| added_regex_field | dest_user | [] | ['<Data Name\\*=(\'|")AccountName(\'|")>(({dest_domain}[^<>\\\/]+)[\\\/]+)?({dest_user}[^<]+?)</Data>'] |