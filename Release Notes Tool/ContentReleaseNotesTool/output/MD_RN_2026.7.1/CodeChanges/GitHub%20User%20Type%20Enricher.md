# Code Changes for GitHub User Type Enricher (Event Enricher)

| Code Change | Field Name | Before | After |
|-------------|------------|--------|-------|
| added_enricher | definition | N/A | {"Name": "github-user_type", "DisplayName": "GitHub User Type Enricher", "Description": "Normalize GitHub bot/user identity into user_type", "Condition": "(exists(user_type) || ((exists(user) || exists(email_address)) && !exists(user_type)))", "Filter": {"universalFieldName": "platform", "universalFieldValues": ["GitHub"]}, "Map": [{"Field": "user_type", "Value": "if(InList(toLower(user_type), 'bot', 'true'), 'bot', 'user')"}]} |