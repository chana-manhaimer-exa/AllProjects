# Code Changes for microsoft-o365-sk4-app-file-success-groupadd (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'app', 'category', 'connection_id', 'email_address', 'email_domain', 'event_name', 'file_name', 'group_name', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'os', 'process_name', 'resource', 'result', 'src_ip', 'src_port', 'tenant_id', 'time', 'user_agent'] | ['additional_info', 'app', 'category', 'connection_id', 'email_address', 'email_domain', 'event_name', 'file_name', 'group_name', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'os', 'process_name', 'resource', 'result', 'session_id', 'src_ip', 'src_port', 'tenant_id', 'time', 'user_agent'] |
| added_regex_field | session_id | [] | ['"AADSessionId":"({session_id}[^"]+)"', '"SessionId":"({session_id}[^"]+)"'] |