# Code Changes for crowdstrike-falcon-json-process-create-processrollup2stats (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_name', 'aid', 'aip', 'container_id', 'dest_ip', 'dest_port', 'domain', 'event_code', 'hash_md5', 'os', 'parent_process_id', 'process_command_line', 'process_id', 'src_ip', 'src_port', 'time', 'user', 'user_sid'] | ['account_name', 'additional_info', 'aid', 'aip', 'container_id', 'dest_ip', 'dest_port', 'domain', 'event_code', 'hash_md5', 'os', 'parent_process_id', 'process_command_line', 'process_id', 'src_ip', 'src_port', 'time', 'user', 'user_sid'] |
| added_regex_field | additional_info | [] | ['"EnvironmentVariablesString":"({additional_info}[^"]+)"'] |