# Code Changes for netskope-sc-cef-file-read-success-pageviewedextended (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'action', 'app', 'auth_method', 'browser', 'bytes', 'dest_ip', 'dest_port', 'domain', 'email_address', 'email_domain', 'file_dir', 'file_ext', 'file_name', 'file_type', 'folder_name', 'full_name', 'location', 'mime', 'object', 'object_type', 'operation', 'os', 'referrer', 'src_host', 'src_ip', 'src_port', 'time', 'url', 'user', 'user_agent', 'web_domain'] | ['access', 'action', 'app', 'auth_method', 'browser', 'bytes', 'dest_ip', 'dest_port', 'domain', 'email_address', 'email_domain', 'file_dir', 'file_ext', 'file_name', 'file_type', 'folder_name', 'full_name', 'location', 'mime', 'object', 'object_type', 'operation', 'os', 'parent_process_name', 'process_name', 'referrer', 'src_host', 'src_ip', 'src_port', 'time', 'url', 'user', 'user_agent', 'web_domain'] |
| added_regex_field | parent_process_name | [] | ['"parent_process_name":\s*"({parent_process_name}[^"]+)"'] |
| added_regex_field | process_name | [] | ['"process_name":\s*"({process_name}[^"]+)"'] |