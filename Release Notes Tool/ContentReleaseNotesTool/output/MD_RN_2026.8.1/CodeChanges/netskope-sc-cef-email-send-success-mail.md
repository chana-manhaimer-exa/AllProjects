# Code Changes for netskope-sc-cef-email-send-success-mail (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'app', 'auth_method', 'browser', 'bytes', 'dest_email_address', 'dest_ip', 'dest_port', 'email_address', 'email_domain', 'email_recipients', 'file_type', 'from_user_at', 'full_name', 'location', 'object', 'operation', 'os', 'referrer', 'site_at', 'src_host', 'src_ip', 'src_port', 'time', 'url', 'user', 'web_domain'] | ['action', 'app', 'auth_method', 'browser', 'bytes', 'dest_email_address', 'dest_ip', 'dest_port', 'email_address', 'email_domain', 'email_recipients', 'file_type', 'from_user_at', 'full_name', 'location', 'object', 'operation', 'os', 'parent_process_name', 'process_name', 'referrer', 'site_at', 'src_host', 'src_ip', 'src_port', 'time', 'url', 'user', 'web_domain'] |
| added_regex_field | parent_process_name | [] | ['"parent_process_name":\s*"({parent_process_name}[^"]+)"'] |
| added_regex_field | process_name | [] | ['"process_name":\s*"({process_name}[^"]+)"'] |