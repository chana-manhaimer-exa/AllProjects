# Code Changes for unix-unix-kv-process-create-success-execve (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | event_name | ['audit\[\d+\]:\s*(({event_name}USER_\S+)|({operation_type}\S+))', 'type=(?:({event_name}USER_\S+)|({operation_type}\S+))'] | ['"message":\s*"({event_name}USER_\S+)', 'audit\[\d+\]:\s*(({event_name}USER_\S+)|({operation_type}\S+))', 'type=(?:({event_name}USER_\S+)|({operation_type}\S+))'] |
| edit_attribute | activity_type | ['process-create:success'] | ['file-copy:success', 'file-delete:success', 'file-move:success', 'folder-delete:success', 'process-create:success'] |
| edit_attribute | legacy_activity_type | ['process-created'] | ['file-delete', 'file-write', 'process-created'] |
| edit_attribute | legacy_product | ['Unix'] | ['UNIX', 'Unix'] |