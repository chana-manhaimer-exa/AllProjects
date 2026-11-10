# Code Changes for netskope-sc-cef-app-login-fail-loginfailed (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'app', 'auth_method', 'browser', 'bytes', 'dest_ip', 'dest_port', 'domain', 'email_address', 'email_domain', 'file_type', 'full_name', 'location', 'mime', 'operation', 'os', 'referrer', 'src_host', 'src_ip', 'src_port', 'time', 'url', 'user', 'user_agent', 'web_domain'] | ['action', 'app', 'auth_method', 'browser', 'bytes', 'dest_ip', 'dest_port', 'domain', 'email_address', 'email_domain', 'file_type', 'full_name', 'location', 'mime', 'operation', 'os', 'parent_process_name', 'process_name', 'referrer', 'src_host', 'src_ip', 'src_port', 'time', 'url', 'user', 'user_agent', 'web_domain'] |
| added_regex_field | parent_process_name | [] | ['"parent_process_name":\s*"({parent_process_name}[^"]+)"'] |
| added_regex_field | process_name | [] | ['"process_name":\s*"({process_name}[^"]+)"'] |