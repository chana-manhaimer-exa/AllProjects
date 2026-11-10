# Code Changes for microsoft-json-events (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd HH:mm:ss Z | yyyy-MM-dd HH:mm:ss Z |
| edit_regex_field | event_name | ['exa_json_path=$.Message,exa_regex=(S-[^"]+|({event_name}[^\."]+))'] | ['exa_json_path=$..Message,exa_regex=(S-[^"]+|({event_name}[^\."]+))'] |