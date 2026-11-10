# Code Changes for dell-network-traffic-fail (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'dell-sw-kv-alert-trigger-success-security') && InList(message_id, '23') && toLower(action)='drop' | InList(type, 'dell-sw-kv-alert-trigger-success-security') && InList(message_id,'983','23') |