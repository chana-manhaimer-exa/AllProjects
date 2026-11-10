# Code Changes for hp-arubacpm-kv-radius-traffic-fail-authenticationfailed (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['auth_method', 'auth_server', 'dest_ip', 'domain', 'event_code', 'failure_reason', 'host', 'network', 'src_domain', 'src_host', 'src_mac', 'time', 'user', 'user_type'] | ['auth_method', 'auth_server', 'dest_ip', 'domain', 'email_address', 'event_code', 'failure_reason', 'host', 'network', 'src_domain', 'src_host', 'src_mac', 'time', 'user', 'user_type'] |
| edit_regex_field | user | ['Common\.Username=(?!(host)/)(({domain}[^\\\s,]+)\\+)?(anonymous|({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] | ['Common\.Username=(?!(host)/)(({domain}[^\\\s,]+)\\+)?(anonymous|({user}[\w\.\-\!\#\^\~]{1,40}\$?))', 'RADIUS\.Acct-Username=(({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)|({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| added_regex_field | email_address | [] | ['RADIUS\.Acct-Username=(({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)|({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |