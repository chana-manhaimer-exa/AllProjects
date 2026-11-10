# Code Changes for amazon-awscloudtrail-json-app-login-awsconsolesignin (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | user_agent | ['"userAgent"+\s*:\s*"({user_agent}[^"]+)"'] | ['"userAgent"+\s*:\s*"\[?({user_agent}(?=[^"]*\/?\d\.).+?)\]?"'] |