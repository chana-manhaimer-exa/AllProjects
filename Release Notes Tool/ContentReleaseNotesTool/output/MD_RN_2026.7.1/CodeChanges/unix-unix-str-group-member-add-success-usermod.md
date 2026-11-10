# Code Changes for unix-unix-str-group-member-add-success-usermod (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['account_name', 'dest_host', 'group_name', 'host', 'process_id', 'process_name', 'time'] | ['account_name', 'dest_host', 'dest_user', 'group_name', 'host', 'process_id', 'process_name', 'time'] |
| edit_regex_field | account_name | ["add \'({account_name}[^']+)\' to group \'({group_name}[^']+)\'"] | ["add \'({dest_user}({account_name}[^']+))\' to group \'({group_name}[^']+)\'"] |
| edit_regex_field | group_name | ["add \'({account_name}[^']+)\' to group \'({group_name}[^']+)\'"] | ["add \'({dest_user}({account_name}[^']+))\' to group \'({group_name}[^']+)\'"] |
| added_regex_field | dest_user | [] | ["add \'({dest_user}({account_name}[^']+))\' to group \'({group_name}[^']+)\'"] |