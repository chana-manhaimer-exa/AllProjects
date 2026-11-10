# Code Changes for microsoft-evsecurity-json-service-create-success-4697 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_user | ['"ServiceAccount":"({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)"'] | ['"ServiceAccount":"(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))"', 'exa_json_path=$.ServiceAccount,exa_regex=^((?i)localsystem|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))$'] |