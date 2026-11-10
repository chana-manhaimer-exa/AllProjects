# Code Changes for oracle-db-mix-database-query-success-audit (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account', 'db_id', 'db_operation', 'db_query', 'db_user', 'dest_user', 'host', 'privileges', 'time', 'user'] | ['account', 'db_id', 'db_operation', 'db_query', 'db_user', 'dest_user', 'event_code', 'host', 'privileges', 'protocol', 'result_code', 'session_id', 'src_host', 'src_ip', 'src_port', 'terminal', 'time', 'user'] |
| edit_regex_field | db_operation | ['ACTION NUMBER:\[\d+\]\s+\'({db_operation}\d{1,3})\'"?,', "ACTION\s+:\[\d+\]\s+'({db_operation}alter \w+)", "ACTION\s+:\[\d+\]\s+'({db_operation}grant \w+)", "ACTION\s+:\[\d+\]\s+'({db_operation}revoke \w+)", "ACTION\s+:\[\d+\]\s+'({db_query}({db_operation}\w+)\s*.*?)\s*'\s+DATABASE USER:"] | ["ACTION\s+:\[\d+\]\s+'({db_operation}alter \w+)", "ACTION\s+:\[\d+\]\s+'({db_operation}grant \w+)", "ACTION\s+:\[\d+\]\s+'({db_operation}revoke \w+)", "ACTION\s+:\[\d+\]\s+'({db_query}({db_operation}\w+)\s*.*?)\s*'\s+DATABASE USER:"] |
| added_regex_field | event_code | [] | ['ACTION NUMBER:\[\d+\]\s+\'({event_code}\d{1,3})\'"?'] |
| added_regex_field | protocol | [] | ["CLIENT ADDRESS:\[\d+\]\s+'\(ADDRESS=\(PROTOCOL=({protocol}\w+)\)\(HOST=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))\)\(PORT=({src_port}\d+)\)\)'"] |
| added_regex_field | result_code | [] | ["STATUS:\[\d+\]\s+'({result_code}\d+)'"] |
| added_regex_field | session_id | [] | ["SESSIONID:\[\d+\]\s+'({session_id}\d+)'"] |
| added_regex_field | src_host | [] | ["USERHOST:\[\d+\]\s+'({src_host}[\w\-.]+)'"] |
| added_regex_field | src_ip | [] | ["CLIENT ADDRESS:\[\d+\]\s+'\(ADDRESS=\(PROTOCOL=({protocol}\w+)\)\(HOST=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))\)\(PORT=({src_port}\d+)\)\)'"] |
| added_regex_field | src_port | [] | ["CLIENT ADDRESS:\[\d+\]\s+'\(ADDRESS=\(PROTOCOL=({protocol}\w+)\)\(HOST=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))\)\(PORT=({src_port}\d+)\)\)'"] |
| added_regex_field | terminal | [] | ["CLIENT TERMINAL:\[\d+\]\s+'({terminal}[^']+)'"] |