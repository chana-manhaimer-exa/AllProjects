# Code Changes for Top Domain Enricher (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | Condition | exists(web_domain) && !isIP(web_domain) | exists(web_domain) && !isIP(web_domain) && contains(web_domain, '.') |