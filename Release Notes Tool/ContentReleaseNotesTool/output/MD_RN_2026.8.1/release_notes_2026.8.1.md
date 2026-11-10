# Content Package 2026.8.1

These release notes contain information about content package 2026.8.1, released on 18 Aug 2026.


## Enhancements
- Added new parser for Egnyte logs: egnyte-e-sk4-app-activity-success-catchall. Updated time format extraction for parser: egnyte-e-sk4-file-create-success-filesystem.
- Added new parser for Amazon AWS Shield logs: amazon-awsshield-mix-alert-trigger-success-attackmitigation.
- Updated query field extractions for parser: fortinet-fortigate-kv-network-traffic-logid.
- Added new parsers for Avaya Ethernet Routing Switch logs: avaya-ers-str-network-notification-success-pol, avaya-ers-str-network-notification-success-ntp, avaya-ers-str-network-notification-success-snmp.
- Updated parser conditions for: amazon-s3-str-http-session-success-accesslog.
- Added new parsers for Symantec Endpoint Protection logs: symantec-endpointprotection-kv-app-activity-renamed, symantec-endpointprotection-kv-app-activity-adserver.
- Added new event builders for parsers: unix-unix-kv-process-create-success-execve, unix-unix-kv-process-create-success-exe.
- Updated code42-incydr-json-file-succes-file conditions to parse broader category of Mimecast Incydr logs.
- Moved parser fortinet-fortigate-kv-network-traffic-logid to low precedence.
- Added new parsers and event builders for Sophos XG Firewall logs.
- Added new parsers for Citrix NetScaler ADC logs.
- Updated parser category mappings in product_category_descriptions.json.
- Added new parser for Google Cloud Asset Inventory logs: google-cloudassetinventory-json-app-activity-success.
- Updated result field regex for parser microsoft-exchange-csv-email-send-receive-expanded, microsoft-exchange-csv-email-send-receive-delivered and template exchange-dlp-email-alert. Updated the regex for event_code for template exchange-dlp-email-alert-2. Updated the regex for log_source field for template exchange-dlp-email-alert-2. Updated the regex for method field for template IISParsersTemplates. Updated the regex for method field for template mcafee-http-session-2.
- Added new parsers for Cursor logs.
- Added shared_conversation_id field extraction for parser: openai-chatgpt-json-ai-conversation-share-clp.
- Updated user and user_upn normalization in event enricher.
- Added new parser for Amazon AWS CloudTrail logs: amazon-awscloudtrail-json-app-activity-success-awsmcp.
- Added new parser for Parallels Secure Workspace logs: parallels-psw-json-app-activity-success.
- Added Extract subdomain from url field using extractSubDomain() function in event enricher.
- Added new parsers for VMware Aria logs: vmware-aria-kv-app-activity-success-vrops, vmware-aria-kv-app-login-vropsauthentication.
- Added new parsers for Unix logs covering application activity and configuration events.
- Added new event enricher: microsoft-access_type.
- Added new parsers for InfoBlox logs: infoblox-bddi-str-network-notification-success-ddns-cleanup infoblox-bddi-str-network-notification-success-deferred-dns-update, infoblox-bddi-str-network-notification-success-failover-connect, infoblox-bddi-str-network-notification-success-failover-disconnect, infoblox-bddi-str-network-notification-success-frequency-error, infoblox-bddi-str-network-notification-success-ntp-sync-resumed, infoblox-bddi-str-network-notification-success-unableaddreversemap.
- Updated zscaler-ia-json-endpoint-notification-success-zia conditions to parse broader category of Zscaler logs. Updated JSON path and added new event builders for parser: zscaler-ia-json-endpoint-notification-success-zia.
- Updated site_id field extractions for Exabeam parsers.
- Added new parsers for Salesforce logs: salesforce-sf-json-app-activity-success-queriedentities, salesforce-sf-json-app-authentication-logintype.
- Added new parser for Harvey AI logs: harvey-harveyai-json-audit-activity-success.
- Added new parsers and event builders for Snowflake logs: snowflake-s-json-app-login-loginhistory-1, snowflake-s-json-app-login-loginhistory-1
- The parsers weblogin-w-kv-app-notification-webactivity and fsecure-policymanager-cef-app-activity-import have been deprecated.
- The priority of the following parsers has been lowered: apc-a-str-endpoint-login-success-webuser, apc-a-str-alert-trigger-success-0004, apc-a-kv-endpoint-login-fail-smtpauthfail, apc-a-kv-email-receive-fail-reject, apc-a-str-app-logout-success-loggedout, and apc-a-str-app-login-fail-invalidcredentials.
- Updated the event builders to mark activity type authentication successful when the operation contains user.authentication.auth_via_ad_agent for the following parsers: okta-amfa-mix-app-login-success-securitycontext, okta-amfa-cef-app-login-success-appadloginsuccess, okta-amfa-cef-app-login-success-coreuserauthloginsuccess, okta-amfa-cef-app-login-fail-coreuserauthloginfailed, okta-amfa-cef-app-login-fail-appadloginbadpassword, okta-amfg-json-endpoint-login-success-authviaradius.

