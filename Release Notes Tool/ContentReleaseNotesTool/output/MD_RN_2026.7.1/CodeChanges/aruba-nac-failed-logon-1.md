# Code Changes for aruba-nac-failed-logon-1 (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'hp-arubacpm-kv-radius-traffic-fail-authenticationfailed','hp-arubacpm-kv-radius-traffic-fail-sessionreuseerror') and not exists(user_type) and exists(user) | InList(type, 'hp-arubacpm-kv-radius-traffic-fail-authenticationfailed','hp-arubacpm-kv-radius-traffic-fail-sessionreuseerror') and (not exists(user_type) and exists(user)) or (Exists(email_address)) |