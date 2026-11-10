# Code Changes for microsoft-azurefw-json-network-session-azfwapplicationrule (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | time | ['"time":\s*"({time}\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d\.\d\d\d\d\d\d\dZ)'] | ['"TimeGenerated":\s*"({time}\d\d\d\d-\d\d-\d\dT\d\d:\d\d:\d\d\.\d+Z)', '"time":\s*"({time}\d\d\d\d\-\d\d\-\d\dT\d\d:\d\d:\d\d\.\d\d\d\d\d\d\dZ)'] |
| changed | Conditions | ['"AZFWApplicationRule"', '"Rule":', '"category":', 'Action":'] | ['"AZFWApplicationRule"', '"Policy":', '"Rule":', 'Action":'] |