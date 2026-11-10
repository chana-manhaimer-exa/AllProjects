# Code Changes for unix-syscall-app-activity-success (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'unix-unix-kv-process-create-success-exe') AND !exists(result) | InList(type, 'unix-unix-kv-process-create-success-exe') && !exists(result) |