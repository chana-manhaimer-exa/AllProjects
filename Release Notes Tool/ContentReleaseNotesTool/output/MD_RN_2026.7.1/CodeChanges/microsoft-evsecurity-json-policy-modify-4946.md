# Code Changes for microsoft-evsecurity-json-policy-modify-4946 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['channel', 'domain', 'event_code', 'event_name', 'host', 'log_source', 'login_id', 'rule', 'rule_id', 'src_domain', 'src_ip', 'src_port', 'src_user', 'tenant_id', 'time', 'user', 'user_sid'] | ['channel', 'domain', 'event_code', 'event_name', 'host', 'log_source', 'login_id', 'opcode', 'process_id', 'result', 'rule', 'rule_id', 'run_level', 'src_domain', 'src_ip', 'src_port', 'src_user', 'tenant_id', 'thread_id', 'time', 'user', 'user_sid'] |
| edit_regex_field | rule | ['<Data Name\\*=\\*(\'|")RuleName\\*(\'|")>({rule}[^<]+)'] | ['"RuleName":"({rule}[^"]+)"', '<Data Name\\*=\\*(\'|")RuleName\\*(\'|")>({rule}[^<]+)'] |
| edit_regex_field | rule_id | ['<Data Name\\*=\\*(\'|")RuleId\\*(\'|")>({rule_id}[^<]+)'] | ['"RuleId":"\{({rule_id}[^"]+)\}"', '<Data Name\\*=\\*(\'|")RuleId\\*(\'|")>({rule_id}[^<]+)'] |
| added_regex_field | opcode | [] | ['"Opcode":"({opcode}[^"]+)"'] |
| added_regex_field | process_id | [] | ['"ProcessID":"({process_id}[^"]+)"'] |
| added_regex_field | result | [] | ['"Keywords":"({result}[^"]+)"'] |
| added_regex_field | run_level | [] | ['"Level":"({run_level}[^"]+)"'] |
| added_regex_field | thread_id | [] | ['"ThreadID":"({thread_id}[^"]+)"'] |
| changed | Conditions | ['"EventID":"4946"', 'A change has been made to Windows Firewall exception list', 'Microsoft-Windows-Security-Auditing'] | ['"EventID":"4946"', 'Microsoft-Windows-Security-Auditing'] |