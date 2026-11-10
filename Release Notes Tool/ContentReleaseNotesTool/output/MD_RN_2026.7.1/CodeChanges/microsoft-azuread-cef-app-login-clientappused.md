# Code Changes for microsoft-azuread-cef-app-login-clientappused (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | failure_reason | ['\"failureReason\":\"({failure_reason}[^\"]+)'] | ['\"failureReason\":\"(Other\.|({failure_reason}[^\"]+))"'] |