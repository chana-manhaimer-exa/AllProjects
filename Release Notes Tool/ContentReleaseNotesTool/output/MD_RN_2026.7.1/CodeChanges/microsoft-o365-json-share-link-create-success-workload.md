# Code Changes for microsoft-o365-json-share-link-create-success-workload (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | target_domain | ['"TargetUserOrGroupName":"([^@]+)@({target_domain}[^"]*?)"', 'exa_json_path=$.TargetUserOrGroupName,exa_regex=([^@]+)@({target_domain}[^"]*?)'] | ['"TargetUserOrGroupName":"([^@]+)@({target_domain}[^"]*?)"', 'exa_json_path=$.TargetUserOrGroupName,exa_regex=([^@]+)@({target_domain}[^"@]+)'] |