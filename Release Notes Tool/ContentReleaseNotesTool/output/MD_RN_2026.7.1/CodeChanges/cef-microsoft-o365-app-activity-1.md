# Code Changes for cef-microsoft-o365-app-activity-1 (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | yyyy-MM-dd'T'HH:mm:ss |
| changed_parsed_fields | N/A | ['additional_info', 'app', 'domain', 'email_address', 'email_subject', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'first_name', 'full_name', 'group_name', 'host', 'last_name', 'object', 'operation', 'process_name', 'result', 'src_ip', 'src_port', 'tenant_id', 'time', 'user', 'user_agent', 'user_sid', 'user_type'] | ['additional_info', 'app', 'domain', 'email_address', 'email_subject', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'first_name', 'full_name', 'group_name', 'host', 'last_name', 'object', 'operation', 'process_name', 'result', 'session_id', 'src_ip', 'src_port', 'tenant_id', 'time', 'user', 'user_agent', 'user_sid', 'user_type'] |
| edit_regex_field | time | ['"CreationTime\\*"+:[\s\\]*"+({time}\d+-\d+-\d+T\d+:\d+:\d+)'] | ['"CreationTime\\*"+:[\s\\]*"+({time}\d+-\d+-\d+T\d+:\d+:\d+(\.\d+)?Z?)'] |
| added_regex_field | session_id | [] | ['"AADSessionId":"({session_id}[^"]+)"', '"SessionId":"({session_id}[^"]+)"'] |