# Code Changes for avaya-ers-str-network-notification-success-ntp (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "avaya-ers-str-network-notification-success-ntp", "Vendor": "Avaya", "Product": "Avaya Ethernet Routing Switch", "ParserVersion": "v1.0.0", "TimeFormat": "yyyy-MM-dd HH:mm:ss", "Conditions": ["NTP:NTP synchronized"], "Fields": ["({event_name}NTP synchronized)", ":\d\d:\d\d\s+({host}[\w.-]+)\s+", "({service_name}NTP)"]} |