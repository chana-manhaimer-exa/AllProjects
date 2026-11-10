# Code Changes for microsoft-defenderep-json-alert-trigger-success-dlprulematch-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | ["yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS"] |
| edit_regex_field | time | ['"CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)', 'exa_regex="CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)'] | ['"CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d(?:\.\d+)?Z?)', 'exa_regex="CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)'] |