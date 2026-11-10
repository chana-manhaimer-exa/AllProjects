# Code Changes for microsoft-windows-json-service-create-success-4697 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_domain | ['exa_regex=Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] | ['exa_regex=Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?((?i)localsystem|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)))\s*(\"|$|<)'] |
| edit_regex_field | dest_user | ['exa_regex=Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] | ['exa_regex=Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?((?i)localsystem|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)))\s*(\"|$|<)'] |