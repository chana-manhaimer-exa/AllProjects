# Code Changes for infoblox-bddi-str-network-notification-success-ntp-sync-resumed (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "infoblox-bddi-str-network-notification-success-ntp-sync-resumed", "ParserVersion": "v1.0.0", "Vendor": "Infoblox", "Product": "BloxOne DDI", "TimeFormat": "MMM dd HH:mm:ss", "Conditions": ["monitor[", "Type: NTP Synchronization,", "The NTP service resumed synchronization"], "Fields": ["({time}\w+\s+\d+\s+\d+:\d+:\d+)\s+({host}[\w.-]+)\s+(?:[\d.]+\s+)?({process_name}\S+)\[({process_id}\d+)\]\:\s*", "\s*Type:\s*NTP\s+Synchronization,.*Event:\s*({event_name}The NTP service resumed synchronization)"]} |