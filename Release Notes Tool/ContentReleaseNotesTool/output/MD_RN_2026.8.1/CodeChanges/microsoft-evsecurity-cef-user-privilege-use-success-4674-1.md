# Code Changes for microsoft-evsecurity-cef-user-privilege-use-success-4674-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'channel', 'dest_host', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_server', 'object_type', 'privileges', 'process_dir', 'process_name', 'process_path', 'result', 'time', 'user'] | ['access', 'channel', 'dest_host', 'domain', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_server', 'privileges', 'process_dir', 'process_name', 'process_path', 'result', 'time', 'user'] |
| removed_regex_field | object_type | ['Object Type:\s*((?-i)\\+[rnt])*(?:-|({object_type}[^:]+?))[\\rnt\s]*Object Name:'] | [] |
| added_regex_field | ds_object_type | [] | ['Object Type:\s*((?-i)\\+[rnt])*(?:-|({ds_object_type}[^:]+?))[\\rnt\s]*Object Name:'] |