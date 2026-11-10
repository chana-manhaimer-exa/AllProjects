# Code Changes for unix-syscall-execute-fail-3 (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'unix-unix-kv-process-create-success-exe') AND result!='yes' | InList(type, 'unix-unix-kv-process-create-success-exe') && !InList(toLower(process_name), 'rm', 'rmdir', 'cp', 'mv') && result!='yes' |