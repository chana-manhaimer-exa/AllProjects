# Code Changes for microsoft-evsecurity-xml-endpoint-authentication-4774 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account', 'channel', 'domain', 'event_code', 'event_name', 'host', 'run_level', 'time', 'user', 'user_type'] | ['account', 'channel', 'domain', 'event_code', 'event_name', 'host', 'run_level', 'src_host', 'time', 'user', 'user_type'] |
| edit_regex_field | domain | ['Account UPN:\s*(?:({user_type}host)/)?(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| edit_regex_field | user | ['Account UPN:\s*(?:({user_type}host)/)?(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| edit_regex_field | user_type | ['Account UPN:\s*(?:({user_type}host)/)?(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| added_regex_field | src_host | [] | ['Account UPN:\s*((?:({user_type}host)\/({src_host}[\w\-.]+))|(({domain}[^\\]+)\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |