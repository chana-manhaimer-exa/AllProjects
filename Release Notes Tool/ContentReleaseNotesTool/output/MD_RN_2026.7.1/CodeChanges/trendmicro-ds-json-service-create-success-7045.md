# Code Changes for trendmicro-ds-json-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_domain', 'account_name', 'domain', 'event_category', 'event_code', 'event_name', 'host', 'login_id', 'os', 'process_dir', 'process_name', 'process_path', 'service_name', 'service_start_type', 'service_type', 'src_host', 'src_ip', 'tenant_id', 'time', 'user', 'user_sid'] | ['dest_domain', 'dest_user', 'domain', 'event_category', 'event_code', 'event_name', 'host', 'login_id', 'os', 'process_dir', 'process_name', 'process_path', 'service_name', 'service_start_type', 'service_type', 'src_host', 'src_ip', 'tenant_id', 'time', 'user', 'user_sid'] |
| removed_regex_field | account_domain | ['\sService Account:\s*(({account_domain}[^\\"\s]+)\\)?({account_name}[^"\s]+)'] | [] |
| removed_regex_field | account_name | ['\sService Account:\s*(({account_domain}[^\\"\s]+)\\)?({account_name}[^"\s]+)'] | [] |
| added_regex_field | dest_domain | [] | ['\sService Account:\s*(({dest_domain}[^\\"\s]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)'] |
| added_regex_field | dest_user | [] | ['\sService Account:\s*(({dest_domain}[^\\"\s]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)'] |