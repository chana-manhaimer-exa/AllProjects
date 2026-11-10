# Code Changes for sophos-xgfirewall-kv-network-traffic-success-firewallrule (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed | Conditions | ['"SFW"', 'device_name=', 'log_component="Firewall Rule', 'status="'] | ['device_name=', 'log_component="', 'log_type="Firewall"', 'status="'] |
| changed_parsed_fields | N/A | ['action', 'dest_country', 'dest_country_code', 'dest_interface', 'dest_ip', 'dest_mac', 'dest_network_zone', 'dest_port', 'dest_translated_ip', 'dest_translated_port', 'device_id', 'duration', 'email_address', 'email_domain', 'event_category', 'event_id', 'host', 'policy_name', 'priority', 'protocol', 'result', 'rule_id', 'src_country', 'src_country_code', 'src_interface', 'src_ip', 'src_mac', 'src_network_zone', 'src_port', 'src_translated_ip', 'src_translated_port', 'subtype', 'time', 'user'] | ['action', 'app', 'app_type', 'bytes_in', 'bytes_out', 'category', 'connection_id', 'dest_country', 'dest_country_code', 'dest_interface', 'dest_ip', 'dest_mac', 'dest_network_zone', 'dest_port', 'dest_translated_ip', 'dest_translated_port', 'device_id', 'duration', 'email_address', 'email_domain', 'event_category', 'event_id', 'event_subtype', 'host', 'packets_in', 'packets_out', 'policy_name', 'priority', 'protocol', 'result', 'rule', 'rule_id', 'rule_type', 'severity', 'src_country', 'src_country_code', 'src_interface', 'src_ip', 'src_mac', 'src_network_zone', 'src_port', 'src_translated_ip', 'src_translated_port', 'subtype', 'time', 'user'] |
| added_regex_field | app | [] | ['\sapp_name"="({app}[^"]+)"'] |
| added_regex_field | app_type | [] | ['\sapp_technology="({app_type}[^"]+)"'] |
| added_regex_field | bytes_in | [] | ['\sbytes_received=({bytes_in}\d+)'] |
| added_regex_field | bytes_out | [] | ['\sbytes_sent=({bytes_out}\d+)'] |
| added_regex_field | category | [] | ['\sapp_category="({category}[^"]+)"'] |
| added_regex_field | connection_id | [] | ['\scon_id="?({connection_id}\d+)"?'] |
| added_regex_field | event_subtype | [] | ['\slog_component="({event_subtype}[^"]+)"'] |
| added_regex_field | packets_in | [] | ['\spackets_received=({packets_in}\d+)'] |
| added_regex_field | packets_out | [] | ['\spackets_sent=({packets_out}\d+)'] |
| added_regex_field | rule | [] | ['\sfw_rule_name="({rule}[^"]+)"'] |
| added_regex_field | rule_type | [] | ['\sfw_rule_type="({rule_type}[^"]+)"'] |
| added_regex_field | severity | [] | ['\sseverity="({severity}[^"]+)"'] |