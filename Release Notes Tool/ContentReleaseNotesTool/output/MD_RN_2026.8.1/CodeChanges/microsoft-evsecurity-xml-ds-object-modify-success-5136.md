# Code Changes for microsoft-evsecurity-xml-ds-object-modify-success-5136 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['attribute', 'attribute_value', 'channel', 'dest_host', 'domain', 'ds_name', 'ds_object_dn', 'ds_type', 'event_code', 'event_name', 'host', 'login_id', 'object_type', 'operation_type', 'run_level', 'src_domain', 'src_user', 'time', 'user'] | ['attribute', 'attribute_value', 'channel', 'dest_host', 'domain', 'ds_name', 'ds_object_dn', 'ds_object_type', 'ds_type', 'event_code', 'event_name', 'host', 'login_id', 'operation_type', 'run_level', 'src_domain', 'src_user', 'time', 'user'] |
| removed_regex_field | object_type | ['<Data Name\\*=(\'|")ObjectClass(\'|")>\s*({object_type}[^<]+?)\s*</Data>'] | [] |
| added_regex_field | ds_object_type | [] | ['<Data Name\\*=(\'|")ObjectClass(\'|")>\s*({ds_object_type}[^<]+?)\s*</Data>'] |