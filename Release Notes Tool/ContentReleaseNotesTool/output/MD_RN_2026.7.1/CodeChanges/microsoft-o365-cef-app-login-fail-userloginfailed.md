# Code Changes for microsoft-o365-cef-app-login-fail-userloginfailed (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'app_id', 'browser', 'email_address', 'email_domain', 'error_code', 'event_name', 'failure_code', 'failure_reason', 'host', 'object', 'object_id', 'operation', 'os', 'request_type', 'resource', 'result', 'session_id', 'src_ip', 'src_port', 'time', 'user', 'user_agent', 'user_id', 'user_type', 'user_upn'] | ['app', 'app_id', 'browser', 'email_address', 'email_domain', 'error_code', 'event_name', 'failure_code', 'failure_reason', 'host', 'login_type_text', 'object', 'object_id', 'operation', 'os', 'request_type', 'resource', 'result', 'session_id', 'src_ip', 'src_port', 'time', 'user', 'user_agent', 'user_id', 'user_type', 'user_upn'] |
| edit_regex_field | session_id | ['\{"Value":"({session_id}[^",]+)","Name":"SessionId"'] | ['\{"Name":"SessionId","Value":"({session_id}[^",]+)"', '\{"Value":"({session_id}[^",]+)","Name":"SessionId"'] |
| added_regex_field | login_type_text | [] | ['"Value":\s*"Login:({login_type_text}[^"]+)"'] |