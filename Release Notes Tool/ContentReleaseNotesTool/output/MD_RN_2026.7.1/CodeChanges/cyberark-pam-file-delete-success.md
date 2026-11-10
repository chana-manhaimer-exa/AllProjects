# Code Changes for cyberark-pam-file-delete-success (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'cyberark-pam-cef-file-safe','cyberark-pam-leef-appactivityfile-vault', 'cyberark-pam-kv-app-activity-fileoperations') && InList(toLower(access),'delete file', 'delete file category') | InList(type, 'cyberark-pam-str-app-activity-success-privilegecloud') && InList(toLower(operation), 'delete file') |