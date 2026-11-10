# Code Changes for symantec-endpointprotection-kv-alert-trigger-success-requestedaction (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'additional_info', 'alert_name', 'alert_severity', 'alert_type', 'bytes', 'dest_host', 'dest_ip', 'dest_port', 'file_hash', 'hash_md5', 'hash_sha1', 'hash_sha256', 'hash_type', 'host', 'malware_url', 'process_name', 'src_host', 'src_ip', 'src_port', 'time', 'user'] | ['action', 'additional_info', 'alert_name', 'alert_severity', 'alert_type', 'bytes', 'dest_host', 'dest_ip', 'dest_port', 'file_hash', 'hash_md5', 'hash_sha1', 'hash_sha256', 'hash_type', 'host', 'malware_url', 'process_name', 'server', 'src_host', 'src_ip', 'src_port', 'time', 'user'] |
| added_regex_field | server | [] | [',Server Name:\s*({server}[^,]+)', ',Server:\s*({server}[^,]+)'] |