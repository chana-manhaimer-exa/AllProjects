# Code Changes for microsoft-evsecurity-kv-ds-object-activity-success-4662-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'action', 'attribute', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_name', 'object_server', 'object_type', 'properties', 'time', 'user'] | ['access', 'action', 'attribute', 'domain', 'ds_object_name', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_server', 'properties', 'time', 'user'] |
| edit_regex_field | object | ['Object Name:\s*({object}({object_name}.+?))\s*Handle ID:'] | ['Object Name:\s*({object}({ds_object_name}.+?))\s*Handle ID:'] |
| removed_regex_field | object_name | ['Object Name:\s*({object}({object_name}.+?))\s*Handle ID:'] | [] |
| removed_regex_field | object_type | ['Object Type:\s*({object_type}.+?)\s*Object Name:'] | [] |
| added_regex_field | ds_object_name | [] | ['Object Name:\s*({object}({ds_object_name}.+?))\s*Handle ID:'] |
| added_regex_field | ds_object_type | [] | ['Object Type:\s*({ds_object_type}.+?)\s*Object Name:'] |