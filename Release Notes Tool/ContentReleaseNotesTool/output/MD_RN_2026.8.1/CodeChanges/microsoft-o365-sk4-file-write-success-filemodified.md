# Code Changes for microsoft-o365-sk4-file-write-success-filemodified (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'email_address', 'file_dir', 'file_ext', 'file_name', 'file_path', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'os', 'resource', 'result', 'site_name', 'src_ip', 'src_port', 'time', 'url', 'user', 'web_domain'] | ['app', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'ds_object_type', 'email_address', 'file_dir', 'file_ext', 'file_name', 'file_path', 'log_source', 'object', 'object_id', 'operation', 'os', 'resource', 'result', 'site_name', 'src_ip', 'src_port', 'time', 'url', 'user', 'web_domain'] |
| removed_regex_field | object_type | ['"activityType":"({object_type}[^"]+)"', 'exa_regex="activityType":"({object_type}[^"]+)"'] | [] |
| added_regex_field | ds_object_type | [] | ['"activityType":"({ds_object_type}[^"]+)"', 'exa_regex="activityType":"({ds_object_type}[^"]+)"'] |