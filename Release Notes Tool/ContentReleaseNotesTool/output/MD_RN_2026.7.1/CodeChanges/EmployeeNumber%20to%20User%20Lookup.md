# Code Changes for EmployeeNumber to User Lookup (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "all-employee_id-user", "DisplayName": "EmployeeNumber to User Lookup", "Description": "Enrich user from employee number", "Condition": "exists(user) && HasContextKey('ID-User',user)", "Map": [{"Field": "employee_id", "Value": "user"}, {"Field": "user", "Value": "toLower(GetContextAttribute('ID-User',user,'u_user'))"}]} |