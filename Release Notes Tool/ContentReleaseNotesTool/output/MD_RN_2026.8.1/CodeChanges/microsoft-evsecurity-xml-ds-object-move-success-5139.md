# Code Changes for microsoft-evsecurity-xml-ds-object-move-success-5139 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['channel', 'domain', 'ds_name', 'ds_object_dn', 'ds_type', 'event_code', 'host', 'login_id', 'object_id', 'object_type', 'run_level', 'src_domain', 'src_ds_object_dn', 'src_user', 'time', 'user', 'user_sid'] | ['channel', 'domain', 'ds_name', 'ds_object_dn', 'ds_object_type', 'ds_type', 'event_code', 'host', 'login_id', 'object_id', 'run_level', 'src_domain', 'src_ds_object_dn', 'src_user', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['<Data Name\\*=(\'|")ObjectClass(\'|")>\s*({object_type}.+?)\s*</Data>'] | [] |
| added_regex_field | ds_object_type | [] | ['<Data Name\\*=(\'|")ObjectClass(\'|")>\s*({ds_object_type}.+?)\s*</Data>'] |