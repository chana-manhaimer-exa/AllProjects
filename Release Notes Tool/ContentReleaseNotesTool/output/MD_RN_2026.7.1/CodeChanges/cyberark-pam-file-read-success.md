# Code Changes for cyberark-pam-file-read-success (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'cyberark-pam-cef-file-safe','cyberark-pam-leef-appactivityfile-vault', 'cyberark-pam-kv-app-activity-fileoperations') && InList(toLower(access),'open file','retrieve file', 'retrieve', 'confirm get file', 'find files') | InList(type, 'cyberark-pam-str-app-activity-success-privilegecloud') && InList(toLower(operation), 'retrieve file','open file','open file (write only)') |