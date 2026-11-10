# Code Changes for microsoft-o365-cef-alert-trigger-success-alertdetected (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | time | ['"CreationTime":"({time}\d\d\d\d-\d\d-\d\d(T|\s)\d\d:\d\d:\d\d(\.\d{1,3})?)', '"TimeGenerated":"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\dZ)"'] | ['"CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\d \d\d:\d\d:\d\d(?:\.\d+)?Z?)', '"CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d(?:\.\d+)?Z?)', '"TimeGenerated":"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\dZ)"'] |