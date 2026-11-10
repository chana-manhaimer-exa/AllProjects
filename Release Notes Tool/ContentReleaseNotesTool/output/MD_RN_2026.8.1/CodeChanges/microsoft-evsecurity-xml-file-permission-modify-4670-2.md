# Code Changes for microsoft-evsecurity-xml-file-permission-modify-4670-2 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['attribute', 'channel', 'domain', 'event_code', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'host', 'login_id', 'new_attribute', 'object_id', 'object_name', 'object_server', 'object_type', 'old_attribute', 'permissions', 'process_dir', 'process_id', 'process_name', 'process_path', 'result', 'run_level', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] | ['attribute', 'channel', 'domain', 'ds_object_name', 'ds_object_type', 'event_code', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'host', 'login_id', 'new_attribute', 'object_id', 'object_server', 'old_attribute', 'permissions', 'process_dir', 'process_id', 'process_name', 'process_path', 'result', 'run_level', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_name | ['<Data Name\\*=(\'|")ObjectName(\'|")>(-|({object_name}[^<]+))</Data>'] | [] |
| removed_regex_field | object_type | ['<Data Name\\*=(\'|")ObjectType(\'|")>(-|({object_type}[^<]+))</Data>'] | [] |
| added_regex_field | ds_object_name | [] | ['<Data Name\\*=(\'|")ObjectName(\'|")>(-|({ds_object_name}[^<]+))</Data>'] |
| added_regex_field | ds_object_type | [] | ['<Data Name\\*=(\'|")ObjectType(\'|")>(-|({ds_object_type}[^<]+))</Data>'] |