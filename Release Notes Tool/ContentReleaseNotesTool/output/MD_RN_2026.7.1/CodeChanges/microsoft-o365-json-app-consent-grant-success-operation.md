# Code Changes for microsoft-o365-json-app-consent-grant-success-operation (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'domain', 'email_address', 'full_name', 'object', 'permission', 'principal_type', 'user', 'user_agent'] | ['app', 'domain', 'email_address', 'full_name', 'object', 'operation', 'permission', 'principal_type', 'user', 'user_agent'] |
| added_regex_field | operation | [] | ['exa_regex="Operation":\s*"({operation}[^"]+?)\s*\.?"'] |