# Code Changes for microsoft-evsecurity-xml-service-create-success-4697 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_domain | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({dest_domain}[^\\<]*)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)</Data>'] | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({dest_domain}[^\\<]*)\\)?(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))</Data>'] |
| edit_regex_field | dest_user | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({dest_domain}[^\\<]*)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)</Data>'] | ['<Data Name(\\)?=(\'|")ServiceAccount(\'|")>(?=\w)(({dest_domain}[^\\<]*)\\)?(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))</Data>'] |