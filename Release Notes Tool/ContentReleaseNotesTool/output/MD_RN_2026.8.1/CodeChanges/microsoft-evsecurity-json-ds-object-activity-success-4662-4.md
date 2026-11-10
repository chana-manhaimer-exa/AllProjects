# Code Changes for microsoft-evsecurity-json-ds-object-activity-success-4662-4 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'action', 'activity_id', 'additional_info', 'app', 'dest_domain', 'dest_login_id', 'dest_user', 'dest_user_sid', 'domain', 'event_code', 'event_id', 'event_name', 'host', 'login_id', 'login_type', 'object', 'object_name', 'object_server', 'object_type', 'operation', 'os', 'privileges', 'process_id', 'provider_name', 'result', 'sid_history', 'src_domain', 'src_host', 'src_user', 'task_name', 'thread_id', 'time', 'user', 'user_sid'] | ['access', 'action', 'activity_id', 'additional_info', 'app', 'dest_domain', 'dest_login_id', 'dest_user', 'dest_user_sid', 'domain', 'ds_object_name', 'ds_object_type', 'event_code', 'event_id', 'event_name', 'host', 'login_id', 'login_type', 'object', 'object_server', 'operation', 'os', 'privileges', 'process_id', 'provider_name', 'result', 'sid_history', 'src_domain', 'src_host', 'src_user', 'task_name', 'thread_id', 'time', 'user', 'user_sid'] |
| edit_regex_field | object | ['"ObjectName":"({object}({object_name}[^"]+))"'] | ['"ObjectName":"({object}({ds_object_name}[^"]+))"'] |
| removed_regex_field | object_name | ['"ObjectName":"({object}({object_name}[^"]+))"'] | [] |
| removed_regex_field | object_type | ['"ObjectType":"({object_type}[^"]+)"'] | [] |
| added_regex_field | ds_object_name | [] | ['"ObjectName":"({object}({ds_object_name}[^"]+))"'] |
| added_regex_field | ds_object_type | [] | ['"ObjectType":"({ds_object_type}[^"]+)"'] |