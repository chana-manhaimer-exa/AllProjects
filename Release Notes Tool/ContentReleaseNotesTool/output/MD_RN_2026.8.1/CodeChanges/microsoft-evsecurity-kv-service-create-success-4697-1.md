# Code Changes for microsoft-evsecurity-kv-service-create-success-4697-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_domain | ['\WService Account:\s*(({dest_domain}[^\\]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\s*$'] | ['\WService Account:\s*(({dest_domain}[^\\]+)\\)?(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*$'] |
| edit_regex_field | dest_user | ['\WService Account:\s*(({dest_domain}[^\\]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\s*$'] | ['\WService Account:\s*(({dest_domain}[^\\]+)\\)?(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*$'] |