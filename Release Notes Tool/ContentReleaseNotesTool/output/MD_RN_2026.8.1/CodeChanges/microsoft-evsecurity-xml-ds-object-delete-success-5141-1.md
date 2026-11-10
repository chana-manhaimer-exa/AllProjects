# Code Changes for microsoft-evsecurity-xml-ds-object-delete-success-5141-1 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['channel', 'domain', 'ds_object_dn', 'event_code', 'event_name', 'host', 'login_id', 'object_type', 'run_level', 'src_domain', 'src_ip', 'src_port', 'src_user', 'tenant_id', 'time', 'user', 'user_sid'] | ['channel', 'domain', 'ds_object_dn', 'ds_object_type', 'event_code', 'event_name', 'host', 'login_id', 'run_level', 'src_domain', 'src_ip', 'src_port', 'src_user', 'tenant_id', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['<Data Name\\?=\\?"ObjectClass\\?">(|({object_type}[^<]+))<\/Data>'] | [] |
| added_regex_field | ds_object_type | [] | ['<Data Name\\?=\\?"ObjectClass\\?">(|({ds_object_type}[^<]+))<\/Data>'] |