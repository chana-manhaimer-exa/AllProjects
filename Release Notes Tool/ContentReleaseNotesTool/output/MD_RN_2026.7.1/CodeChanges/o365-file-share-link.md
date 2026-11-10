# Code Changes for o365-file-share-link (ParserTemplate)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | target_domain | ['"TargetUserOrGroupName":"([^@]+)@({target_domain}[^"]*?)"', 'exa_json_path=$.TargetUserOrGroupName,exa_regex=([^@]+)@({target_domain}[^"]*?)'] | ['"TargetUserOrGroupName":"([^@]+)@({target_domain}[^"]*?)"', 'exa_json_path=$.TargetUserOrGroupName,exa_regex=([^@]+)@({target_domain}[^"@]+)'] |