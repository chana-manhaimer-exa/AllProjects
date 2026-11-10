# Code Changes for microsoft-evsystem-json-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_user | ['Service Account:\s*(\\\\t|\\\\r|\\\\n)*(|({dest_user}[^\\"]+?))\s*(\\t|\\r|\\n)*"'] | ['Service Account:\s*(\\\\t|\\\\r|\\\\n)*(|localsystem|NetworkService|NETWORKSERVICE|({dest_user}[^\\"]+?))\s*(\\t|\\r|\\n)*"'] |