## Addressed Issues
- Updated email_address field extractions for parsers: microsoft-o365-json-share-link-create-success-workload, microsoft-o365-json-share-link-modify-success-workload.
- Updated dest_host and host field extractions for parser: microsoft-evsecurity-json-user-switch-success-4648.
- Updated category, score, percentScore, credentials and action field extractions for parser: darktrace-darktrace-json-alert-trigger-success-comparatortype.
- Updated the condition for Microsoft parsers: microsoft-azurefw-json-network-session-azfwnetworkrule, microsoft-azurefw-json-network-session-azfwapplicationrule, microsoft-azurefw-json-dns-request-success-azfwdnsquery
- Updated result, process_name, login_type, user_sid, user_agent, email_address, object and src_email_folder field extractions for Microsoft O365 parsers and templates.
- Updated multiple field extractions for parser: github-g-json-app-activity-success-apirequest.
- Added new parser for the github logs: github-g-json-app-activity-success-action-1. Updated mime field extraction for parser: squid-s-str-http-session-squidlog.
- Updated user_agent field extractions for parser: amazon-awscloudtrail-json-app-activity-awsapicall, auth0-a-json-app-login-success-ss, amazon-awscloudtrail-json-role-assume-success-assumerole.
- Added bytes_out field extraction for parser: google-cloudplatform-json-app-activity-success-googleapismethodname.
- Updated the app and app_id field extraction for parsers: okta-amfg-json-endpoint-login-success-authviaradius, okta-amfa-mix-app-login-success-securitycontext, okta-amfa-csv-app-login-success-securitycontext.
- Updated dest_ip and src_host field extractions for parser: symantec-fireglass-cef-http-session-url.
- Updated permissions field extractions for parser: microsoft-o365-json-recipient-permission-modify-permissionmodify.
- Added and updated process-create event builders for parsers: unix-unix-kv-process-create-success-execve, unix-unix-kv-process-create-success-exe.
- Updated process_name and parent_process_name field extractions for Netskope Security Cloud logs.
- Updated email_address field extraction for parsers: postfix-postfix-kv-email-queue, microsoft-o365-json-share-link-create-success-workload, microsoft-o365-json-share-link-modify-success-workload, microsoft-o365-json-share-link-member-add-success-workload.
- Updated dest_user field extractions for Microsoft parsers.
- Updated event_code, result_code, src_ip, src_host, src_port, session_id and protocol field extractions for parser: oracle-db-mix-database-query-success-audit.
- Updated top_domain enricher conditions.
- Updated activity-type to alert-trigger for parsers: logrhythm-netmon-rule-trigger-success ,dl-logrhythm-l-rule-trigger-success, google-workspace-rule-trigger-success.
- Updated src_host field extraction for parser: microsoft-defenderep-json-alert-trigger-success-category.
- Updated the InvalidIf filter for the dest_user field to stop excluding entries that contain '-'.
- Updated email_subject, attachment_count, email_recipients, dest_email_address, email_address, full_name, email_urls, url_count, dest_email_folder, alert_status, malware_url and result field extractions for parser: microsoft-defender365-json-alert-trigger-success-initialaccess.
- Updated vendor and product mappings for Infoblox NIOS and BDDI parsers.
- Updated event builder conditions for Netskope Security Cloud logs.
- Updated user and proxy_action field extractions for parser: symantec-bcpa-mix-http-session-deniedtcp.
- Updated bytes field extraction for parser: microsoft-o365-json-email-send-fail-publish.
- Updated src_host and host field extraction regexes for Netskope Security Cloud logs.
- Updated TimeFormat and time field extraction regexes for Windows parsers.
- Added new parsers for Cisco proxy logs: cisco-websec-csv-http-session-tcpclientrefreshmiss, cisco-websec-csv-http-session-tcpdenied, cisco-websec-csv-http-session-tcpdeniedssl, cisco-websec-csv-http-session-tcphit, cisco-websec-csv-http-session-tcpmemhit, cisco-websec-str-app-activity-info, cisco-websec-csv-http-session-none, cisco-websec-str-app-activity-warning, cisco-websec-str-endpoint-authentication-fail-critical. Updated conditions of Cisco parsers: cisco-iws-csv-http-session-tcpmissssl, cisco-iws-csv-http-session-tcprefreshhit, cisco-iws-csv-http-session-tcpmiss.
- Updated JSON path for parser: microsoft-evsecurity-json-endpoint-activity-success-catchall.

## Code Changes

For a full list of code changes, see [CodeChanges2026.8.1.html](CodeChanges2026.8.1.html).
