# Code Changes for microsoft-o365-json-user-permission-modify-success-adddelegatedpermission (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | event_name | ['"Operation":"({event_name}({operation}[^"]+))"'] | ['"({event_name}Add ({operation}delegated) permission grant)'] |
| edit_regex_field | operation | ['"Operation":"({event_name}({operation}[^"]+))"'] | ['"({event_name}Add ({operation}delegated) permission grant)', 'exa_regex=({operation}delegated)'] |