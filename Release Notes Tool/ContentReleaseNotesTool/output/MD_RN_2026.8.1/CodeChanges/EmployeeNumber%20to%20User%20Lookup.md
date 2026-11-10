# Code Changes for EmployeeNumber to User Lookup (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | Condition | exists(user) && HasContextKey('ID-User',user) | exists(user) && HasContextKey('ID-User',toLower(user)) |
| edit_attribute | Map | [ConfigTree([('Field', 'employee_id'), ('Value', 'user')]), ConfigTree([('Field', 'user'), ('Value', "toLower(GetContextAttribute('ID-User',user,'u_user'))")])] | [ConfigTree([('Field', 'employee_id'), ('Value', 'user')]), ConfigTree([('Field', 'user'), ('Value', "toLower(GetContextAttribute('ID-User',toLower(user),'u_user'))")])] |