# Code Changes for microsoft-evsystem-xml-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_domain | ['<Data Name\\*=(\'|")AccountName(\'|")>(({dest_domain}[^<>\\\/]+)[\\\/]+)?({dest_user}[^<]+?)</Data>'] | ['<Data Name\\*=(\'|")AccountName(\'|")>(({dest_domain}[^<>\\\/]+)[\\\/]+)?(localsystem|NetworkService|NETWORKSERVICE({dest_user}[^<]+?))</Data>'] |
| edit_regex_field | dest_user | ['<Data Name\\*=(\'|")AccountName(\'|")>(({dest_domain}[^<>\\\/]+)[\\\/]+)?({dest_user}[^<]+?)</Data>'] | ['<Data Name\\*=(\'|")AccountName(\'|")>(({dest_domain}[^<>\\\/]+)[\\\/]+)?(localsystem|NetworkService|NETWORKSERVICE({dest_user}[^<]+?))</Data>'] |