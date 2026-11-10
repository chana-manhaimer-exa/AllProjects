# Code Changes for cyberark-pam-app-activity-success-2 (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'cyberark-pam-cef-app-activity-success-catchall') && !InList(toLower(operation), 'set password', 'logoff') | InList(type, 'cyberark-pam-str-app-activity-success-connectormanagement') && !InList(toLower(action), 'create','delete','removed','edit') && !InList(toLower(log_severity), 'failure') |