# Code Changes for symantec-endpointprotection-kv-endpoint-modify-renamed (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "symantec-endpointprotection-kv-endpoint-modify-renamed", "ParserVersion": "v1.0.0", "Vendor": "Symantec", "Product": "Symantec Endpoint Protection", "TimeFormat": ["MMM dd HH:mm:ss", "MMM  d HH:mm:ss"], "Conditions": ["SymantecServer: ", ",Server Name: ", "client computer", "renamed"], "Fields": ["({time}\w+\s+\d+\s+\d+:\d+:\d+)", "Server Name:\s*({server}[^,]+)", "Domain Name:\s*({domain}[^,]+)", "({event_name}The client computer has been renamed)", "The client computer has been renamed,\s*({src_host}[\w\-.]+),\s*({user}[\w\.\-\!\#\^\~]{1,40}\$?),\s*({src_domain}[^\s,]+)"]} |