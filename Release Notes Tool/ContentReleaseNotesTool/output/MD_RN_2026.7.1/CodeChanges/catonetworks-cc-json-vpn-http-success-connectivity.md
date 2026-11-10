# Code Changes for catonetworks-cc-json-vpn-http-success-connectivity (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | activity_type | ['vpn-login:success'] | ['vpn-login:fail', 'vpn-login:success'] |
| edit_attribute | legacy_activity_type | ['vpn-connection'] | ['failed-vpn-login', 'vpn-connection'] |