# Code Changes for microsoft-evsecurity-json-endpoint-login-4769 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | src_host | ['"ServiceName":"({src_host}[\w\-.]+\$)"', 'exa_json_path=$..ServiceName,exa_regex=^({src_host}[\w\-.]+\$)$'] | ['"ServiceName":"({src_host}[\w\-.]+\$)"', '"TargetUserName":"({src_host}[\w\.-]+)\$(?:.*)","TargetDomainName"', 'exa_json_path=$..ServiceName,exa_regex=^({src_host}[\w\-.]+\$)$', 'exa_regex="TargetUserName":"({src_host}[\w\.-]+)\$(?:.*)","TargetDomainName"'] |