# Code Changes for symantec-endpointprotection-kv-network-traffic-fail-block (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'additional_info', 'dest_host', 'dest_ip', 'dest_mac', 'dest_port', 'direction', 'domain', 'host', 'operation', 'protocol', 'src_host', 'src_ip', 'src_port', 'time', 'user'] | ['action', 'additional_info', 'dest_host', 'dest_ip', 'dest_mac', 'dest_port', 'direction', 'domain', 'operation', 'protocol', 'server', 'src_host', 'src_ip', 'src_port', 'time', 'user'] |
| removed_regex_field | host | ['\w+\s+\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}\s({host}[\w\.-]+)\s'] | [] |
| added_regex_field | server | [] | ['\w+\s+\d{1,2}\s\d{1,2}:\d{1,2}:\d{1,2}\s({server}[\w\.-]+)\s'] |