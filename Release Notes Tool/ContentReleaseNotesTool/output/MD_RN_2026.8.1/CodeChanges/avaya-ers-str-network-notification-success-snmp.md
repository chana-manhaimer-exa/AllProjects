# Code Changes for avaya-ers-str-network-notification-success-snmp (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "avaya-ers-str-network-notification-success-snmp", "Vendor": "Avaya", "Product": "Avaya Ethernet Routing Switch", "ParserVersion": "v1.0.0", "TimeFormat": "yyyy-MM-dd'T'HH:mm:ssZ", "Conditions": ["SNMP:Link", "Unit/Port:"], "Fields": ["({time}\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}[+-]\d{2}:\d{2})", "({additional_info}({service_name}SNMP):Link .+?)\s*$", "({event_name}Link (Up|Down))", ":\d\d[+-]\d\d:\d\d\s+({host}[\w.-]+)\s+"]} |