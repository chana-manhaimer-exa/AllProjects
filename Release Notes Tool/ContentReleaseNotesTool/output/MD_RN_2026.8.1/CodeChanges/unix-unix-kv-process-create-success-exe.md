# Code Changes for unix-unix-kv-process-create-success-exe (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | activity_type | ['app-activity:success', 'process-create:success', 'syscall-execute:fail', 'syscall-execute:success'] | ['app-activity:success', 'file-copy:fail', 'file-copy:success', 'file-delete:fail', 'file-delete:success', 'file-move:fail', 'file-move:success', 'folder-delete:fail', 'folder-delete:success', 'process-create:success', 'syscall-execute:fail', 'syscall-execute:success'] |
| edit_attribute | legacy_activity_type | ['app-activity', 'process-created', 'process-created-failed'] | ['app-activity', 'file-delete', 'file-write', 'process-created', 'process-created-failed'] |
| edit_attribute | legacy_product | ['Centrify Infrastructure Services', 'Unix'] | ['Centrify Infrastructure Services', 'UNIX', 'Unix'] |