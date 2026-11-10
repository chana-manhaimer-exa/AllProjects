# Code Changes for microsoft-evsecurity-kv-endpoint-success-4624-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss.SSSZ | ["yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ"] |
| changed | Conditions | ['4624', 'Logon', 'LogonType:', 'TargetUserName:'] | ['4624 ', 'Logon', 'LogonType:', 'TargetUserName:'] |
| edit_regex_field | auth_package | ['AuthenticationPackageName:({auth_package}[^,]+)'] | ['AuthenticationPackageName:({auth_package}[^,\s]+)'] |
| edit_regex_field | auth_process | ['LogonProcessName:({auth_process}[^,]+)'] | ['LogonProcessName:({auth_process}[^,\s]+)'] |
| edit_regex_field | dest_domain | ['TargetDomainName:({dest_domain}({domain}[^,]+))'] | ['TargetDomainName:({dest_domain}({domain}[^,\s]+))'] |
| edit_regex_field | dest_login_id | ['TargetLogonId:({dest_login_id}({login_id}[^,]+))'] | ['TargetLogonId:({dest_login_id}({login_id}[^,\s]+))'] |
| edit_regex_field | dest_user_sid | ['TargetUserSid:({dest_user_sid}({user_sid}[^,]+))'] | ['TargetUserSid:({dest_user_sid}({user_sid}[^,\s]+))'] |
| edit_regex_field | domain | ['TargetDomainName:({dest_domain}({domain}[^,]+))'] | ['TargetDomainName:({dest_domain}({domain}[^,\s]+))'] |
| edit_regex_field | login_id | ['TargetLogonId:({dest_login_id}({login_id}[^,]+))'] | ['TargetLogonId:({dest_login_id}({login_id}[^,\s]+))'] |
| edit_regex_field | src_host | ['WorkstationName:(-|({src_host_windows}({src_host}[^,]+)))'] | ['WorkstationName:(-|({src_host_windows}({src_host}[^,\s]+)))'] |
| edit_regex_field | src_host_windows | ['WorkstationName:(-|({src_host_windows}({src_host}[^,]+)))'] | ['WorkstationName:(-|({src_host_windows}({src_host}[^,\s]+)))'] |
| edit_regex_field | src_user | ['SubjectUserName:(-|({src_user}[^,]+))'] | ['SubjectUserName:(-|({src_user}[^,\s]+))'] |
| edit_regex_field | subject_sid | ['SubjectUserSid:({subject_sid}[^,]+)'] | ['SubjectUserSid:({subject_sid}[^,\s]+)'] |
| edit_regex_field | time | ['({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d\d\d[+-]\d\d:\d\d)\s({host}[\w\-.]+)'] | ['({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d\d\d[+-]\d\d:\d\d)\s({host}[\w\-.]+)', 'SystemTime:({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d+Z)'] |
| edit_regex_field | user_sid | ['TargetUserSid:({dest_user_sid}({user_sid}[^,]+))'] | ['TargetUserSid:({dest_user_sid}({user_sid}[^,\s]+))'] |