# Code Changes for infoblox-bddi-str-network-notification-success-failover-connect (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "infoblox-bddi-str-network-notification-success-failover-connect", "ParserVersion": "v1.0.0", "Vendor": "Infoblox", "Product": "BloxOne DDI", "TimeFormat": "MMM dd HH:mm:ss", "Conditions": ["dhcpd[", "Failover CONNECT"], "Fields": ["({time}\w+\s+\d+\s+\d+:\d+:\d+)\s+({host}[\w.-]+)\s+(?:[\d.]+\s+)?({process_name}\S+)\[({process_id}\d+)\]\:\s*", "({event_name}Failover CONNECT)"]} |