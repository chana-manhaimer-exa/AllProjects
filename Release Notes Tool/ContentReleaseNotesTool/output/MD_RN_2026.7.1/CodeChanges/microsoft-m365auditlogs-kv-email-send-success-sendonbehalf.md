# Code Changes for microsoft-m365auditlogs-kv-email-send-success-sendonbehalf (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'domain', 'email_address', 'email_attachment', 'email_attachments', 'email_subject', 'file_ext', 'host', 'message_id', 'operation', 'process_name', 'result', 'src_ip', 'src_port', 'target', 'time', 'user', 'user_agent', 'user_sid'] | ['app', 'domain', 'email_address', 'email_attachment', 'email_attachments', 'email_subject', 'file_ext', 'host', 'message_id', 'operation', 'process_name', 'result', 'session_id', 'src_ip', 'src_port', 'target', 'time', 'user', 'user_agent', 'user_sid'] |
| added_regex_field | session_id | [] | ['SESSID=({session_id}[^\s]+)\s\w+='] |