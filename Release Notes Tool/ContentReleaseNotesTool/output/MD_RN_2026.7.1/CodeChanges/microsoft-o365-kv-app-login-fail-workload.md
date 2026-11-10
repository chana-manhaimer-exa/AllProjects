# Code Changes for microsoft-o365-kv-app-login-fail-workload (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'domain', 'email_address', 'event_name', 'object', 'result', 'src_ip', 'src_port', 'time', 'user', 'user_agent'] | ['app', 'domain', 'email_address', 'event_name', 'object', 'result', 'session_id', 'src_ip', 'src_port', 'time', 'user', 'user_agent'] |
| added_regex_field | session_id | [] | ['SESSID=({session_id}[^\s]+)\s\w+='] |