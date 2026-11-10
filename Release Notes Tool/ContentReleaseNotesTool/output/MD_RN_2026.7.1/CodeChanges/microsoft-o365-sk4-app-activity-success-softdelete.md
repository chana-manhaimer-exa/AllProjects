# Code Changes for microsoft-o365-sk4-app-activity-success-softdelete (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | ["yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ", "yyyy-MM-dd'T'HH:mm:ss"] |
| edit_regex_field | time | ['"CreationTime\\*"+:[\s\\]*"+({time}\d+-\d+-\d+T\d+:\d+:\d+)'] | ['"CreationTime\\*"+:[\s\\]*"+({time}\d+-\d+-\d+T\d+:\d+:\d+(\.\d+)?Z?)'] |