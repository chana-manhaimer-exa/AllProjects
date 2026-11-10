# Code Changes for github-g-json-app-activity-document_id (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'company', 'country_code', 'doc_id', 'domain', 'email_address', 'host', 'operation_type', 'src_ip', 'src_port', 'time', 'user', 'user_agent', 'user_id'] | ['action', 'company', 'country_code', 'doc_id', 'domain', 'email_address', 'host', 'operation_type', 'src_ip', 'src_port', 'time', 'user', 'user_agent', 'user_id', 'user_type'] |
| added_regex_field | user_type | [] | ['"actor_is_bot":({user_type}[^",]+)'] |