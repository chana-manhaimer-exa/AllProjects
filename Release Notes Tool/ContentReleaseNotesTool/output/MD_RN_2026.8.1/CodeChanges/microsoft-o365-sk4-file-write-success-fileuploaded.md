# Code Changes for microsoft-o365-sk4-file-write-success-fileuploaded (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'app_id', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'email_address', 'email_domain', 'file_dir', 'file_ext', 'file_name', 'file_path', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'resource', 'result', 'site_name', 'src_file_dir', 'src_ip', 'src_port', 'time', 'url', 'user_type', 'web_domain'] | ['app', 'app_id', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'ds_object_type', 'email_address', 'email_domain', 'file_dir', 'file_ext', 'file_name', 'file_path', 'log_source', 'object', 'object_id', 'operation', 'resource', 'result', 'site_name', 'src_file_dir', 'src_ip', 'src_port', 'time', 'url', 'user_type', 'web_domain'] |
| removed_regex_field | object_type | ['"activityType":"({object_type}[^"]+)"'] | [] |
| added_regex_field | ds_object_type | [] | ['"activityType":"({ds_object_type}[^"]+)"'] |