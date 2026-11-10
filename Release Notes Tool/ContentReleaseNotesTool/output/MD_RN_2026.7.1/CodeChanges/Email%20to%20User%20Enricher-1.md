# Code Changes for Email to User Enricher-1 (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-user-1", "DisplayName": "Email to User Enricher-1", "Description": "Enrich user from email_address field", "Condition": "exists(email_address) && !exists(user) && HasContextKey('Email User',toLower(email_address))", "Map": [{"Field": "user", "Value": "ToLower(GetContextAttribute('Email User', toLower(email_address), 'u_user'))"}]} |