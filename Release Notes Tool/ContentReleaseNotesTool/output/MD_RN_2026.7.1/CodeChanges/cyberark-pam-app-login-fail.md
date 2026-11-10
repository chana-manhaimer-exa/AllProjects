# Code Changes for cyberark-pam-app-login-fail (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'cyberark-pam-cef-app-login-fail-userauth')  | InList(type, 'cyberark-pam-str-app-activity-success-identity') && InList(toLower(operation), 'failed login') && InList(toLower(action), 'deny') |