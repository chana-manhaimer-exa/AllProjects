# Code Changes for microsoft-evapp-json-file-close-success-327 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd HH:mm:ss Z | ['yyyy-MM-dd HH:mm:ss Z', "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"] |
| edit_regex_field | event_name | ['exa_json_path=$.Message,exa_regex=(S-[^"]+|({event_name}[^\."]+))', 'exa_regex=({event_name}The database engine detached a database)'] | ['exa_json_path=$..Message,exa_regex=(S-[^"]+|({event_name}[^\."]+))', 'exa_regex=({event_name}The database engine detached a database)'] |