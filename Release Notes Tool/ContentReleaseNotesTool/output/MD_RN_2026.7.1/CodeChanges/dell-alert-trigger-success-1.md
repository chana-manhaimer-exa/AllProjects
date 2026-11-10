# Code Changes for dell-alert-trigger-success-1 (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'dell-sw-kv-alert-trigger-success-security') && !InList(message_id, '82', '83', '177', '608', '609', '904', '1198', '1369','1154','23','1179') && toLower(action)='drop' | InList(type, 'dell-sw-kv-alert-trigger-success-security') && !InList(message_id, '82', '83', '177', '608', '609', '904', '1198','1199', '1369','1154','1179','941','945','983','23','30','32','33','171','1370') |