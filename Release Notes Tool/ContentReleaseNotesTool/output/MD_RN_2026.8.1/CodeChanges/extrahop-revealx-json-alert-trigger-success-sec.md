# Code Changes for extrahop-revealx-json-alert-trigger-success-sec (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'alert_name', 'alert_severity', 'alert_type', 'dest_host', 'dest_ip', 'dest_port', 'dns_query', 'domain', 'host', 'original_risk_score', 'src_host', 'src_ip', 'src_port', 'status_msg', 'sub_domain', 'time'] | ['additional_info', 'alert_name', 'alert_severity', 'alert_type', 'dest_host', 'dest_ip', 'dest_port', 'dns_query', 'domain', 'host', 'original_risk_score', 'src_host', 'src_ip', 'src_port', 'status_msg', 'time'] |
| removed_regex_field | sub_domain | ['"netbiosName":(null|"({sub_domain}[^"]+))'] | [] |