# Code Changes for cyberark-pam-app-login-success (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'cyberark-pam-cef-app-login-success-logon','cyberark-pam-mix-app-login-success-logon') | InList(type, 'cyberark-pam-str-app-activity-success-identity') && InList(toLower(operation), 'login') && InList(toLower(action), 'login') |