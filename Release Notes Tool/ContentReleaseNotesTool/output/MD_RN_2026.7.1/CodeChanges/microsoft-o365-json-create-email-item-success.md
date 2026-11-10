# Code Changes for microsoft-o365-json-create-email-item-success (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | user_upn | ['"UserId":\s*"(anonymous|({user_upn}[^",]+))"', 'MailboxOwnerUPN":\s"(anonymous|({user_upn}[^"]+))",', 'exa_json_path=$.UserId,exa_regex=(anonymous|({user_upn}[^"]+))'] | ['"UserId":\s*"(anonymous|({user_upn}[^",]+))"', 'MailboxOwnerUPN":\s"(anonymous|({user_upn}[^"]+))",', 'exa_json_path=$..UserId,exa_regex=(anonymous|({user_upn}[^"]+))'] |