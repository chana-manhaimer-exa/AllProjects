# Code Changes for microsoft-evsecurity-xml-file-permission-modify-4670 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['attribute', 'channel', 'domain', 'event_code', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'host', 'login_id', 'object_name', 'object_server', 'object_type', 'permissions', 'process_dir', 'process_id', 'process_name', 'process_path', 'run_level', 'src_user', 'time', 'user', 'user_sid'] | ['attribute', 'channel', 'domain', 'ds_object_type', 'event_code', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'host', 'login_id', 'object_name', 'object_server', 'permissions', 'process_dir', 'process_id', 'process_name', 'process_path', 'run_level', 'src_user', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['Object Type:(\\t|\\r|\\n)*\s*(|({object_type}[^:]+?))(\\t|\\r|\\n)*\s*Object Name:'] | [] |
| added_regex_field | ds_object_type | [] | ['Object Type:(\\t|\\r|\\n)*\s*(|({ds_object_type}[^:]+?))(\\t|\\r|\\n)*\s*Object Name:'] |