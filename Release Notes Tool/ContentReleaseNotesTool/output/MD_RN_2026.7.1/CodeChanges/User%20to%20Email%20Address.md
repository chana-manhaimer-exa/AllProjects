# Code Changes for User to Email Address (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| removed_enricher | definition | {"Name": "all-email_address-user", "DisplayName": "User to Email Address", "Description": "Enrich Email Address from user field", "Condition": "exists(user) && contains(replaceAll(toLower(user), '^(?:[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,})$', 'found!'), 'found!')", "Map": [{"Field": "email_address", "Value": "user"}, {"Field": "user", "Value": "''"}]} | N/A |