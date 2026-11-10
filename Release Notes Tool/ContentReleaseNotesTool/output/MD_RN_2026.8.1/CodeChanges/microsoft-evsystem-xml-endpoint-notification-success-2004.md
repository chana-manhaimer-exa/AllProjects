# Code Changes for microsoft-evsystem-xml-endpoint-notification-success-2004 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ | ["yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ"] |
| edit_regex_field | time | ['<TimeCreated SystemTime=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d{9}Z)'] | ['<TimeCreated SystemTime=(\'|")({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d{7,9}Z)'] |