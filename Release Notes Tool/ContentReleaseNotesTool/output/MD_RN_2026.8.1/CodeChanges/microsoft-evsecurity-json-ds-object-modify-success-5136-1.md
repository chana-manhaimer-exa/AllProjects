# Code Changes for microsoft-evsecurity-json-ds-object-modify-success-5136-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['dest_host', 'dest_ip', 'dest_port', 'domain', 'ds_object_dn', 'ds_object_ou', 'event_code', 'event_name', 'host', 'login_id', 'object_type', 'src_ip', 'src_port', 'time', 'user', 'user_sid'] | ['dest_host', 'dest_ip', 'dest_port', 'domain', 'ds_object_dn', 'ds_object_ou', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'src_ip', 'src_port', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['"Target_Class":"({object_type}[^"]+)'] | [] |
| added_regex_field | ds_object_type | [] | ['"Target_Class":"({ds_object_type}[^"]+)'] |