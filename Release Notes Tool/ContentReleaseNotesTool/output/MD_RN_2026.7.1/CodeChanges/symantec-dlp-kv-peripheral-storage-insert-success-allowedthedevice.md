# Code Changes for symantec-dlp-kv-peripheral-storage-insert-success-allowedthedevice (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['dest_host', 'dest_ip', 'dest_port', 'device_class', 'device_id', 'device_pid', 'device_vid', 'domain', 'host', 'operation', 'operation_details', 'time', 'user'] | ['dest_host', 'dest_ip', 'dest_port', 'device_class', 'device_id', 'device_pid', 'device_vid', 'domain', 'host', 'operation', 'operation_details', 'server', 'time', 'user'] |
| added_regex_field | server | [] | ['\w+\s+\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}\s({server}[\w\.-]+)\s'] |