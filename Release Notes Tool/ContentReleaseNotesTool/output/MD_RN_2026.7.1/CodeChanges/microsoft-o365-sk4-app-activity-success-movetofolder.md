# Code Changes for microsoft-o365-sk4-app-activity-success-movetofolder (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | time | ['"CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)"', 'timestamp="({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d)'] | ['"CreationTime":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d(\.\d+)?Z?)"', 'timestamp="({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d(\.\d+)?Z?)"'] |