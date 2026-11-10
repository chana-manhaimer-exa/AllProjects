# Code Changes for microsoft-evsecurity-sk4-ds-object-create-success-5137 (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed_parsed_fields | N/A | ['app', 'channel', 'domain', 'ds_object_dn', 'event_code', 'event_name', 'group_name', 'host', 'login_id', 'object_type', 'src_domain', 'src_user', 'tenant_id', 'time', 'user', 'user_sid'] | ['app', 'channel', 'domain', 'ds_object_dn', 'ds_object_type', 'event_code', 'event_name', 'group_name', 'host', 'login_id', 'src_domain', 'src_user', 'tenant_id', 'time', 'user', 'user_sid'] |
| removed_regex_field | object_type | ['<Data Name\\?=\\?"ObjectClass\\?">({object_type}[^<]+?)<\/Data>'] | [] |
| added_regex_field | ds_object_type | [] | ['<Data Name\\?=\\?"ObjectClass\\?">({ds_object_type}[^<]+?)<\/Data>'] |