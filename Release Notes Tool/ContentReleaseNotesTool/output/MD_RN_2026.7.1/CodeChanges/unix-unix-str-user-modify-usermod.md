# Code Changes for unix-unix-str-user-modify-usermod (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'event_code', 'event_name', 'host', 'host_ip', 'process_id', 'process_name', 'user'] | ['additional_info', 'dest_user', 'event_code', 'event_name', 'host', 'host_ip', 'process_id', 'process_name', 'user'] |
| edit_regex_field | user | ["usermod.+?user\s'({user}[\w\.\-\!\#\^\~]{1,40}\$?)'"] | ["usermod.+?user\s'({dest_user}({user}[\w\.\-\!\#\^\~]{1,40}\$?))'"] |
| added_regex_field | dest_user | [] | ["usermod.+?user\s'({dest_user}({user}[\w\.\-\!\#\^\~]{1,40}\$?))'"] |