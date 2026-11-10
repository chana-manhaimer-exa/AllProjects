# Code Changes for microsoft-evsecurity-xml-ds-object-activity-success-4662 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'access_list', 'access_mask', 'channel', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object_name', 'object_server', 'object_type', 'operation', 'properties', 'result', 'run_level', 'src_domain', 'src_host', 'src_user', 'time', 'user', 'user_sid'] | ['access', 'access_list', 'access_mask', 'channel', 'domain', 'ds_object_name', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'object_server', 'operation', 'properties', 'result', 'run_level', 'src_domain', 'src_host', 'src_user', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_name | ['<Data Name(\\)?=(\'|")ObjectName(\'|")>({object_name}[^<]+)'] | [] |
| removed_regex_field | object_type | ['<Data Name(\\)?=(\'|")ObjectType(\'|")>({object_type}[^<]+)'] | [] |
| added_regex_field | ds_object_name | [] | ['<Data Name(\\)?=(\'|")ObjectName(\'|")>({ds_object_name}[^<]+)'] |
| added_regex_field | ds_object_type | [] | ['<Data Name(\\)?=(\'|")ObjectType(\'|")>({ds_object_type}[^<]+)'] |