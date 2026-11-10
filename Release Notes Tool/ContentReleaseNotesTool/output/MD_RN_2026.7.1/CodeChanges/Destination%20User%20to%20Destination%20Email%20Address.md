# Code Changes for Destination User to Destination Email Address (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| removed_enricher | definition | {"Name": "all-dest_email_address-dest_user", "DisplayName": "Destination User to Destination Email Address", "Description": "Enrich Destination Email Address from Destination User field", "Condition": "exists(dest_user) && contains(replaceAll(toLower(dest_user), '^(?:[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,})$', 'found!'), 'found!')", "Map": [{"Field": "dest_email_address", "Value": "dest_user"}, {"Field": "dest_user", "Value": "''"}]} | N/A |