# Code Changes for unix-unix-kv-service-stop-success-servicestop (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | event_name | ['audit\[\d+\]:\s*(({event_name}USER_\S+)|({operation_type}\S+))', 'type=(?:({event_name}USER_\S+)|({operation_type}\S+))'] | ['"message":\s*"({event_name}USER_\S+)', 'audit\[\d+\]:\s*(({event_name}USER_\S+)|({operation_type}\S+))', 'type=(?:({event_name}USER_\S+)|({operation_type}\S+))'] |