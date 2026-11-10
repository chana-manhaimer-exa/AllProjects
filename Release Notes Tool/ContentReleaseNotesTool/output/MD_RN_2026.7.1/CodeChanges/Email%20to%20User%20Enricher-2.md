# Code Changes for Email to User Enricher-2 (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-email_address-user-2", "DisplayName": "Email to User Enricher-2", "Description": "Enrich user from email_address field", "Condition": "(!exists(email_address) && exists(user) && HasContextKey('Email User',toLower(user))) || (exists(email_address) && exists(user) && (toLower(user) = toLower(email_address)) && HasContextKey('Email User',toLower(email_address)))", "Map": [{"Field": "email_address", "Value": "user"}, {"Field": "user", "Value": "ToLower(GetContextAttribute('Email User', toLower(email_address), 'u_user'))"}]} |