# Code Changes for microsoft-mssql-kv-database-login-fail-permission (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | db_name | ['database_name:({db_name}[^\s\\]+)'] | ['database_name:({db_name}[^\s\\]+?)(?:schema_name:|\s|$)'] |
| edit_regex_field | db_operation | ['(\s|\\[rnt])action_id:({db_operation}[^\s\\]+)'] | ['(\s|\\[rnt])?action_id:({db_operation}[^\s\\]+?)(?:succeeded:|\s|$)'] |