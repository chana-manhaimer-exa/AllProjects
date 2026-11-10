# Code Changes for microsoft-evsecurity-xml-service-create-success-4697 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_domain', 'account_name', 'channel', 'dest_host', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'process_dir', 'process_name', 'process_path', 'run_level', 'service_name', 'service_type', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] | ['channel', 'dest_domain', 'dest_host', 'dest_user', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'process_dir', 'process_name', 'process_path', 'run_level', 'service_name', 'service_type', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] |
| removed_regex_field | account_domain | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({account_domain}[^\\<]*)\\)?({account_name}[^<]+)</Data>'] | [] |
| removed_regex_field | account_name | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({account_domain}[^\\<]*)\\)?({account_name}[^<]+)</Data>'] | [] |
| added_regex_field | dest_domain | [] | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({dest_domain}[^\\<]*)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)</Data>'] |
| added_regex_field | dest_user | [] | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({dest_domain}[^\\<]*)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)</Data>'] |