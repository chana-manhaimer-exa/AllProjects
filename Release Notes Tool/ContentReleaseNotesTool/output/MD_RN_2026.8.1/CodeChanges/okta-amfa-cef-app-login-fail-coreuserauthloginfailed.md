# Code Changes for okta-amfa-cef-app-login-fail-coreuserauthloginfailed (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | activity_type | ['alert-trigger:success', 'app-activity:fail', 'app-activity:success', 'app-login:fail', 'app-login:success', 'user-lock:success'] | ['alert-trigger:success', 'app-activity:fail', 'app-activity:success', 'app-authentication:success', 'app-login:fail', 'app-login:success', 'user-lock:success'] |
| edit_attribute | legacy_activity_type | ['account-lockout', 'app-activity', 'app-activity-failed', 'app-login', 'failed-app-login', 'security-alert'] | ['account-lockout', 'app-activity', 'app-activity-failed', 'app-login', 'authentication-successful', 'failed-app-login', 'security-alert'] |