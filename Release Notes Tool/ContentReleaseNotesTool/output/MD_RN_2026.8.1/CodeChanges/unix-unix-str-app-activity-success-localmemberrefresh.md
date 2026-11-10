# Code Changes for unix-unix-str-app-activity-success-localmemberrefresh (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Vendor": "Unix", "Product": "Unix", "TimeFormat": "MMM dd HH:mm:ss", "Fields": ["({time}\w+\s+\d+\s+\d+:\d+:\d+)\s+({host}[\w\.\-]+)\s+({process_name}[^\[]+)\[({process_id}\d+)\]:\s*({additional_info}.+$)", ":\s*({additional_info}(\s*({severity}\w+):)?\s*\[({src_ip}(?:[0-9a-fA-F]{1,4}:){1,7}[0-9a-fA-F]{1,4}|::[0-9a-fA-F]{1,4}|(?:(?:25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d\d?))(:({src_port}\d+))?\]\s*.+$)"], "Name": "unix-unix-str-app-activity-success-localmemberrefresh", "ParserVersion": "v1.0.0", "Conditions": [" ipmserver[", "local_member_refresh"]} |