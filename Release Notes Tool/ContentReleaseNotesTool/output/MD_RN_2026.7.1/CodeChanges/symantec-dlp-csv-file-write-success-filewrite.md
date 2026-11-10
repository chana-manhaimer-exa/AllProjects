# Code Changes for symantec-dlp-csv-file-write-success-filewrite (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['bytes', 'client', 'dest_host', 'dest_ip', 'device_class', 'device_id', 'domain', 'email_address', 'file_ext', 'file_name', 'file_path', 'operation', 'operation_details', 'process_dir', 'process_name', 'process_path', 'rule', 'time', 'user'] | ['bytes', 'dest_host', 'dest_ip', 'device_class', 'device_id', 'domain', 'email_address', 'file_ext', 'file_name', 'file_path', 'host', 'operation', 'operation_details', 'process_dir', 'process_name', 'process_path', 'rule', 'server', 'time', 'user'] |
| removed_regex_field | client | ['SymantecServer:\s*({client}[^,]+?)\s*(,|$)'] | [] |
| added_regex_field | host | [] | ['SymantecServer:\s*({host}[\w\-.]+)'] |
| added_regex_field | server | [] | ['\w+\s+\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}\s({server}[\w\.-]+)\s'] |