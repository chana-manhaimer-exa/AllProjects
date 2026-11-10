# Code Changes for snowflake-s-sk4-database-query-success-queryhistory (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| COULD_NOT_COMPARE | TimeFormat | yyyy-MM-dd'T'HH:mm:ss | ["yyyy-MM-dd'T'HH:mm:ss", 'yyyy-MM-dd HH:mm:ss.SSS'] |
| edit_regex_field | time | ['"START_TIME":"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)'] | ['"START_TIME":"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)', '"START_TIME":"({time}\d\d\d\d-\d\d-\d\d\s\d\d:\d\d:\d\d\.\d\d\d)'] |