# Code Changes for github-g-json-user-invite-success-org (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'host', 'object', 'operation', 'resource', 'src_ip', 'src_port', 'time', 'user'] | ['app', 'host', 'object', 'operation', 'permissions', 'resource', 'src_ip', 'src_port', 'time', 'user', 'user_type'] |
| added_regex_field | permissions | [] | ['"permission":"({permissions}[^"]+)"'] |
| added_regex_field | user_type | [] | ['"actor_is_bot":({user_type}[^",]+)'] |