# Code Changes for m365-activity-json (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['domain', 'email_address', 'full_name', 'object', 'user', 'user_agent'] | ['domain', 'email_address', 'full_name', 'object', 'operation', 'user', 'user_agent'] |
| added_regex_field | operation | [] | ['exa_regex="Operation":\s*"({operation}[^"]+?)\s*\.?"'] |