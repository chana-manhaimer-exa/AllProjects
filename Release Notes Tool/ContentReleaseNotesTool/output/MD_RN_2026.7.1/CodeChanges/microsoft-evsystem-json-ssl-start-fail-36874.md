# Code Changes for microsoft-evsystem-json-ssl-start-fail-36874 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd HH:mm:ss Z | ['yyyy-MM-dd HH:mm:ss Z', "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"] |
| edit_regex_field | event_name | ['exa_json_path=$.Message,exa_regex=(S-[^"]+|({event_name}[^\."]+))', 'exa_regex=({event_name}The \w+ connection request has failed)'] | ['exa_json_path=$..Message,exa_regex=(S-[^"]+|({event_name}[^\."]+))', 'exa_regex=({event_name}The \w+ connection request has failed)'] |