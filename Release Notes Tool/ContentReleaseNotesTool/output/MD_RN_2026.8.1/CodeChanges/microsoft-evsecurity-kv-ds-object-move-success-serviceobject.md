# Code Changes for microsoft-evsecurity-kv-ds-object-move-success-serviceobject (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['channel', 'correlation_id', 'domain', 'ds_name', 'ds_object_dn', 'ds_type', 'event_code', 'event_name', 'host', 'login_id', 'new_attribute', 'object_id', 'object_type', 'old_attribute', 'src_ds_object_dn', 'src_ds_object_ou', 'src_host', 'src_ip', 'time', 'user', 'user_sid'] | ['channel', 'correlation_id', 'domain', 'ds_name', 'ds_object_dn', 'ds_object_type', 'ds_type', 'event_code', 'event_name', 'host', 'login_id', 'new_attribute', 'object_id', 'old_attribute', 'src_ds_object_dn', 'src_ds_object_ou', 'src_host', 'src_ip', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['Class:\s+({object_type}[^:]+?)\s+Operation:'] | [] |
| added_regex_field | ds_object_type | [] | ['Class:\s+({ds_object_type}[^:]+?)\s+Operation:'] |