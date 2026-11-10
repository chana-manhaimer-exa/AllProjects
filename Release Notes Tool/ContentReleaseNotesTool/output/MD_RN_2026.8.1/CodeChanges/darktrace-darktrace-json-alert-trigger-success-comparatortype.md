# Code Changes for darktrace-darktrace-json-alert-trigger-success-comparatortype (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'alert_name', 'alert_severity', 'alert_type', 'dest_ip', 'dest_port', 'malware_url', 'os', 'src_host', 'src_ip', 'src_mac', 'src_port', 'time'] | ['action', 'additional_info', 'alert_name', 'alert_severity', 'alert_type', 'category', 'dest_ip', 'dest_port', 'malware_url', 'original_risk_score', 'os', 'risk_score', 'src_host', 'src_ip', 'src_mac', 'src_port', 'time', 'users'] |
| added_regex_field | action | [] | ['"action":"({action}[^"]+)'] |
| added_regex_field | category | [] | ['"category":"({category}[^"]+)'] |
| added_regex_field | original_risk_score | [] | ['"score":({original_risk_score}\d+)'] |
| added_regex_field | risk_score | [] | ['"percentScore":({risk_score}\d+)'] |
| added_regex_field | users | [] | ['"credentials":({users}[^\]:]+\]?),'] |