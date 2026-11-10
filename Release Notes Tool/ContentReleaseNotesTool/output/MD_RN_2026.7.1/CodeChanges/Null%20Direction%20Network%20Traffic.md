# Code Changes for Null Direction Network Traffic (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-direction-3", "DisplayName": "Null Direction Network Traffic", "Description": "Enrich direction for network traffic as 'null' when conditions are met", "Filter": {"universalFieldName": "platform", "universalFieldValues": ["Network"]}, "Condition": "exists(src_ip) && src_network_type = 'LAN' && exists(dest_ip) && dest_network_type = 'LAN'", "Map": [{"Field": "direction", "Value": "''"}]} |