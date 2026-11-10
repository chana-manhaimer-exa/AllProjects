# Code Changes for avaya-ers-str-network-notification-success-pol (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "avaya-ers-str-network-notification-success-pol", "Vendor": "Avaya", "Product": "Avaya Ethernet Routing Switch", "ParserVersion": "v1.0.0", "TimeFormat": "yyyy-MM-dd'T'HH:mm:ssZ", "Conditions": ["POL:PoE Port Detection", "Status:"], "Fields": ["({time}\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}[+-]\d{2}:\d{2})", "({additional_info}({service_name}POL):PoE .+?)\s*$", "({event_name}PoE Port Detection)", ":\d\d[+-]\d\d:\d\d\s+({host}[\w.-]+)\s+"]} |