# Code Changes for dell-vpn-session-success (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'dell-sw-kv-alert-trigger-success-security') && InList(message_id, '171') && toLower(action)!='drop' | InList(type, 'dell-sw-kv-alert-trigger-success-security') && InList(message_id, '171') |