# Code Changes for Destination Email to Destination User Enricher-1 (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-dest_user-1", "DisplayName": "Destination Email to Destination User Enricher-1", "Description": "Enrich dest_user from dest_email_address field", "Condition": "exists(dest_email_address) && !exists(dest_user) && HasContextKey('Email User', toLower(dest_email_address))", "Map": [{"Field": "dest_user", "Value": "ToLower(GetContextAttribute('Email User', toLower(dest_email_address), 'u_user'))"}]} |