# Code Changes for cisco-unifiedcm-endpoint-authentication-fail (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'cisco-cucm-kv-endpoint-authentication-fail-userlogging') && exists(user) | InList(type, 'cisco-cucm-kv-endpoint-authentication-fail-userlogging') && InList(toLower(result), 'failure') |