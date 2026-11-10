# Code Changes for auth0-a-json-app-authentication-success-startauth (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | user_agent | ['user_agent"+:"+({user_agent}[^"]+)'] | ['exa_json_path=$..user_agent,exa_regex=\[?({user_agent}(?=[^"]*\/?\d\.).+?)\]?$', 'exa_json_path=$.data..request.userAgent,exa_regex=\[?({user_agent}(?=[^"]*\/?\d\.).+?)\]?$', 'user_agent"+:"+\[?({user_agent}(?=[^"]*\/?\d\.).+?)\]?"'] |