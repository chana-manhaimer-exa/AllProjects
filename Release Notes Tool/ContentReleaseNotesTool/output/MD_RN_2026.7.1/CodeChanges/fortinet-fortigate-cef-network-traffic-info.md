# Code Changes for fortinet-fortigate-cef-network-traffic-info (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['action', 'additional_info', 'bytes_in', 'bytes_out', 'category', 'dest_country', 'dest_host', 'dest_interface', 'dest_ip', 'dest_port', 'event_code', 'event_name', 'host', 'network_app', 'policy_name', 'protocol', 'src_country', 'src_host', 'src_interface', 'src_ip', 'src_port', 'status_msg', 'sub_category', 'time', 'tz', 'user'] | ['action', 'additional_info', 'bytes_in', 'bytes_out', 'category', 'dest_country', 'dest_host', 'dest_interface', 'dest_ip', 'dest_port', 'dns_query_type', 'event_code', 'event_name', 'host', 'network_app', 'operation_type', 'policy_name', 'protocol', 'qclass', 'query', 'src_country', 'src_host', 'src_interface', 'src_ip', 'src_port', 'status_msg', 'sub_category', 'time', 'tz', 'user'] |
| edit_regex_field | protocol | ['\Wproto=({protocol}\w+)'] | ['\Wproto=({protocol}.+?)(\s+[\w\.]+=|\s*$)', '\Wproto=({protocol}\w+)'] |
| added_regex_field | dns_query_type | [] | ['\Wqtype=({dns_query_type}.+?)(\s+[\w\.]+=|\s*$)'] |
| added_regex_field | operation_type | [] | ['\Weventtype=({operation_type}.+?)(\s+[\w\.]+=|\s*$)'] |
| added_regex_field | qclass | [] | ['\Wqclass=({qclass}.+?)(\s+[\w\.]+=|\s*$)'] |
| added_regex_field | query | [] | ['\Wqname=({query}.+?)(\s+[\w\.]+=|\s*$)'] |