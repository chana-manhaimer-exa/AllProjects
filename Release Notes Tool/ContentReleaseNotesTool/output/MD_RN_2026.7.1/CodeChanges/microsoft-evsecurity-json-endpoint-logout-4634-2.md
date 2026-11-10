# Code Changes for microsoft-evsecurity-json-endpoint-logout-4634-2 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | ["yyyy-MM-dd'T'HH:mm:ss"] |
| changed | Conditions | ['"EventID":"4634"', 'An account was logged off'] | ['"EventID":"4634"', 'Microsoft-Windows-Security-Auditing'] |