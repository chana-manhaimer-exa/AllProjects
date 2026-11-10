# Code Changes for symantec-endpointprotection-kv-app-activity-adserver (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "symantec-endpointprotection-kv-app-activity-adserver", "ParserVersion": "v1.0.0", "Vendor": "Symantec", "Product": "Symantec Endpoint Protection", "TimeFormat": ["MMM dd HH:mm:ss", "MMM  d HH:mm:ss"], "Conditions": ["SymantecServer: ", ",Server Name: ", "AD Server", "down"], "Fields": ["({time}\w+\s+\d+\s+\d+:\d+:\d+)", "Server Name:\s*({server}[^,]+)", "Event Description:\s*({event_name}[^\[]+)", "path=[^\/]+\/+({dest_host}[\w\-.]+):({dest_port}\d+)", "user=({user}[\w\.\-\!\#\^\~]{1,40}\$?)", "error code:({error_code}\d+),"]} |