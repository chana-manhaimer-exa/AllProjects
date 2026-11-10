# Code Changes for cisco-cucm-kv-endpoint-authentication-fail-userlogging (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | activity_type | ['endpoint-authentication:fail'] | ['endpoint-authentication:fail', 'endpoint-authentication:success'] |
| edit_attribute | legacy_activity_type | ['authentication-failed'] | ['authentication-failed', 'authentication-successful'] |