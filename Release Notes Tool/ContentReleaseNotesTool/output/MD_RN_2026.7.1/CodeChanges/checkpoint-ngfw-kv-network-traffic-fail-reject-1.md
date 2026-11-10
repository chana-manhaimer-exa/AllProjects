# Code Changes for checkpoint-ngfw-kv-network-traffic-fail-reject-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'app', 'category', 'dest_ip', 'dest_port', 'direction', 'domain', 'email_address', 'event_name', 'full_name', 'host', 'origin_ip', 'origin_name', 'product_name', 'protocol', 'rule_type', 'severity', 'src_host', 'src_ip', 'src_port', 'time', 'user'] | ['action', 'category', 'dest_ip', 'dest_port', 'direction', 'domain', 'email_address', 'event_name', 'full_name', 'host', 'network_app', 'origin_ip', 'origin_name', 'product_name', 'protocol', 'rule_type', 'severity', 'src_host', 'src_ip', 'src_port', 'time', 'user'] |
| removed_regex_field | app | ['appi_name:"({app}[^"]+)"'] | [] |
| added_regex_field | network_app | [] | ['appi_name:"({network_app}[^"]+)"'] |