# Code Changes for microsoft-evsecurity-json-ds-object-activity-success-4662 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'additional_info', 'channel', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_name', 'object_server', 'object_type', 'operation', 'properties', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] | ['access', 'additional_info', 'channel', 'domain', 'ds_object_name', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_server', 'operation', 'properties', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] |
| edit_regex_field | object | ['"ObjectName":"({object}({object_name}[^"]+))"'] | ['"ObjectName":"({object}({ds_object_name}[^"]+))"'] |
| removed_regex_field | object_name | ['"ObjectName":"({object}({object_name}[^"]+))"'] | [] |
| removed_regex_field | object_type | ['"ObjectType":"({object_type}[^"]+)"'] | [] |
| added_regex_field | ds_object_name | [] | ['"ObjectName":"({object}({ds_object_name}[^"]+))"'] |
| added_regex_field | ds_object_type | [] | ['"ObjectType":"({ds_object_type}[^"]+)"'] |