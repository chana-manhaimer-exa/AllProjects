# Code Changes for symantec-endpointprotection-kv-network-traffic-fail-rule (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'dest_host', 'dest_ip', 'dest_mac', 'dest_port', 'direction', 'domain', 'email_address', 'hash_md5', 'hash_sha256', 'host', 'process_dir', 'process_name', 'process_path', 'protocol', 'rule', 'src_host', 'src_ip', 'src_mac', 'src_port', 'time', 'user'] | ['action', 'dest_host', 'dest_ip', 'dest_mac', 'dest_port', 'direction', 'domain', 'email_address', 'hash_md5', 'hash_sha256', 'host', 'process_dir', 'process_name', 'process_path', 'protocol', 'rule', 'server', 'src_ip', 'src_mac', 'src_port', 'time', 'user'] |
| edit_regex_field | host | ['\d\d:\d\d:\d\d (({host}[\w-]+)\s+)?SymantecServer: (({=host}[\w-]+),)?({src_host}[^,]+),Local Host IP:'] | ['\d\d:\d\d:\d\d ({server}[\w-]+)\s+SymantecServer: ({host}[\w-]+),Local Host IP:', '\d\d:\d\d:\d\d SymantecServer:\s*({server}[^,]+),({host}[\w-]+),Local Host IP:'] |
| removed_regex_field | src_host | ['\d\d:\d\d:\d\d (({host}[\w-]+)\s+)?SymantecServer: (({=host}[\w-]+),)?({src_host}[^,]+),Local Host IP:'] | [] |
| added_regex_field | server | [] | ['\d\d:\d\d:\d\d ({server}[\w-]+)\s+SymantecServer: ({host}[\w-]+),Local Host IP:', '\d\d:\d\d:\d\d SymantecServer:\s*({server}[^,]+),({host}[\w-]+),Local Host IP:'] |