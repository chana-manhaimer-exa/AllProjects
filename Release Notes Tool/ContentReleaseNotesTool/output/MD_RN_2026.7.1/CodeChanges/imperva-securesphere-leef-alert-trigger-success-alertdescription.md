# Code Changes for imperva-securesphere-leef-alert-trigger-success-alertdescription (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'alert_id', 'alert_name', 'alert_severity', 'alert_type', 'app', 'dest_ip', 'dest_port', 'server_group', 'src_ip', 'src_port', 'time', 'user'] | ['additional_info', 'alert_id', 'alert_name', 'alert_severity', 'alert_type', 'app', 'dest_ip', 'dest_port', 'domain', 'server_group', 'src_ip', 'src_port', 'time', 'user'] |
| edit_regex_field | user | ['(\s|\|)usrName="*({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] | ['(\s|\|)usrName="*(((?i:NT SERVICE|NT AUTHORITY)|({domain}[^\\]+))\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] |
| added_regex_field | domain | [] | ['(\s|\|)usrName="*(((?i:NT SERVICE|NT AUTHORITY)|({domain}[^\\]+))\\+)?({user}[\w\.\-\!\#\^\~]{1,40}\$?)'] |