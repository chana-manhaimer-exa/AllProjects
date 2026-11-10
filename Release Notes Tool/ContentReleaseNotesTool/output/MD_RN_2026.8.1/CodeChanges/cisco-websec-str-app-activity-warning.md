# Code Changes for cisco-websec-str-app-activity-warning (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "cisco-websec-str-app-activity-warning", "ParserVersion": "v1.0.0", "Vendor": "Cisco", "Product": "Cisco Web Security", "TimeFormat": "MMM dd HH:mm:ss", "Conditions": [": Warning:", ">"], "Fields": ["({time}\w+\s+\d+\s+\d\d:\d\d:\d\d)\s+({host}[\w\.\-]+)\s+({log_source}[^\s:]+):\s*Warning:", "Warning:\s*({additional_info}.+$)"]} |