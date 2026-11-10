# Code Changes for microsoft-evsystem-kv-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_user | ['Service Account:\s+({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\s*(\w+=|$)'] | ['Service Account:\s+(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\w+=|$)'] |