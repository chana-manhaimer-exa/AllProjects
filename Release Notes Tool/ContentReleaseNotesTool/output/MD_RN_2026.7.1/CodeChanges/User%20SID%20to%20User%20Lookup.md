# Code Changes for User SID to User Lookup (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-user", "DisplayName": "User SID to User Lookup", "Description": "Enrich user from user_sid field", "Condition": "exists(user_sid) && !exists(user)", "Map": [{"Field": "user", "Value": "ToLower(GetContextAttribute('User SID', user_sid, 'u_user'))"}]} |