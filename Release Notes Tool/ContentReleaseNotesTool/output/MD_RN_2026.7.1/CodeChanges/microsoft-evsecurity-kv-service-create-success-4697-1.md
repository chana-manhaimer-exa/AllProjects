# Code Changes for microsoft-evsecurity-kv-service-create-success-4697-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_domain', 'account_name', 'dest_host', 'domain', 'event_code', 'host', 'login_id', 'process_dir', 'process_name', 'process_path', 'service_name', 'service_type', 'time', 'user', 'user_sid'] | ['dest_domain', 'dest_host', 'dest_user', 'domain', 'event_code', 'host', 'login_id', 'process_dir', 'process_name', 'process_path', 'service_name', 'service_type', 'time', 'user', 'user_sid'] |
| removed_regex_field | account_domain | ['\WService Account:\s*(({account_domain}[^\\]+)\\)?({account_name}.+?)\s*$'] | [] |
| removed_regex_field | account_name | ['\WService Account:\s*(({account_domain}[^\\]+)\\)?({account_name}.+?)\s*$'] | [] |
| added_regex_field | dest_domain | [] | ['\WService Account:\s*(({dest_domain}[^\\]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\s*$'] |
| added_regex_field | dest_user | [] | ['\WService Account:\s*(({dest_domain}[^\\]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\s*$'] |