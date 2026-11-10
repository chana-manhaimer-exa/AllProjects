# Code Changes for dl-unix-app-activity-success-2 (Event Builder)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_conditions | expression | InList(type, 'unix-unix-str-app-activity-docker','unix-unix-str-app-activity-vault') && (!exists(result) || !InList(toLower(result), 'failure','failed')) | InList(type, 'unix-unix-str-app-activity-docker','unix-unix-str-app-activity-vault','unix-unix-str-app-activity-success-localmemberrefresh','unix-unix-str-app-activity-success-dbmaintenance','unix-unix-str-app-activity-success-ipmserver-catchall') && (!exists(result) || (exists(result) && !InList(toLower(result), 'failure','failed'))) |