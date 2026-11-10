# Code Changes for dl-unix-process-create-success-1 (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'unix-unix-kv-process-create-success-execve') | InList(type, 'unix-unix-kv-process-create-success-execve') && !InList(toLower(process_name), 'rm', 'rmdir', 'cp', 'mv') |