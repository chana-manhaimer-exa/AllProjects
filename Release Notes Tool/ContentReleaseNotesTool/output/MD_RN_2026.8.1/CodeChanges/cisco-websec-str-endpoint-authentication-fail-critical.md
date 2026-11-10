# Code Changes for cisco-websec-str-endpoint-authentication-fail-critical (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "cisco-websec-str-endpoint-authentication-fail-critical", "ParserVersion": "v1.0.0", "Vendor": "Cisco", "Product": "Cisco Web Security", "TimeFormat": "MMM dd HH:mm:ss", "Conditions": [": Critical:", "Authentication Service Failure"], "Fields": ["({time}\w+\s+\d+\s+\d\d:\d\d:\d\d)\s+({host}[\w\.\-]+)\s+({log_source}[^\s:]+):\s*Critical:", "Critical:\s*({additional_info}.+$)"]} |