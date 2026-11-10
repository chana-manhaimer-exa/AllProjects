# Code Changes for symantec-endpointprotection-csv-app-activity-success-user1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['dest_host', 'domain', 'group_name', 'host', 'scan_id', 'src_ip', 'src_port', 'time', 'user'] | ['domain', 'group_name', 'host', 'scan_id', 'server', 'src_ip', 'src_port', 'time', 'user'] |
| removed_regex_field | dest_host | ['Server Name:\s*({dest_host}[^.\s,]+)'] | [] |
| added_regex_field | server | [] | ['Server Name:\s*({server}[^.\s,]+)'] |