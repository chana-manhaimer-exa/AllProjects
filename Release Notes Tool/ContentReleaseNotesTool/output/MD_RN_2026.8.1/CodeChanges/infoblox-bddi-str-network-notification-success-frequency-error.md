# Code Changes for infoblox-bddi-str-network-notification-success-frequency-error (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "infoblox-bddi-str-network-notification-success-frequency-error", "ParserVersion": "v1.0.0", "Vendor": "Infoblox", "Product": "BloxOne DDI", "TimeFormat": "MMM dd HH:mm:ss", "Conditions": ["ntpd[", "frequency error", "PPM exceeds tolerance"], "Fields": ["({time}\w+\s+\d+\s+\d+:\d+:\d+)\s+({host}[\w.-]+)\s+(?:[\d.]+\s+)?({process_name}\S+)\[({process_id}\d+)\]\:\s*", "({event_name}frequency error)"]} |