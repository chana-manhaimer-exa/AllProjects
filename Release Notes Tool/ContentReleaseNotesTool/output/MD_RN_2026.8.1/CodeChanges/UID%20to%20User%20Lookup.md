# Code Changes for UID to User Lookup (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | Condition | exists(user) && HasContextKey('UID User',user) | exists(user) && HasContextKey('UID User',toLower(user)) |
| edit_attribute | Map | [ConfigTree([('Field', 'user_id'), ('Value', 'user')]), ConfigTree([('Field', 'user'), ('Value', "toLower(GetContextAttribute('UID User',user,'u_user'))")])] | [ConfigTree([('Field', 'user_id'), ('Value', 'user')]), ConfigTree([('Field', 'user'), ('Value', "toLower(GetContextAttribute('UID User',toLower(user),'u_user'))")])] |