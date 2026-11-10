# Code Changes for microsoft-evsystem-str-service-create-success-7045 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | dest_domain | ['Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] | ['Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)))\s*(\"|$|<)'] |
| edit_regex_field | dest_user | ['Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?))\s*(\"|$|<)'] | ['Service Account:\s*(|(({dest_domain}[^\s"]+)\\)?(localsystem|NetworkService|NETWORKSERVICE|({dest_user}[\w\.\-\!\#\^\~]{1,40}\$?)))\s*(\"|$|<)'] |