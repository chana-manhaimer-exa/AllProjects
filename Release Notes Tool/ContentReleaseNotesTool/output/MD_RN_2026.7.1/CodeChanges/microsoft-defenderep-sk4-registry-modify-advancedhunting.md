# Code Changes for microsoft-defenderep-sk4-registry-modify-advancedhunting (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | activity_type | ['registry-modify:success'] | ['app-activity:success', 'registry-create:success', 'registry-delete:success', 'registry-modify:success', 'registry-rename:success'] |
| edit_attribute | legacy_activity_type | ['registry-write'] | ['app-activity', 'registry-write'] |