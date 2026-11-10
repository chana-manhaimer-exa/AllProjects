# Code Changes for Microsoft User UPN to User/Email Enricher (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "microsoft-email_address-user", "DisplayName": "Microsoft User UPN to User/Email Enricher", "Description": "Enrich email_address and user from Microsoft user_upn (User Principal Name)", "Filter": {"universalFieldName": "vendor", "universalFieldValues": ["Microsoft"]}, "Condition": "exists(user_upn) && !exists(user) && HasContextKey('Email User', user_upn)", "Map": [{"Field": "email_address", "Value": "user_upn"}, {"Field": "user", "Value": "ToLower(GetContextAttribute('Email User', user_upn, 'u_user'))"}]} |