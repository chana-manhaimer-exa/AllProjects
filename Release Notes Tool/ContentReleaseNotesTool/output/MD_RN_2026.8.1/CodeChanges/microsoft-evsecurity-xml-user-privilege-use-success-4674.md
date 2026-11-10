# Code Changes for microsoft-evsecurity-xml-user-privilege-use-success-4674 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ | ["yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"] |
| changed_parsed_fields | N/A | ['channel', 'dest_host', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object_server', 'privileges', 'process_dir', 'process_name', 'process_path', 'result', 'run_level', 'src_domain', 'src_user', 'time', 'user'] | ['channel', 'dest_host', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object_server', 'privileges', 'process_dir', 'process_name', 'process_path', 'result', 'run_level', 'src_domain', 'src_user', 'time', 'user', 'user_sid'] |
| edit_regex_field | time | ['<TimeCreated SystemTime\\*=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d\d\d\d\d\d\d\d\dZ)'] | ['<TimeCreated SystemTime\\*=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)', '<TimeCreated SystemTime\\*=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d{7,9}Z)'] |
| added_regex_field | user_sid | [] | ['<Data Name\\*=(\'|")SubjectUserSid(\'|")>(?:NONE_MAPPED|({user_sid}S-[^<]+))<'] |