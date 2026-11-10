# Code Changes for microsoft-defenderep-json-service-create-serviceinstalled (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_user | ['AdditionalFields":[^\}]*?ServiceAccount\\+?":\\+?"({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\\+?"', 'exa_json_path=$..AdditionalFields,exa_regex=ServiceAccount\\":\\"({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)\\"'] | ['AdditionalFields":[^\}]*?ServiceAccount\\+?":\\+?"(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\\+?"', 'exa_json_path=$..AdditionalFields,exa_regex=ServiceAccount\\":\\"((?i)localsystem|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\\"'] |