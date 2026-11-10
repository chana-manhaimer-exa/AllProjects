# Code Changes for microsoft-evsecurity-kv-ds-object-modify-success-5136 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['attribute', 'dest_host', 'domain', 'event_code', 'event_id', 'event_name', 'host', 'login_id', 'new_attribute', 'object_name', 'object_type', 'old_attribute', 'operation_type', 'src_host', 'src_ip', 'src_port', 'time', 'user', 'user_sid'] | ['attribute', 'dest_host', 'domain', 'ds_object_name', 'ds_object_type', 'event_code', 'event_id', 'event_name', 'host', 'login_id', 'new_attribute', 'old_attribute', 'operation_type', 'src_host', 'src_ip', 'src_port', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_name | ['ACCOUNT_NAME\s*=\s*(null|-|({object_name}[^\s]+))'] | [] |
| removed_regex_field | object_type | ["FORMAT_MESSAGE\s*=\s*(null|-|({object_type}[^\']+?))\s*'"] | [] |
| added_regex_field | ds_object_name | [] | ['ACCOUNT_NAME\s*=\s*(null|-|({ds_object_name}[^\s]+))'] |
| added_regex_field | ds_object_type | [] | ["FORMAT_MESSAGE\s*=\s*(null|-|({ds_object_type}[^\']+?))\s*'"] |