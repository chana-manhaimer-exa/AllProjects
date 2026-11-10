# Code Changes for citrix-netscaleradc-str-app-notification-success-ipdata (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_parser | N/A | N/A | {"Name": "citrix-netscaleradc-str-app-notification-success-ipdata", "ParserVersion": "v1.0.0", "Vendor": "Citrix", "Product": "NetScaler ADC", "TimeFormat": "yyyy-MM-dd'T'HH:mm:ssZ", "Conditions": [" iprep ", " Sending IP Data ", "status"], "Fields": ["({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\dZ)\s+({host}[^\s]+)\s+({process_name}iprep)", "({additional_info}Sending IP Data\s+[^\n]+)"]} |