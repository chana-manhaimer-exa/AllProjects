# Code Changes for Microsoft User UPN to Email Address Enricher (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| removed_enricher | definition | {"Name": "microsoft-email_address-user_upn", "DisplayName": "Microsoft User UPN to Email Address Enricher", "Description": "Enrich email_address from Microsoft user_upn (User Principal Name)", "Filter": {"universalFieldName": "vendor", "universalFieldValues": ["Microsoft"]}, "Condition": "!exists(email_address) && exists(user_upn) && contains(replaceAll(toLower(user_upn), '^(?:[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,})$', 'found!'), 'found!')", "Map": [{"Field": "email_address", "Value": "user_upn"}]} | N/A |