# Code Changes for github-g-json-repository-create-success-repocreate (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['domain', 'email_address', 'src_ip', 'src_user', 'user', 'user_type'] | ['domain', 'email_address', 'operation', 'service_name', 'src_ip', 'src_user', 'user', 'user_type'] |
| added_regex_field | operation | [] | ['exa_regex="action":\s*"({operation}[^"]+)"'] |
| added_regex_field | service_name | [] | ['exa_regex="service":\s*"({service_name}[^"]+)"'] |