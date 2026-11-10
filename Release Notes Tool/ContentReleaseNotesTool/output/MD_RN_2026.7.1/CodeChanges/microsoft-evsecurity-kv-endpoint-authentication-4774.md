# Code Changes for microsoft-evsecurity-kv-endpoint-authentication-4774 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | MMM dd HH:mm:ss yyyy | ['MMM dd HH:mm:ss yyyy', "yyyy-MM-dd'T'HH:mm:ss"] |
| changed_parsed_fields | N/A | ['account', 'auth_package', 'domain', 'event_code', 'event_name', 'host', 'time', 'user', 'user_type'] | ['account', 'auth_package', 'domain', 'event_code', 'event_name', 'host', 'src_host', 'time', 'user', 'user_type'] |
| edit_regex_field | account | ['Mapped Name:\s*({account}[^\s]+)'] | ['Mapped Name:\s*({account}[^\s\<\>]+)'] |
| edit_regex_field | auth_package | ['Authentication Package:\s*({auth_package}[^\s]+)'] | ['Authentication Package:\s*({auth_package}[^\s\<\>]+)'] |
| edit_regex_field | domain | ['Account UPN:\s*(?:({user_type}host)/)?(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| edit_regex_field | time | ['({time}\w+ \d+ \d+:\d+:\d+ \d\d\d\d)'] | ['({time}\w+ \d+ \d+:\d+:\d+ \d\d\d\d)', 'SystemTime=(\'|")({time}\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d)'] |
| edit_regex_field | user | ['Account UPN:\s*(?:({user_type}host)/)?(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| edit_regex_field | user_type | ['Account UPN:\s*(?:({user_type}host)/)?(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| added_regex_field | src_host | [] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |