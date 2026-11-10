# Code Changes for Extract URL Subdomain (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-sub_domain", "DisplayName": "Extract URL Subdomain", "Description": "Extract subdomain from url field using extractSubDomain()", "Condition": "exists(url) && !exists(sub_domain)", "Map": [{"Field": "sub_domain", "Value": "extractSubDomain(url)"}]} |