# Code Changes for aws-waf-json-http-session-httprequest (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_id', 'action', 'aws_account', 'browser', 'dest_host', 'dest_ip', 'dest_port', 'host', 'method', 'mime', 'os', 'protocol', 'referrer', 'rule', 'rule_id', 'src_ip', 'src_port', 'time', 'uri_path', 'uri_query', 'user', 'user_agent', 'web_domain'] | ['account_id', 'action', 'aws_account', 'browser', 'dest_host', 'dest_ip', 'dest_port', 'fingerprint', 'host', 'method', 'mime', 'os', 'protocol', 'referrer', 'rule', 'rule_id', 'src_ip', 'src_port', 'time', 'uri_path', 'uri_query', 'user', 'user_agent', 'web_domain'] |
| added_regex_field | fingerprint | [] | ['"ja3Fingerprint"+:+"({fingerprint}[^"]+)"'] |