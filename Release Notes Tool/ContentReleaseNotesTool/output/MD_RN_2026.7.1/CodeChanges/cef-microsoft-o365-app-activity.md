# Code Changes for cef-microsoft-o365-app-activity (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | yyyy-MM-dd'T'HH:mm:ss |
| edit_regex_field | time | ['"CreationTime\\*"+:[\s\\]*"+({time}\d+-\d+-\d+T\d+:\d+:\d+)'] | ['"CreationTime\\*"+:[\s\\]*"+({time}\d+-\d+-\d+T\d+:\d+:\d+(\.\d+)?Z?)'] |