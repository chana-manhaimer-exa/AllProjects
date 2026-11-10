# Code Changes for microsoft-evsecurity-xml-handle-request-4659 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['access', 'channel', 'dest_host', 'domain', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_id', 'object_server', 'object_type', 'operation', 'process_id', 'provider_name', 'result', 'run_level', 'thread_id', 'time', 'transaction_id', 'user', 'user_sid'] | ['access', 'channel', 'dest_host', 'domain', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'object', 'object_id', 'object_server', 'operation', 'process_id', 'provider_name', 'result', 'run_level', 'thread_id', 'time', 'transaction_id', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['Object Type:\s*({object_type}\S+)\s*'] | [] |
| added_regex_field | ds_object_type | [] | ['Object Type:\s*({ds_object_type}\S+)\s*'] |