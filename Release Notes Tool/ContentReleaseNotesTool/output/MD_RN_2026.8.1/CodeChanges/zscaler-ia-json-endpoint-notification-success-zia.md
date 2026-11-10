# Code Changes for zscaler-ia-json-endpoint-notification-success-zia (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| changed | Conditions | ['"adminid":"', '"auditlogtype":"ZIA"', '"category":"', '"interface":"'] | ['"adminid":"', '"auditlogtype":', '"category":"', '"interface":"'] |
| edit_regex_field | src_ip | ['exa_json_path=$.clientip,exa_regex=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))(:({src_port}\d+))?'] | ['exa_json_path=$..clientip,exa_regex=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))(:({src_port}\d+))?'] |
| edit_regex_field | src_port | ['exa_json_path=$.clientip,exa_regex=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))(:({src_port}\d+))?'] | ['exa_json_path=$..clientip,exa_regex=({src_ip}((([0-9a-fA-F.]{0,4}):{1,2}){1,7}([0-9a-fA-F]){0,4})|(((25[0-5]|(2[0-4]|1\d|[0-9]|)\d)\.?\b){4}))(:({src_port}\d+))?'] |
| edit_attribute | activity_type | ['endpoint-notification:success'] | ['app-login:fail', 'app-login:success', 'app-logout:fail', 'app-logout:success', 'app-notification:success'] |
| edit_attribute | legacy_activity_type | [] | ['app-activity', 'app-login', 'failed-app-login'] |
| edit_attribute | legacy_product | [] | ['Zscaler'] |