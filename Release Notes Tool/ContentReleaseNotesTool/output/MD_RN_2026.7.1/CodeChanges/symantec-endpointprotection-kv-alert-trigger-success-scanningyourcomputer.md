# Code Changes for symantec-endpointprotection-kv-alert-trigger-success-scanningyourcomputer (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'alert_name', 'alert_severity', 'alert_type', 'dest_host', 'dest_ip', 'dest_port', 'domain', 'host', 'priority', 'protocol', 'src_host', 'src_ip', 'time', 'user'] | ['additional_info', 'alert_name', 'alert_severity', 'alert_type', 'dest_host', 'dest_ip', 'dest_port', 'domain', 'host', 'priority', 'protocol', 'server', 'src_host', 'src_ip', 'time', 'user'] |
| edit_regex_field | host | ['\w+\s+\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}\s({host}[\w\.-]+)\s'] | ['SymantecServer:\s*({host}[\w\-.]+)'] |
| added_regex_field | server | [] | ['\w+\s+\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}\s({server}[\w\.-]+)\s'] |