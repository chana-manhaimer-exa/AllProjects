# Code Changes for microsoft-o365-sk4-file-write-success-fileuploaded (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'app_id', 'auth_type', 'browser', 'category', 'connection_id', 'email_address', 'file_name', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'resource', 'result', 'site_name', 'src_file_dir', 'src_ip', 'src_port', 'time', 'url', 'user_type', 'web_domain'] | ['app', 'app_id', 'auth_type', 'browser', 'bytes', 'category', 'connection_id', 'email_address', 'email_domain', 'file_dir', 'file_ext', 'file_name', 'file_path', 'log_source', 'object', 'object_id', 'object_type', 'operation', 'resource', 'result', 'site_name', 'src_file_dir', 'src_ip', 'src_port', 'time', 'url', 'user_type', 'web_domain'] |
| edit_regex_field | email_address | ['"(UserId|userPrincipalName)":"({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)"'] | ['"(UserId|userPrincipalName)":"({email_address}[A-Za-z0-9!#$%&\'+\/=?^_`~.-]+@[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+)"', '"UserId":"({email_address}([A-Za-z0-9]+[!#$%&\'+\/=?^_`~.-])*[A-Za-z0-9]+@({email_domain}[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+))"'] |
| edit_regex_field | file_name | [',SourceFileName":"({file_name}[^,]+)'] | ['"ObjectId":"({file_path}({file_dir}[^"]+?)\\+({file_name}[^\\"]+))"', '"SourceFileName":"({file_name}[^"]+)'] |
| added_regex_field | bytes | [] | ['"FileSizeBytes":({bytes}\d+)'] |
| added_regex_field | email_domain | [] | ['"UserId":"({email_address}([A-Za-z0-9]+[!#$%&\'+\/=?^_`~.-])*[A-Za-z0-9]+@({email_domain}[^\]\s"\\,\|]+\.[^\]\s"\\,\|]+))"'] |
| added_regex_field | file_dir | [] | ['"ObjectId":"({file_path}({file_dir}[^"]+?)\\+({file_name}[^\\"]+))"', '"SourceRelativeUrl":"({file_dir}[^"]+)"'] |
| added_regex_field | file_ext | [] | ['"SourceFileExtension":"({file_ext}[^"]+)'] |
| added_regex_field | file_path | [] | ['"ObjectId":"({file_path}({file_dir}[^"]+?)\\+({file_name}[^\\"]+))"'] |