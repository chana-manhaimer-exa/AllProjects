# Code Changes for defender-atp-security-alert-events (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['additional_info', 'alert_id', 'alert_name', 'alert_severity', 'alert_type', 'dest_ip', 'dest_port', 'domain', 'email_address', 'email_domain', 'host', 'incident_status', 'more_info', 'result', 'src_ip', 'src_port', 'technique', 'tenant_id', 'time', 'user', 'workspace_name'] | ['additional_info', 'alert_id', 'alert_name', 'alert_severity', 'alert_type', 'dest_ip', 'dest_port', 'domain', 'email_address', 'email_domain', 'host', 'incident_status', 'more_info', 'result', 'src_host', 'src_ip', 'src_port', 'technique', 'tenant_id', 'time', 'user', 'workspace_name'] |
| edit_regex_field | host | ['"hostname":"({host}[^"]+)"', 'exa_regex="hostName":"({host}[\w\-.]+)'] | ['"hostname":"({host}[\w\-.]+)"', 'exa_regex="hostName":"({host}[\w\-.]+)'] |
| added_regex_field | src_host | [] | ['"deviceDnsName":"({src_host}[\w\-\.]+)"', 'exa_regex="deviceDnsName":"({src_host}[\w\-.]+)'] |