# Code Changes for microsoft-o365-sk4-file-write-success-filemodified (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'email_address', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'os', 'resource', 'result', 'site_name', 'src_file_dir', 'src_file_ext', 'src_file_name', 'src_ip', 'src_port', 'time', 'url', 'web_domain'] | ['app', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'email_address', 'file_dir', 'file_ext', 'file_name', 'file_path', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'os', 'resource', 'result', 'site_name', 'src_ip', 'src_port', 'time', 'url', 'user', 'web_domain'] |
| edit_regex_field | email_address | ['"(UserId|userPrincipalName)":"({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)"', 'exa_json_path=$.UserId,exa_regex=^({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)$'] | ['"(UserId|userPrincipalName)":"(({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)$|({user}[\w\.\-\!\#\^\~]{1,40}\$?))"', 'exa_json_path=$.UserId,exa_regex=^(({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)$|({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |
| removed_regex_field | src_file_dir | ['"SourceRelativeUrl":"({src_file_dir}[^"]+)"', 'exa_regex="SourceRelativeUrl":"({src_file_dir}[^"]+)"'] | [] |
| removed_regex_field | src_file_ext | ['"SourceFileExtension":"({src_file_ext}[^"]+)"', 'exa_regex="SourceFileExtension":"({src_file_ext}[^"]+)"'] | [] |
| removed_regex_field | src_file_name | [',SourceFileName":"({src_file_name}[^,]+)'] | [] |
| added_regex_field | file_dir | [] | ['"SourceRelativeUrl":"({file_dir}[^"]+)"', 'exa_regex="SourceRelativeUrl":"({file_dir}[^"]+)"'] |
| added_regex_field | file_ext | [] | ['"SourceFileExtension":"({file_ext}[^"]+)"', 'exa_regex="SourceFileExtension":"({file_ext}[^"]+)"'] |
| added_regex_field | file_name | [] | ['"SourceFileName":"({file_name}[^,]+)'] |
| added_regex_field | file_path | [] | ['"ObjectId":\s?"({file_path}[^"]+)'] |
| added_regex_field | user | [] | ['"(UserId|userPrincipalName)":"(({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)$|({user}[\w\.\-\!\#\^\~]{1,40}\$?))"', 'exa_json_path=$.UserId,exa_regex=^(({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)$|({user}[\w\.\-\!\#\^\~]{1,40}\$?))'] |