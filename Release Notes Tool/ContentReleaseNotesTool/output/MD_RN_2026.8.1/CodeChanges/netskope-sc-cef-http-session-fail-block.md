# Code Changes for netskope-sc-cef-http-session-fail-block (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'additional_info', 'app', 'auth_method', 'browser', 'bytes', 'bytes_in', 'bytes_out', 'categories', 'category', 'dest_country', 'dest_ip', 'dest_port', 'email_address', 'location', 'operation', 'os', 'protocol', 'referrer', 'src_country', 'src_host', 'src_ip', 'src_location', 'src_port', 'time', 'uri_path', 'uri_query', 'url', 'web_domain'] | ['action', 'additional_info', 'app', 'auth_method', 'browser', 'bytes', 'bytes_in', 'bytes_out', 'categories', 'category', 'dest_country', 'dest_ip', 'dest_port', 'email_address', 'location', 'operation', 'os', 'parent_process_name', 'process_name', 'protocol', 'referrer', 'src_country', 'src_host', 'src_ip', 'src_location', 'src_port', 'time', 'uri_path', 'uri_query', 'url', 'web_domain'] |
| added_regex_field | parent_process_name | [] | ['"parent_process_name":\s*"({parent_process_name}[^"]+)"'] |
| added_regex_field | process_name | [] | ['"process_name":\s*"({process_name}[^"]+)"'] |