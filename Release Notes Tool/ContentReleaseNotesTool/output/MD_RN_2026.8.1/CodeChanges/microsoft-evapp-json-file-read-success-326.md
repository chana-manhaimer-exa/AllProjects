# Code Changes for microsoft-evapp-json-file-read-success-326 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['domain', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'login_id', 'operation', 'user', 'user_sid'] | ['domain', 'event_name', 'file_dir', 'file_ext', 'file_name', 'file_path', 'login_id', 'operation', 'process_id', 'process_name', 'user', 'user_sid'] |
| added_regex_field | process_id | [] | ['exa_regex="({process_name}[^\s"]+)\s(\(({process_id}\d+)\))?[^"]*?The database engine attached a database'] |
| added_regex_field | process_name | [] | ['exa_regex="({process_name}[^\s"]+)\s(\(({process_id}\d+)\))?[^"]*?The database engine attached a database'] |