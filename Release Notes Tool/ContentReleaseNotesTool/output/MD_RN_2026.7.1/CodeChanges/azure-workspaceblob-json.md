# Code Changes for azure-workspaceblob-json (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['auth_type', 'bytes_in', 'bytes_out', 'correlation_id', 'dest_host', 'email_address', 'email_domain', 'event_category', 'file_modify_time', 'file_name', 'file_path', 'operation', 'operation_name', 'operation_type', 'operation_version', 'protocol', 'referrer', 'region', 'resource_id', 'result', 'result_code', 'schema_version', 'src_ip', 'src_port', 'storage_account', 'subscription_id', 'tenant_id', 'time', 'url', 'user_agent'] | ['auth_type', 'bytes_in', 'bytes_out', 'correlation_id', 'dest_host', 'email_address', 'email_domain', 'event_category', 'file_modify_time', 'file_url', 'operation', 'operation_name', 'operation_type', 'operation_version', 'protocol', 'referrer', 'region', 'resource_id', 'result', 'result_code', 'schema_version', 'src_ip', 'src_port', 'storage_account', 'subscription_id', 'tenant_id', 'time', 'url', 'user_agent'] |
| removed_regex_field | file_name | ['"+Uri"+:\s*"+({url}({file_path}[^"]+\/({file_name}[^\?"\/]+))[^"]*|[^"]+)"+'] | [] |
| removed_regex_field | file_path | ['"+Uri"+:\s*"+({url}({file_path}[^"]+\/({file_name}[^\?"\/]+))[^"]*|[^"]+)"+'] | [] |
| edit_regex_field | url | ['"+Uri"+:\s*"+({url}({file_path}[^"]+\/({file_name}[^\?"\/]+))[^"]*|[^"]+)"+'] | ['"+Uri"+:\s*"+({url}({file_url}[^"]+\/)[^"]*|[^"]+)"+'] |
| added_regex_field | file_url | [] | ['"+Uri"+:\s*"+({url}({file_url}[^"]+\/)[^"]*|[^"]+)"+'] |