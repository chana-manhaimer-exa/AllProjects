# Code Changes for amazon-awscloudtrail-cef-app-activity-awsapicall (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | user_agent | ['"userAgent"\s*:\s*"\[?(|({user_agent}[^"]+?))\]?"'] | ['user_agent"+:"+\[?(|({user_agent}(?=[^"]*\/?\d\.).+?))\]?"'] |