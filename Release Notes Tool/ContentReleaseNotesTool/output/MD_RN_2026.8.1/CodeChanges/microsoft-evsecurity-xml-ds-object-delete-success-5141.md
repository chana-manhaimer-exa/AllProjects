# Code Changes for microsoft-evsecurity-xml-ds-object-delete-success-5141 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['channel', 'domain', 'ds_name', 'ds_object_dn', 'ds_type', 'event_code', 'event_name', 'host', 'login_id', 'object_type', 'result', 'run_level', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] | ['channel', 'domain', 'ds_name', 'ds_object_dn', 'ds_object_type', 'ds_type', 'event_code', 'event_name', 'host', 'login_id', 'result', 'run_level', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['<Data Name\\*=(\'|")ObjectClass(\'|")>(|({object_type}[^<]+?))</Data>'] | [] |
| added_regex_field | ds_object_type | [] | ['<Data Name\\*=(\'|")ObjectClass(\'|")>(|({ds_object_type}[^<]+?))</Data>'] |