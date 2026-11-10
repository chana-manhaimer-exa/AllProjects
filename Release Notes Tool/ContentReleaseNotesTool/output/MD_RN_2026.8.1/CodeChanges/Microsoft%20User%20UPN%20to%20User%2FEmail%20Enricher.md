# Code Changes for Microsoft User UPN to User/Email Enricher (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | Condition | exists(user_upn) && !exists(user) && HasContextKey('Email User', user_upn) | exists(user_upn) && !exists(user) && HasContextKey('Email User', toLower(user_upn)) |
| edit_attribute | Map | [ConfigTree([('Field', 'email_address'), ('Value', 'user_upn')]), ConfigTree([('Field', 'user'), ('Value', "ToLower(GetContextAttribute('Email User', user_upn, 'u_user'))")])] | [ConfigTree([('Field', 'email_address'), ('Value', 'toLower(user_upn)')]), ConfigTree([('Field', 'user'), ('Value', "ToLower(GetContextAttribute('Email User', toLower(user_upn), 'u_user'))")])] |