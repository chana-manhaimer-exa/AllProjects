# Code Changes for microsoft-azurefw-json-network-session-azfwnetworkrule (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | time | ['"time":\s*"({time}\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d\.\d\d\d\d\d\d\dZ)'] | ['"TimeGenerated":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d+Z)', '"time":\s*"({time}\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d\.\d\d\d\d\d\d\dZ)'] |
| changed | Conditions | ['"AZFWNetworkRule"', '"Rule":', '"category":', 'Action":'] | ['"AZFWNetworkRule"', '"Policy":', '"Rule":', 'Action":'] |