# Code Changes for microsoft-evsecurity-xml-scheduled-task-modify-4702-2 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | ["yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"] |
| edit_regex_field | time | ['<TimeCreated SystemTime(\\)?=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)'] | ['<TimeCreated SystemTime(\\)?=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)', '<TimeCreated SystemTime(\\)?=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d{7,9}Z)'] |