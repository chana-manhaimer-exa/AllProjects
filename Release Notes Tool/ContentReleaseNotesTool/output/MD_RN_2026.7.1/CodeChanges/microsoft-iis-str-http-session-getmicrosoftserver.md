# Code Changes for microsoft-iis-str-http-session-getmicrosoftserver (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_attribute | activity_type | ['http-session:success'] | ['http-session:fail', 'http-session:success'] |
| edit_attribute | legacy_activity_type | ['web-activity-allowed'] | ['web-activity-allowed', 'web-activity-denied'] |