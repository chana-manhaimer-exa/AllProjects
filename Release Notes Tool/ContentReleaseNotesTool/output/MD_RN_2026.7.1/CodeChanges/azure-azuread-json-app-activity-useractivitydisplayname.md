# Code Changes for azure-azuread-json-app-activity-useractivitydisplayname (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | operation | ['"activityDisplayName":\s*"({operation}({event_name}[^"]+))"', '"operationType":\s*"({operation}[^"]+)"'] | ['"activityDisplayName":\s*"({operation}({event_name}[^"]+))"', '"operationType":\s*"({operation}[^"]+)"', 'exa_regex=({operation}Add registered owner to device)'] |
| edit_attribute | activity_type | ['app-activity:fail', 'app-activity:success', 'configuration-delete:fail', 'configuration-delete:success', 'group-delete:fail', 'group-delete:success', 'group-member-add:fail', 'group-member-add:success', 'group-member-remove:success'] | ['app-activity:fail', 'app-activity:success', 'configuration-delete:fail', 'configuration-delete:success', 'endpoint-modify:success', 'group-delete:fail', 'group-delete:success', 'group-member-add:fail', 'group-member-add:success', 'group-member-remove:success'] |
| edit_attribute | legacy_product | ['Azure Active Directory', 'Microsoft Azure'] | ['Azure Active Directory', 'Microsoft Azure', 'Microsoft Office 365'] |
| edit_attribute | Platform | ['Azure'] | ['Azure', 'Microsoft 365'] |