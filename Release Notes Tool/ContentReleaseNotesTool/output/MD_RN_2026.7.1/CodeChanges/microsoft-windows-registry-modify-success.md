# Code Changes for microsoft-windows-registry-modify-success (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'microsoft-defenderep-sk4-registry-modify-advancedhunting') | InList(type, 'microsoft-defenderep-sk4-registry-modify-advancedhunting') && InList(toLower(event_name), 'registryvalueset')  |