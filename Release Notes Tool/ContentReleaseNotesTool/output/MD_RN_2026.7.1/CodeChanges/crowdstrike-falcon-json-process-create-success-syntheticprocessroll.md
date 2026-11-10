# Code Changes for crowdstrike-falcon-json-process-create-success-syntheticprocessroll (Parser)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| edit_regex_field | additional_info | ['"FalconHostLink\\*"+:\s*\\*"+({additional_info}({falcon_host_link}[^"]+))', 'exa_json_path=$.message,exa_regex="FalconHostLink\\*"+:\s*\\*"+({additional_info}({falcon_host_link}[^"]+))'] | ['"EnvironmentVariablesString":"({additional_info}[^"]+)"', '"FalconHostLink\\*"+:\s*\\*"+({additional_info}({falcon_host_link}[^"]+))', 'exa_json_path=$.message,exa_regex="FalconHostLink\\*"+:\s*\\*"+({additional_info}({falcon_host_link}[^"]+))'] |