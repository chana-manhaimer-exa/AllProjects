# Code Changes for UID to User Lookup (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-user_id-user", "DisplayName": "UID to User Lookup", "Description": "Enrich user from UID", "Condition": "exists(user) && HasContextKey('UID User',user)", "Map": [{"Field": "user_id", "Value": "user"}, {"Field": "user", "Value": "toLower(GetContextAttribute('UID User',user,'u_user'))"}]} |