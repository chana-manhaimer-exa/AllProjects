# Code Changes for symantec-endpointprotection-kv-app-activity-category (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | host | ['\d\d:\d\d:\d\d\s({host}[^\s]+) SymantecServer:'] | ['SymantecServer:\s*({host}[\w\.-]+)'] |
| edit_regex_field | server | ['SymantecServer:\s*({server}[^,]+)'] | ['\d\d:\d\d:\d\d\s({server}[^\s]+) SymantecServer:'] |