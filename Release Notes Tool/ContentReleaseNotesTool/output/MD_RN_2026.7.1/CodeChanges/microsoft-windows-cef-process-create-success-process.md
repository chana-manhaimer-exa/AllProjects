# Code Changes for microsoft-windows-cef-process-create-success-process (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | ["yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS"] |
| edit_regex_field | time | ['"Timestamp":"({time}\d+-\d+-\d+T\d+:\d+:\d+)'] | ['"Timestamp":"({time}\d+-\d+-\d+T\d+:\d+:\d+(\.\d+)?Z?)'] |