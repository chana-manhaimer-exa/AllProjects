# Content Package 2026.7.1

These release notes contain information about content package 2026.7.1, released on 15 Jul 2026.


## Enhancements
- Updated parser fortinet-fortiweb-kv-alert-trigger-success-attack condition to parse unparsed logs.
- Updated json path for parser template m365-activity. Added session_id field extraction for Microsoft 365 parsers.
- Updated src_host field extraction for parser: microsoft-nps-xml-radius-traffic-fail-6273, microsoft-nps-xml-radius-traffic-fail-6274, microsoft-evsecurity-xml-endpoint-authentication-4774, microsoft-evsecurity-kv-endpoint-authentication-4774.
- Added new parser for Microsoft Azure AD Activity logs : microsoft-azureadactivity-json-app-activity-success-aadgraphactivity.
- Updated operation field extraction for parser: microsoft-o365-json-app-modify-success-addowner. Updated target_domain field extraction for parser: microsoft-o365-json-share-link-member-add-success-workload. Updated event_type mapping for parser: microsoft-o365-sk4-app-addowner. Fixed native JSON fields extraction issue for parser: microsoft-o365-json-user-permission-modify-success-addapproleassignment, microsoft-o365-json-user-permission-modify-success-adddelegatedpermission.
- Added new parser for CrowdStrike Falcon logs: crowdstrike-falcon-json-alert-trigger-success-cloudproviderfinding.
- Added new parser for Proofpoint Email Protection logs: proofpoint-tappod-json-email-receive-success-inbound.
- Added resource_path, resource_type, operation_details, identifier and http_response_code fields for github-g-json-app-activity-success-apirequest parser.
- Added new parsers for Hillstone Web Application Firewall logs: hillstone-hwaf-json-http-traffic-policy, hillstone-hwaf-str-http-traffic-wafwebevent and hillstone-hwaf-str-network-traffic-eventnetarp.
- Added new parser hillstone-hnbds-str-alert-trigger-success-threatsecurity to  support  Hillstone BDS  logs.
- Added 24 new Microsoft EVSecurity JSON parsers covering user/group management, policy changes, network session failures, registry modifications, scheduled tasks, services, endpoint activity, and audit events. Enhanced 5 existing parsers (Event Code: 4946, 4723, 4634, 4625, and 5152) to improve event parsing accuracy, normalization, and field extraction.
- Update conditions and field extraction for parser amazon-inspector-json-alert-trigger-success-inspector.
- Added new parser for Amazon AWS CloudTrail logs: amazon-awscloudtrail-json-app-activity-success-redshift.
- Added new parser for Salesforce logs: salesforce-sf-json-app-activity-success-apianomalyeventstore.
- Mitre Mapping updated for rules using mitre tactic Defense Evasion and mapped them to Stealth and  Defense Impairment as applicable.
- Updated Category for multiple Products from Other category to a better fitting specific category. This will lead to better Outcomes Navigator coverage analysis.
- Updated additional_info field extraction for parsers - crowdstrike-falcon-json-process-create-success-processrollup, crowdstrike-falcon-json-process-create-success-processroll, crowdstrike-falcon-json-process-create-success-syntheticprocessroll, crowdstrike-falcon-json-process-create-processrollup2stats and crowdstrike-falcon-json-process-create-syntheticprocessrollup2.
- Added permissions field extraction for parsers github-g-json-app-activity-success-action and github-g-json-user-invite-success-org Added permissions field extraction for template json-github-actions-1.
- Added github-user_type enricher and added user_type field for GitHub parsers.
- Added new parser for Cybereason logs: cybereason-cr-json-alert-trigger-success-malops.
- Added new parsers for Netskope Events and Alerts logs: netskope-sc-str-events-alerts-cloudapp, netskope-sc-str-events-alerts-privateapp, netskope-sc-str-events-alerts-nonweb, netskope-sc-str-events-alerts-nonweb-2, netskope-sc-str-events-alerts-web, netskope-sc-str-events-alerts-epdlp.
- Updated added_user field for amazon-awscloudtrail-json-image-modify-imageattribute parser.
- Enhanced parser and event builder logic across Cisco CUCM, Cisco FMC, Cisco IE, Dell Security, HP Aruba, ArubaOS, and Microsoft Azure parsers to improve event classification, detection accuracy, and log processing consistency. Expanded field extraction and event coverage by adding support for new CyberArk PAM log fields, extracting user and email attributes in Aruba CPM authentication events.
- Added 18 new Suricata JSON parsers to expand monitoring coverage across DNS, HTTP, FTP, SMTP, SSH, RDP, LDAP, IKE/VPN, QUIC, SNMP, DCERPC, SMB, Kerberos, TFTP, anomaly detection, and statistics events.
- Updated the Microsoft Windows Event codes 4697, 7045 service-create parsers to capture service account details as dest_user and dest_domain Updated activity-type from service-create to service-start for event code 5478.

## Addressed Issues
- Updated event builder for parser dell-sw-kv-alert-trigger-success-security.
- Updated event builder conditions for parser: zscaler-ia-csv-network-traffic-ipsecnetworksecurity.
- Added alternative timestamp to microsoft parsers and templates. Parsers: microsoft-defenderep-json-alert-trigger-success-dlprulematch-1, microsoft-o365-cef-alert-trigger-success-alertdetected, microsoft-o365-sk4-app-activity-success-movetofolder, microsoft-windows-cef-process-create-success-process, microsoft-o365-mix-file-success-workload, microsoft-o365-sk4-app-file-operationworkload, microsoft-o365-json-email-send-receive-internentmessageid, microsoft-o365-sk4-alert-trigger-success-malwareindata, microsoft-o365-sk4-alert-trigger-success-urlclicked, microsoft-azureadip-cef-alert-trigger-success-logininfected. Templates: cef-microsoft-o365-app-activity-1, o365-file-app-activity, cef-microsoft-o365-app-activity.
- Updated dest_ip field for trendmicro-tippingpoint-cef-alert-trigger-success-unityone parser .
- Added src_host field extraction for parser: microsoft-evnps-xml-radius-traffic-success-6272.
- Updated host field extraction in parser - microsoft-evsecurity-kv-endpoint-unlock-success-4801-5.
- Updated event builder of parser catonetworks-cc-json-vpn-http-success-connectivity.
- Updated user field extraction for parser: imperva-securesphere-leef-alert-trigger-success-alertdescription and microsoft-o365-sk4-app-file-operationworkload.
- Added app, session_id, hash_md5, dest_country, web_domain,confidence_level, category, device_type, dest_country, src_ip and email_address fields for netskope-sc-json-alert-trigger-success-alertname and netskope-sc-json-app-activity-success-share parser .
- Updated failure_reason field extraction for parser microsoft-azuread-cef-app-login-clientappused.
- Added new parser azure-azuread-json-endpoint-domain-join-success-azureadjoin. Updated event builder for azure-azuread-json-app-activity-useractivitydisplayname and microsoft-o365-sk4-app-file-operationworkload.
- Updated the email_address field extraction for the parser netskope-sc-sk4-alert-trigger-success-actdetect.
- Added support for new activity_type mappings (registrykeyrenamed, registryvaluedeleted, registrykeydeleted, registrykeycreated, and registryvalueset) in the microsoft-defenderep-sk4-registry-modify-advancedhunting parser.
- Added src_file_dir and bytes field extractions for parser - microsoft-o365-cef-app-file-success-filesyncuploadedfull.
- Updated Event type and added fields into parser fortinet-fortigate-kv-network-app-fortigate.
- Updated process_path, process_dir, process_name, parent_process_path, parent_process_dir, parent_process_name field extractions for parser: microsoft-evsecurity-xml-process-create-success-4688.
- Added host field extraction for parser microsoft-evsecurity-kv-ds-object-move-success-serviceobject.
- Added dest_user field for parser: unix-unix-str-group-member-add-success-usermod , unix-unix-str-group-member-add-success-usermod-1, unix-unix-str-user-modify-usermod.
- Added network_app field extraction for parser: checkpoint-ngfw-kv-network-traffic-success-accept-1 and Updated field extraction for app to network_app for parser: checkpoint-ngfw-kv-network-traffic-fail-reject-1.
- Updated parser name to exabeam-observra-json-ai-agent-activity with new parser conditions and set Product/Platform to Observra.
- Updated login_type_text, session_id for parser: microsoft-o365-cef-app-login-fail-userloginfailed.
- Removed src_file_* mapping from parser microsoft-o365-sk4-file-write-success-filemodified. Added file_name, file_path, file_dir, file_ext field extractions for parser microsoft-o365-sk4-file-write-success-filemodified.
- Updated the regex to extract session_id, login_type_text for parser: microsoft-o365-cef-app-login-success-user.
- Updated parser conditions and regex extractions for dest_domain, domain, dest_login_id, login_id, dest_user_sid, user_sid, auth_process, auth_package, src_host_windows, src_host, subject_sid and src_user in parser - microsoft-evsecurity-kv-endpoint-success-4624-1.
- Updated dest_ip, dest_port, categories, method, protocol, url, web_domain, uri_path, uri_query, mime, bytes_in, bytes_out and user_agent field extractions for parser: mcafee-wg-kv-http-session-success-mwgaccess3.
- Added file_path, file_name, file_ext, file_dir, bytes, email_address, email_domain field extractions for parser: microsoft-o365-sk4-file-write-success-fileuploaded.
- Updated dest_ip field extraction for parser: pan-ngfw-csv-http-session-9999, postgresql-p-str-database-query-success-statement, postgresql-p-str-database-activity-log, unix-unix-str-endpoint-login-sshdconnectionfrom.
- Updated the host and server field extraction for Symantec parsers.
- Updated time regex and respective TimeFormat in parser: snowflake-s-sk4-database-query-success-queryhistory.
- Added rule, folder_name and risk_score field extractions for parser: proofpoint-tap-json-email-receive-fail-threat , proofpoint-tap-json-email-receive-fail-threatinsight.
- Updated activity type from email-send to alert-trigger:success and added log_time and dest_ip field extractions for parser: proofpoint-tap-json-email-receive-fail-threatinsight.
- Added alert_name, alert_type field extraction for parser: pan-cortex-json-alert-trigger-success-xdr.
- Updated time field extraction and corresponding time formats for parsers: microsoft-evsystem-xml-endpoint-notification-success-25, microsoft-azuremon-json-app-activity-operationname, amazon-awscloudtrail-json-app-activity-awsapicall, amazon-awscloudtrail-json-file-write-putobject, amazon-awscloudtrail-json-file-read-getobject, confluent-confluentcloud-json-app-activity-appactivity, microsoft-evsecurity-xml-driver-load-fail-5038.
- Updated parsers for Netskope Security Cloud WebTx logs.

## Code Changes
### ℹ️ How to Read This Document

Each item listed under **Code Changes** includes:

- **A link to a detailed change file**  
  Click the filename (e.g., `Carbon Black.md`) to open a full table showing what changed.

- **A search icon 🔍**  
  Click the 🔍 icon to search for this resource in the [Exabeam Content Library](https://github.com/ExabeamLabs/Content-Library-CIM2).


<details>
<summary><strong id="event-builder">Event Builder (143)</strong></summary>


### Change Types
- [Added Event Builder](#added-event-builder)
- [Edit Conditions](#edit-conditions)
- [Removed Event Builder](#removed-event-builder)

#### Added Event Builder
<a id="added-event-builder"></a>
- [catonetworks-catocloud-vpn-login-fail](CodeChanges/catonetworks-catocloud-vpn-login-fail.md)
- [catonetworks-catocloud-vpn-login-success-1](CodeChanges/catonetworks-catocloud-vpn-login-success-1.md)
- [cisco-unifiedcm-endpoint-authentication-success](CodeChanges/cisco-unifiedcm-endpoint-authentication-success.md)
- [cyberark-pam-app-activity-fail-2](CodeChanges/cyberark-pam-app-activity-fail-2.md)
- [cyberark-pam-app-activity-fail-3](CodeChanges/cyberark-pam-app-activity-fail-3.md)
- [cyberark-pam-app-activity-fail-4](CodeChanges/cyberark-pam-app-activity-fail-4.md)
- [cyberark-pam-app-activity-fail-5](CodeChanges/cyberark-pam-app-activity-fail-5.md)
- [cyberark-pam-app-activity-success-3](CodeChanges/cyberark-pam-app-activity-success-3.md)
- [cyberark-pam-app-activity-success-4](CodeChanges/cyberark-pam-app-activity-success-4.md)
- [cyberark-pam-app-activity-success-5](CodeChanges/cyberark-pam-app-activity-success-5.md)
- [cyberark-pam-app-activity-success-6](CodeChanges/cyberark-pam-app-activity-success-6.md)
- [cyberark-pam-app-activity-success-7](CodeChanges/cyberark-pam-app-activity-success-7.md)
- [cyberark-pam-app-activity-success-8](CodeChanges/cyberark-pam-app-activity-success-8.md)
- [cyberark-pam-app-authentication-success](CodeChanges/cyberark-pam-app-authentication-success.md)
- [cyberark-pam-app-login-fail-2](CodeChanges/cyberark-pam-app-login-fail-2.md)
- [cyberark-pam-app-login-success-2](CodeChanges/cyberark-pam-app-login-success-2.md)
- [cyberark-pam-app-logout-success](CodeChanges/cyberark-pam-app-logout-success.md)
- [cyberark-pam-configuration-create-fail](CodeChanges/cyberark-pam-configuration-create-fail.md)
- [cyberark-pam-configuration-create-success](CodeChanges/cyberark-pam-configuration-create-success.md)
- [cyberark-pam-configuration-create-success-1](CodeChanges/cyberark-pam-configuration-create-success-1.md)
- [cyberark-pam-configuration-delete-fail](CodeChanges/cyberark-pam-configuration-delete-fail.md)
- [cyberark-pam-configuration-delete-success](CodeChanges/cyberark-pam-configuration-delete-success.md)
- [cyberark-pam-configuration-delete-success-1](CodeChanges/cyberark-pam-configuration-delete-success-1.md)
- [cyberark-pam-configuration-modify-fail](CodeChanges/cyberark-pam-configuration-modify-fail.md)
- [cyberark-pam-configuration-modify-success](CodeChanges/cyberark-pam-configuration-modify-success.md)
- [cyberark-pam-configuration-modify-success-1](CodeChanges/cyberark-pam-configuration-modify-success-1.md)
- [cyberark-pam-database-activity-fail](CodeChanges/cyberark-pam-database-activity-fail.md)
- [cyberark-pam-database-activity-success](CodeChanges/cyberark-pam-database-activity-success.md)
- [cyberark-pam-file-create-success](CodeChanges/cyberark-pam-file-create-success.md)
- [cyberark-pam-file-delete-success-1](CodeChanges/cyberark-pam-file-delete-success-1.md)
- [cyberark-pam-file-read-success-1](CodeChanges/cyberark-pam-file-read-success-1.md)
- [cyberark-pam-file-rename-success](CodeChanges/cyberark-pam-file-rename-success.md)
- [cyberark-pam-process-create-success](CodeChanges/cyberark-pam-process-create-success.md)
- [cyberark-pam-role-create-success](CodeChanges/cyberark-pam-role-create-success.md)
- [cyberark-pam-role-delete-success](CodeChanges/cyberark-pam-role-delete-success.md)
- [cyberark-pam-role-modify-success](CodeChanges/cyberark-pam-role-modify-success.md)
- [cyberark-pam-user-create-success](CodeChanges/cyberark-pam-user-create-success.md)
- [cyberark-pam-user-delete-success](CodeChanges/cyberark-pam-user-delete-success.md)
- [cyberark-pam-user-modify-success](CodeChanges/cyberark-pam-user-modify-success.md)
- [cyberark-pam-user-privilege-modify-success](CodeChanges/cyberark-pam-user-privilege-modify-success.md)
- [dell-vpn-login-fail](CodeChanges/dell-vpn-login-fail.md)
- [dl-amazon-aws-app-activity-success-3](CodeChanges/dl-amazon-aws-app-activity-success-3.md)
- [dl-cisco-fmc-app-activity-success-1](CodeChanges/dl-cisco-fmc-app-activity-success-1.md)
- [dl-crowdstrike-aws-alert-trigger-success](CodeChanges/dl-crowdstrike-aws-alert-trigger-success.md)
- [dl-crowdstrike-falcon-alert-trigger-success-12](CodeChanges/dl-crowdstrike-falcon-alert-trigger-success-12.md)
- [dl-cyberark-pam-alert-create-success](CodeChanges/dl-cyberark-pam-alert-create-success.md)
- [dl-cyberark-pam-alert-modify-success](CodeChanges/dl-cyberark-pam-alert-modify-success.md)
- [dl-cyberark-pam-file-property-delete-success](CodeChanges/dl-cyberark-pam-file-property-delete-success.md)
- [dl-cyberark-pam-file-property-modify-success](CodeChanges/dl-cyberark-pam-file-property-modify-success.md)
- [dl-cyberark-pam-password-create-success](CodeChanges/dl-cyberark-pam-password-create-success.md)
- [dl-cyberark-pam-password-modify-success](CodeChanges/dl-cyberark-pam-password-modify-success.md)
- [dl-cyberark-pam-password-read-success](CodeChanges/dl-cyberark-pam-password-read-success.md)
- [dl-cyberark-pam-policy-disable-success](CodeChanges/dl-cyberark-pam-policy-disable-success.md)
- [dl-cyberark-pam-policy-enable-success](CodeChanges/dl-cyberark-pam-policy-enable-success.md)
- [dl-cyberark-pam-rule-disable-success](CodeChanges/dl-cyberark-pam-rule-disable-success.md)
- [dl-cyberark-pam-rule-enable-success](CodeChanges/dl-cyberark-pam-rule-enable-success.md)
- [dl-cyberark-pam-ssh-close-fail](CodeChanges/dl-cyberark-pam-ssh-close-fail.md)
- [dl-cyberark-pam-ssh-close-success](CodeChanges/dl-cyberark-pam-ssh-close-success.md)
- [dl-cyberark-pam-ssh-start-success](CodeChanges/dl-cyberark-pam-ssh-start-success.md)
- [dl-dell-network-notification-success](CodeChanges/dl-dell-network-notification-success.md)
- [dl-exabeam-observra-ai-agent-request-success](CodeChanges/dl-exabeam-observra-ai-agent-request-success.md)
- [dl-exabeam-observra-ai-agent-response-success](CodeChanges/dl-exabeam-observra-ai-agent-response-success.md)
- [dl-exabeam-observra-app-activity-success](CodeChanges/dl-exabeam-observra-app-activity-success.md)
- [dl-exabeam-observra-tool-call-success](CodeChanges/dl-exabeam-observra-tool-call-success.md)
- [dl-exabeam-observra-tool-return-success](CodeChanges/dl-exabeam-observra-tool-return-success.md)
- [dl-hillstone-waf-http-traffic-success](CodeChanges/dl-hillstone-waf-http-traffic-success.md)
- [dl-hillstone-waf-network-traffic-success](CodeChanges/dl-hillstone-waf-network-traffic-success.md)
- [dl-microsoft-azure-endpoint-domain-join-success](CodeChanges/dl-microsoft-azure-endpoint-domain-join-success.md)
- [dl-netskope-network-http-session-fail-4](CodeChanges/dl-netskope-network-http-session-fail-4.md)
- [dl-netskope-network-http-session-success-4](CodeChanges/dl-netskope-network-http-session-success-4.md)
- [dl-netskope-network-network-traffic-fail-2](CodeChanges/dl-netskope-network-network-traffic-fail-2.md)
- [dl-netskope-network-network-traffic-success-2](CodeChanges/dl-netskope-network-network-traffic-success-2.md)
- [dl-netskope-sc-app-activity-success-3](CodeChanges/dl-netskope-sc-app-activity-success-3.md)
- [dl-netskope-sc-app-activity-success-4](CodeChanges/dl-netskope-sc-app-activity-success-4.md)
- [dl-netskope-sc-app-login-fail-1](CodeChanges/dl-netskope-sc-app-login-fail-1.md)
- [dl-netskope-sc-app-login-success-2](CodeChanges/dl-netskope-sc-app-login-success-2.md)
- [dl-netskope-sc-app-logout-success-3](CodeChanges/dl-netskope-sc-app-logout-success-3.md)
- [dl-netskope-sc-endpoint-activity-success-1](CodeChanges/dl-netskope-sc-endpoint-activity-success-1.md)
- [dl-netskope-securitycloud-alert-trigger-success-5](CodeChanges/dl-netskope-securitycloud-alert-trigger-success-5.md)
- [dl-suricata-ftp-traffic-success](CodeChanges/dl-suricata-ftp-traffic-success.md)
- [dl-suricata-network-notification-success](CodeChanges/dl-suricata-network-notification-success.md)
- [dl-suricata-smtp-start-success](CodeChanges/dl-suricata-smtp-start-success.md)
- [fortinet-fortigate-dns-request-success-1](CodeChanges/fortinet-fortigate-dns-request-success-1.md)
- [hillstone-bds-alert-trigger-success](CodeChanges/hillstone-bds-alert-trigger-success.md)
- [hillstone-waf-http-traffic-fail](CodeChanges/hillstone-waf-http-traffic-fail.md)
- [hillstone-waf-http-traffic-success](CodeChanges/hillstone-waf-http-traffic-success.md)
- [microsoft-365-app-modify-fail](CodeChanges/microsoft-365-app-modify-fail.md)
- [microsoft-365-app-modify-success-1](CodeChanges/microsoft-365-app-modify-success-1.md)
- [microsoft-365-endpoint-modify-success](CodeChanges/microsoft-365-endpoint-modify-success.md)
- [microsoft-network-http-session-fail-5](CodeChanges/microsoft-network-http-session-fail-5.md)
- [microsoft-windows-app-activity-success-1](CodeChanges/microsoft-windows-app-activity-success-1.md)
- [microsoft-windows-endpoint-login-success-26](CodeChanges/microsoft-windows-endpoint-login-success-26.md)
- [microsoft-windows-registry-create-success-1](CodeChanges/microsoft-windows-registry-create-success-1.md)
- [microsoft-windows-registry-delete-success-1](CodeChanges/microsoft-windows-registry-delete-success-1.md)
- [microsoft-windows-registry-rename-success-1](CodeChanges/microsoft-windows-registry-rename-success-1.md)
- [microsoft-windows-service-start-success](CodeChanges/microsoft-windows-service-start-success.md)
- [proofpoint-email-receive-success-1](CodeChanges/proofpoint-email-receive-success-1.md)
- [proofpoint-email-send-success](CodeChanges/proofpoint-email-send-success.md)
- [proofpoint-ep-alert-trigger-success](CodeChanges/proofpoint-ep-alert-trigger-success.md)
- [suricata-app-activity-success](CodeChanges/suricata-app-activity-success.md)
- [suricata-dns-request-success](CodeChanges/suricata-dns-request-success.md)
- [suricata-dns-response-success](CodeChanges/suricata-dns-response-success.md)
- [suricata-endpoint-authentication-fail](CodeChanges/suricata-endpoint-authentication-fail.md)
- [suricata-endpoint-authentication-fail-1](CodeChanges/suricata-endpoint-authentication-fail-1.md)
- [suricata-endpoint-authentication-success](CodeChanges/suricata-endpoint-authentication-success.md)
- [suricata-endpoint-authentication-success-1](CodeChanges/suricata-endpoint-authentication-success-1.md)
- [suricata-endpoint-login-fail](CodeChanges/suricata-endpoint-login-fail.md)
- [suricata-endpoint-login-success](CodeChanges/suricata-endpoint-login-success.md)
- [suricata-file-read-success](CodeChanges/suricata-file-read-success.md)
- [suricata-http-traffic-fail](CodeChanges/suricata-http-traffic-fail.md)
- [suricata-http-traffic-success](CodeChanges/suricata-http-traffic-success.md)
- [suricata-network-traffic-success](CodeChanges/suricata-network-traffic-success.md)
- [suricata-rdp-traffic-success](CodeChanges/suricata-rdp-traffic-success.md)
- [suricata-ssh-traffic-success](CodeChanges/suricata-ssh-traffic-success.md)
- [suricata-vpn-authentication-success](CodeChanges/suricata-vpn-authentication-success.md)
- [symantec-emailsecurity-email-receive-success-4](CodeChanges/symantec-emailsecurity-email-receive-success-4.md)
- [symantec-emailsecurity-email-send-success-4](CodeChanges/symantec-emailsecurity-email-send-success-4.md)

#### Edit Conditions
<a id="edit-conditions"></a>
- [aruba-nac-failed-logon-1](CodeChanges/aruba-nac-failed-logon-1.md)
- [cisco-unifiedcm-endpoint-authentication-fail](CodeChanges/cisco-unifiedcm-endpoint-authentication-fail.md)
- [cyberark-pam-app-activity-success-2](CodeChanges/cyberark-pam-app-activity-success-2.md)
- [cyberark-pam-app-login-fail](CodeChanges/cyberark-pam-app-login-fail.md)
- [cyberark-pam-app-login-success](CodeChanges/cyberark-pam-app-login-success.md)
- [cyberark-pam-file-delete-success](CodeChanges/cyberark-pam-file-delete-success.md)
- [cyberark-pam-file-read-success](CodeChanges/cyberark-pam-file-read-success.md)
- [dell-alert-trigger-success](CodeChanges/dell-alert-trigger-success.md)
- [dell-alert-trigger-success-1](CodeChanges/dell-alert-trigger-success-1.md)
- [dell-network-traffic-fail](CodeChanges/dell-network-traffic-fail.md)
- [dell-vpn-session-success](CodeChanges/dell-vpn-session-success.md)
- [dl-fortinet-fortigate-network-notification-success-1](CodeChanges/dl-fortinet-fortigate-network-notification-success-1.md)
- [microsoft-365-app-activity-fail](CodeChanges/microsoft-365-app-activity-fail.md)
- [microsoft-365-app-activity-success-2](CodeChanges/microsoft-365-app-activity-success-2.md)
- [microsoft-azure-app-activity-success-5](CodeChanges/microsoft-azure-app-activity-success-5.md)
- [microsoft-windows-registry-modify-success](CodeChanges/microsoft-windows-registry-modify-success.md)
- [zscaler-network-network-traffic-fail-1](CodeChanges/zscaler-network-network-traffic-fail-1.md)
- [zscaler-network-network-traffic-success-1](CodeChanges/zscaler-network-network-traffic-success-1.md)

#### Removed Event Builder
<a id="removed-event-builder"></a>
- [dell-vpn-authentication-success](CodeChanges/dell-vpn-authentication-success.md)
- [dell-vpn-login-success](CodeChanges/dell-vpn-login-success.md)
- [dl-exabeam-aba-ai-agent-request-success](CodeChanges/dl-exabeam-aba-ai-agent-request-success.md)
- [dl-exabeam-aba-ai-agent-response-success](CodeChanges/dl-exabeam-aba-ai-agent-response-success.md)
- [dl-exabeam-aba-app-activity-success](CodeChanges/dl-exabeam-aba-app-activity-success.md)
- [dl-exabeam-aba-tool-call-success](CodeChanges/dl-exabeam-aba-tool-call-success.md)
- [dl-exabeam-aba-tool-return-success](CodeChanges/dl-exabeam-aba-tool-return-success.md)
- [tessian-email-send-success](CodeChanges/tessian-email-send-success.md)

</details>

<details>
<summary><strong id="event-enricher">Event Enricher (15)</strong></summary>


### Change Types
- [Added Enricher](#added-enricher)
- [Removed Enricher](#removed-enricher)

#### Added Enricher
<a id="added-enricher"></a>
- [Destination%20Email%20to%20Destination%20User%20Enricher-1](CodeChanges/Destination%20Email%20to%20Destination%20User%20Enricher-1.md)
- [Destination%20Email%20to%20Destination%20User%20Enricher-2](CodeChanges/Destination%20Email%20to%20Destination%20User%20Enricher-2.md)
- [Email%20to%20User%20Enricher-1](CodeChanges/Email%20to%20User%20Enricher-1.md)
- [Email%20to%20User%20Enricher-2](CodeChanges/Email%20to%20User%20Enricher-2.md)
- [EmployeeNumber%20to%20User%20Lookup](CodeChanges/EmployeeNumber%20to%20User%20Lookup.md)
- [GitHub%20User%20Type%20Enricher](CodeChanges/GitHub%20User%20Type%20Enricher.md)
- [Microsoft%20User%20UPN%20to%20User%2FEmail%20Enricher](CodeChanges/Microsoft%20User%20UPN%20to%20User%2FEmail%20Enricher.md)
- [Null%20Direction%20Network%20Traffic](CodeChanges/Null%20Direction%20Network%20Traffic.md)
- [UID%20to%20User%20Lookup](CodeChanges/UID%20to%20User%20Lookup.md)
- [User%20SID%20to%20User%20Lookup](CodeChanges/User%20SID%20to%20User%20Lookup.md)

#### Removed Enricher
<a id="removed-enricher"></a>
- [Destination%20User%20to%20Destination%20Email%20Address](CodeChanges/Destination%20User%20to%20Destination%20Email%20Address.md)
- [Destination%20User%20to%20Destination%20User%20SID%20or%20Destination%20User%20ID](CodeChanges/Destination%20User%20to%20Destination%20User%20SID%20or%20Destination%20User%20ID.md)
- [Microsoft%20User%20UPN%20to%20Email%20Address%20Enricher](CodeChanges/Microsoft%20User%20UPN%20to%20Email%20Address%20Enricher.md)
- [User%20to%20Email%20Address](CodeChanges/User%20to%20Email%20Address.md)
- [User%20to%20User%20SID%20or%20User%20ID](CodeChanges/User%20to%20User%20SID%20or%20User%20ID.md)

</details>

<details>
<summary><strong id="parser">Parser (407)</strong></summary>


### Change Types
- [Could Not Compare](#could-not-compare)
- [Added Attribute](#added-attribute)
- [Added Parser](#added-parser)
- [Changed](#changed)
- [Changed Parsed Fields](#changed-parsed-fields)
- [Edit Attribute](#edit-attribute)
- [Edit Regex Field](#edit-regex-field)
- [Removed Parser](#removed-parser)

#### Could Not Compare
<a id="could-not-compare"></a>
- [microsoft-azureadip-cef-alert-trigger-success-logininfected](CodeChanges/microsoft-azureadip-cef-alert-trigger-success-logininfected.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azureadip-cef-alert-trigger-success-logininfected&type=code)
- [microsoft-defenderep-json-alert-trigger-success-dlprulematch-1](CodeChanges/microsoft-defenderep-json-alert-trigger-success-dlprulematch-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-defenderep-json-alert-trigger-success-dlprulematch-1&type=code)
- [microsoft-evapp-json-certificate-expire-64](CodeChanges/microsoft-evapp-json-certificate-expire-64.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-certificate-expire-64&type=code)
- [microsoft-evapp-json-configuration-modify-16028](CodeChanges/microsoft-evapp-json-configuration-modify-16028.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-configuration-modify-16028&type=code)
- [microsoft-evapp-json-endpoint-activity-fail-5605](CodeChanges/microsoft-evapp-json-endpoint-activity-fail-5605.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-endpoint-activity-fail-5605&type=code)
- [microsoft-evapp-json-endpoint-activity-success-catchall](CodeChanges/microsoft-evapp-json-endpoint-activity-success-catchall.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-endpoint-activity-success-catchall&type=code)
- [microsoft-evapp-json-endpoint-notification-1000](CodeChanges/microsoft-evapp-json-endpoint-notification-1000.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-endpoint-notification-1000&type=code)
- [microsoft-evapp-json-file-close-success-327](CodeChanges/microsoft-evapp-json-file-close-success-327.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-file-close-success-327&type=code)
- [microsoft-evapp-json-file-read-success-326](CodeChanges/microsoft-evapp-json-file-read-success-326.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-file-read-success-326&type=code)
- [microsoft-evapp-json-script-execute-fail-4100](CodeChanges/microsoft-evapp-json-script-execute-fail-4100.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evapp-json-script-execute-fail-4100&type=code)
- [microsoft-evsecurity-json-audit-policy-modify-success-5449](CodeChanges/microsoft-evsecurity-json-audit-policy-modify-success-5449.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-audit-policy-modify-success-5449&type=code)
- [microsoft-evsecurity-json-driver-load-fail-5038](CodeChanges/microsoft-evsecurity-json-driver-load-fail-5038.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-driver-load-fail-5038&type=code)
- [microsoft-evsecurity-json-endpoint-activity-success-4691](CodeChanges/microsoft-evsecurity-json-endpoint-activity-success-4691.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-activity-success-4691&type=code)
- [microsoft-evsecurity-json-endpoint-activity-success-catchall](CodeChanges/microsoft-evsecurity-json-endpoint-activity-success-catchall.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-activity-success-catchall&type=code)
- [microsoft-evsecurity-json-endpoint-login-success-4624-6](CodeChanges/microsoft-evsecurity-json-endpoint-login-success-4624-6.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-login-success-4624-6&type=code)
- [microsoft-evsecurity-json-endpoint-logout-4634-2](CodeChanges/microsoft-evsecurity-json-endpoint-logout-4634-2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-logout-4634-2&type=code)
- [microsoft-evsecurity-json-policy-modify-4946-1](CodeChanges/microsoft-evsecurity-json-policy-modify-4946-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-modify-4946-1&type=code)
- [microsoft-evsecurity-json-policy-modify-4948-1](CodeChanges/microsoft-evsecurity-json-policy-modify-4948-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-modify-4948-1&type=code)
- [microsoft-evsecurity-json-policy-modify-success-4947](CodeChanges/microsoft-evsecurity-json-policy-modify-success-4947.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-modify-success-4947&type=code)
- [microsoft-evsecurity-json-share-modify-success-5143](CodeChanges/microsoft-evsecurity-json-share-modify-success-5143.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-share-modify-success-5143&type=code)
- [microsoft-evsecurity-kv-endpoint-authentication-4774](CodeChanges/microsoft-evsecurity-kv-endpoint-authentication-4774.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-endpoint-authentication-4774&type=code)
- [microsoft-evsecurity-kv-endpoint-success-4624-1](CodeChanges/microsoft-evsecurity-kv-endpoint-success-4624-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-endpoint-success-4624-1&type=code)
- [microsoft-evsystem-json-endpoint-activity-success-catchall](CodeChanges/microsoft-evsystem-json-endpoint-activity-success-catchall.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-json-endpoint-activity-success-catchall&type=code)
- [microsoft-evsystem-json-policy-apply-fail-1030](CodeChanges/microsoft-evsystem-json-policy-apply-fail-1030.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-json-policy-apply-fail-1030&type=code)
- [microsoft-evsystem-json-policy-apply-success-1502](CodeChanges/microsoft-evsystem-json-policy-apply-success-1502.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-json-policy-apply-success-1502&type=code)
- [microsoft-evsystem-json-service-state-modify-7040-1](CodeChanges/microsoft-evsystem-json-service-state-modify-7040-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-json-service-state-modify-7040-1&type=code)
- [microsoft-evsystem-json-ssl-start-fail-36874](CodeChanges/microsoft-evsystem-json-ssl-start-fail-36874.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-json-ssl-start-fail-36874&type=code)
- [microsoft-evsystem-xml-endpoint-notification-success-25](CodeChanges/microsoft-evsystem-xml-endpoint-notification-success-25.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-xml-endpoint-notification-success-25&type=code)
- [microsoft-o365-json-email-send-receive-internentmessageid](CodeChanges/microsoft-o365-json-email-send-receive-internentmessageid.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-email-send-receive-internentmessageid&type=code)
- [microsoft-o365-mix-file-success-workload](CodeChanges/microsoft-o365-mix-file-success-workload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-mix-file-success-workload&type=code)
- [microsoft-o365-sk4-alert-trigger-success-malwareindata](CodeChanges/microsoft-o365-sk4-alert-trigger-success-malwareindata.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-alert-trigger-success-malwareindata&type=code)
- [microsoft-o365-sk4-alert-trigger-success-urlclicked](CodeChanges/microsoft-o365-sk4-alert-trigger-success-urlclicked.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-alert-trigger-success-urlclicked&type=code)
- [microsoft-o365-sk4-app-activity-success-create](CodeChanges/microsoft-o365-sk4-app-activity-success-create.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-activity-success-create&type=code)
- [microsoft-o365-sk4-app-activity-success-movetodeleteditems-2](CodeChanges/microsoft-o365-sk4-app-activity-success-movetodeleteditems-2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-activity-success-movetodeleteditems-2&type=code)
- [microsoft-o365-sk4-app-activity-success-softdelete](CodeChanges/microsoft-o365-sk4-app-activity-success-softdelete.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-activity-success-softdelete&type=code)
- [microsoft-o365-sk4-app-file-operationworkload](CodeChanges/microsoft-o365-sk4-app-file-operationworkload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-operationworkload&type=code)
- [microsoft-o365-sk4-file-app-userkey](CodeChanges/microsoft-o365-sk4-file-app-userkey.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-file-app-userkey&type=code)
- [microsoft-o365-sk4-file-app-userkey-1](CodeChanges/microsoft-o365-sk4-file-app-userkey-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-file-app-userkey-1&type=code)
- [microsoft-windows-cef-process-create-success-process](CodeChanges/microsoft-windows-cef-process-create-success-process.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-windows-cef-process-create-success-process&type=code)
- [snowflake-s-sk4-database-query-success-queryhistory](CodeChanges/snowflake-s-sk4-database-query-success-queryhistory.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20snowflake-s-sk4-database-query-success-queryhistory&type=code)

#### Added Attribute
<a id="added-attribute"></a>
- [microsoft-o365-json-user-permission-modify-success-addapproleassignment](CodeChanges/microsoft-o365-json-user-permission-modify-success-addapproleassignment.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-user-permission-modify-success-addapproleassignment&type=code)

#### Added Parser
<a id="added-parser"></a>
- [amazon-awsredshift-json-app-activity-success-redshift](CodeChanges/amazon-awsredshift-json-app-activity-success-redshift.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20amazon-awsredshift-json-app-activity-success-redshift&type=code)
- [amazon-awsredshift-sk4-app-activity-success-redshift](CodeChanges/amazon-awsredshift-sk4-app-activity-success-redshift.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20amazon-awsredshift-sk4-app-activity-success-redshift&type=code)
- [amazon-inspector-json-alert-trigger-success-inspector](CodeChanges/amazon-inspector-json-alert-trigger-success-inspector.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20amazon-inspector-json-alert-trigger-success-inspector&type=code)
- [azure-azuread-json-endpoint-domain-join-success-azureadjoin](CodeChanges/azure-azuread-json-endpoint-domain-join-success-azureadjoin.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20azure-azuread-json-endpoint-domain-join-success-azureadjoin&type=code)
- [crowdstrike-falcon-json-alert-trigger-success-cloudproviderfinding](CodeChanges/crowdstrike-falcon-json-alert-trigger-success-cloudproviderfinding.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-alert-trigger-success-cloudproviderfinding&type=code)
- [cyberark-pam-str-app-activity-success-connectormanagement](CodeChanges/cyberark-pam-str-app-activity-success-connectormanagement.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-connectormanagement&type=code)
- [cyberark-pam-str-app-activity-success-identity](CodeChanges/cyberark-pam-str-app-activity-success-identity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-identity&type=code)
- [cyberark-pam-str-app-activity-success-itdr](CodeChanges/cyberark-pam-str-app-activity-success-itdr.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-itdr&type=code)
- [cyberark-pam-str-app-activity-success-privilegecloud](CodeChanges/cyberark-pam-str-app-activity-success-privilegecloud.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-privilegecloud&type=code)
- [cyberark-pam-str-app-activity-success-psmcmda](CodeChanges/cyberark-pam-str-app-activity-success-psmcmda.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-psmcmda&type=code)
- [cyberark-pam-str-app-activity-success-scpsession](CodeChanges/cyberark-pam-str-app-activity-success-scpsession.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-scpsession&type=code)
- [cyberark-pam-str-app-activity-success-secureaccess](CodeChanges/cyberark-pam-str-app-activity-success-secureaccess.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-secureaccess&type=code)
- [cyberark-pam-str-app-activity-success-settingsupdated](CodeChanges/cyberark-pam-str-app-activity-success-settingsupdated.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-settingsupdated&type=code)
- [cyberark-pam-str-app-activity-success-sftpsession](CodeChanges/cyberark-pam-str-app-activity-success-sftpsession.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-sftpsession&type=code)
- [cyberark-pam-str-app-activity-success-sshsession](CodeChanges/cyberark-pam-str-app-activity-success-sshsession.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-sshsession&type=code)
- [cyberark-pam-str-app-activity-success-sshsessionend](CodeChanges/cyberark-pam-str-app-activity-success-sshsessionend.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-sshsessionend&type=code)
- [cyberark-pam-str-app-activity-success-sshsessionend-1](CodeChanges/cyberark-pam-str-app-activity-success-sshsessionend-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-activity-success-sshsessionend-1&type=code)
- [cyberark-pam-str-app-authentication-success-mfacachingtoken](CodeChanges/cyberark-pam-str-app-authentication-success-mfacachingtoken.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-app-authentication-success-mfacachingtoken&type=code)
- [cyberark-pam-str-database-activity-success-databasesession](CodeChanges/cyberark-pam-str-database-activity-success-databasesession.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-str-database-activity-success-databasesession&type=code)
- [cybereason-cr-json-alert-trigger-success-malops](CodeChanges/cybereason-cr-json-alert-trigger-success-malops.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cybereason-cr-json-alert-trigger-success-malops&type=code)
- [exabeam-observra-json-ai-agent-activity](CodeChanges/exabeam-observra-json-ai-agent-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20exabeam-observra-json-ai-agent-activity&type=code)
- [hillstone-hnbds-str-alert-trigger-success-threatsecurity](CodeChanges/hillstone-hnbds-str-alert-trigger-success-threatsecurity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20hillstone-hnbds-str-alert-trigger-success-threatsecurity&type=code)
- [hillstone-hwaf-json-http-traffic-policy](CodeChanges/hillstone-hwaf-json-http-traffic-policy.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20hillstone-hwaf-json-http-traffic-policy&type=code)
- [hillstone-hwaf-str-http-traffic-wafwebevent](CodeChanges/hillstone-hwaf-str-http-traffic-wafwebevent.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20hillstone-hwaf-str-http-traffic-wafwebevent&type=code)
- [hillstone-hwaf-str-network-traffic-eventnetarp](CodeChanges/hillstone-hwaf-str-network-traffic-eventnetarp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20hillstone-hwaf-str-network-traffic-eventnetarp&type=code)
- [microsoft-azureadactivity-json-app-activity-success-aadgraphactivity](CodeChanges/microsoft-azureadactivity-json-app-activity-success-aadgraphactivity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azureadactivity-json-app-activity-success-aadgraphactivity&type=code)
- [microsoft-evsecurity-json-audit-policy-modify-success-4719-3](CodeChanges/microsoft-evsecurity-json-audit-policy-modify-success-4719-3.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-audit-policy-modify-success-4719-3&type=code)
- [microsoft-evsecurity-json-ds-object-modify-success-4738-3](CodeChanges/microsoft-evsecurity-json-ds-object-modify-success-4738-3.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-ds-object-modify-success-4738-3&type=code)
- [microsoft-evsecurity-json-endpoint-login-success-4624-9](CodeChanges/microsoft-evsecurity-json-endpoint-login-success-4624-9.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-login-success-4624-9&type=code)
- [microsoft-evsecurity-json-endpoint-time-modify-4616](CodeChanges/microsoft-evsecurity-json-endpoint-time-modify-4616.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-time-modify-4616&type=code)
- [microsoft-evsecurity-json-group-member-add-success-4728-2](CodeChanges/microsoft-evsecurity-json-group-member-add-success-4728-2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-group-member-add-success-4728-2&type=code)
- [microsoft-evsecurity-json-group-member-add-success-4732](CodeChanges/microsoft-evsecurity-json-group-member-add-success-4732.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-group-member-add-success-4732&type=code)
- [microsoft-evsecurity-json-group-member-add-success-4756-1](CodeChanges/microsoft-evsecurity-json-group-member-add-success-4756-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-group-member-add-success-4756-1&type=code)
- [microsoft-evsecurity-json-group-modify-success-4735](CodeChanges/microsoft-evsecurity-json-group-modify-success-4735.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-group-modify-success-4735&type=code)
- [microsoft-evsecurity-json-group-modify-success-4737-1](CodeChanges/microsoft-evsecurity-json-group-modify-success-4737-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-group-modify-success-4737-1&type=code)
- [microsoft-evsecurity-json-group-modify-success-4755-1](CodeChanges/microsoft-evsecurity-json-group-modify-success-4755-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-group-modify-success-4755-1&type=code)
- [microsoft-evsecurity-json-log-clear-success-1102](CodeChanges/microsoft-evsecurity-json-log-clear-success-1102.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-log-clear-success-1102&type=code)
- [microsoft-evsecurity-json-network-session-fail-5152](CodeChanges/microsoft-evsecurity-json-network-session-fail-5152.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-network-session-fail-5152&type=code)
- [microsoft-evsecurity-json-network-session-fail-5157-1](CodeChanges/microsoft-evsecurity-json-network-session-fail-5157-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-network-session-fail-5157-1&type=code)
- [microsoft-evsecurity-json-policy-apply-4954](CodeChanges/microsoft-evsecurity-json-policy-apply-4954.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-apply-4954&type=code)
- [microsoft-evsecurity-json-policy-modify-5447-1](CodeChanges/microsoft-evsecurity-json-policy-modify-5447-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-modify-5447-1&type=code)
- [microsoft-evsecurity-json-policy-modify-success-4947-1](CodeChanges/microsoft-evsecurity-json-policy-modify-success-4947-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-modify-success-4947-1&type=code)
- [microsoft-evsecurity-json-registry-create-success-4657](CodeChanges/microsoft-evsecurity-json-registry-create-success-4657.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-registry-create-success-4657&type=code)
- [microsoft-evsecurity-json-scheduled-task-create-success-4698-1](CodeChanges/microsoft-evsecurity-json-scheduled-task-create-success-4698-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-scheduled-task-create-success-4698-1&type=code)
- [microsoft-evsecurity-json-scheduled-task-create-success-4700](CodeChanges/microsoft-evsecurity-json-scheduled-task-create-success-4700.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-scheduled-task-create-success-4700&type=code)
- [microsoft-evsecurity-json-scheduled-task-delete-4699](CodeChanges/microsoft-evsecurity-json-scheduled-task-delete-4699.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-scheduled-task-delete-4699&type=code)
- [microsoft-evsecurity-json-scheduled-task-disable-4701](CodeChanges/microsoft-evsecurity-json-scheduled-task-disable-4701.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-scheduled-task-disable-4701&type=code)
- [microsoft-evsecurity-json-service-create-success-4697-1](CodeChanges/microsoft-evsecurity-json-service-create-success-4697-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-service-create-success-4697-1&type=code)
- [microsoft-evsecurity-json-user-create-success-4720-2](CodeChanges/microsoft-evsecurity-json-user-create-success-4720-2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-user-create-success-4720-2&type=code)
- [microsoft-evsecurity-json-user-disable-success-4725-3](CodeChanges/microsoft-evsecurity-json-user-disable-success-4725-3.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-user-disable-success-4725-3&type=code)
- [microsoft-evsecurity-json-user-enable-success-4722-3](CodeChanges/microsoft-evsecurity-json-user-enable-success-4722-3.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-user-enable-success-4722-3&type=code)
- [microsoft-evsecurity-json-user-password-modify-4723-4](CodeChanges/microsoft-evsecurity-json-user-password-modify-4723-4.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-user-password-modify-4723-4&type=code)
- [microsoft-evsecurity-json-user-unlock-success-4767-3](CodeChanges/microsoft-evsecurity-json-user-unlock-success-4767-3.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-user-unlock-success-4767-3&type=code)
- [netskope-sc-str-events-alerts-cloudapp](CodeChanges/netskope-sc-str-events-alerts-cloudapp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-events-alerts-cloudapp&type=code)
- [netskope-sc-str-events-alerts-epdlp](CodeChanges/netskope-sc-str-events-alerts-epdlp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-events-alerts-epdlp&type=code)
- [netskope-sc-str-events-alerts-nonweb](CodeChanges/netskope-sc-str-events-alerts-nonweb.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-events-alerts-nonweb&type=code)
- [netskope-sc-str-events-alerts-nonweb-2](CodeChanges/netskope-sc-str-events-alerts-nonweb-2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-events-alerts-nonweb-2&type=code)
- [netskope-sc-str-events-alerts-privateapp](CodeChanges/netskope-sc-str-events-alerts-privateapp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-events-alerts-privateapp&type=code)
- [netskope-sc-str-events-alerts-web](CodeChanges/netskope-sc-str-events-alerts-web.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-events-alerts-web&type=code)
- [proofpoint-tappod-json-email-receive-success-inbound](CodeChanges/proofpoint-tappod-json-email-receive-success-inbound.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20proofpoint-tappod-json-email-receive-success-inbound&type=code)
- [proofpoint-tappod-json-email-send-success-outbound](CodeChanges/proofpoint-tappod-json-email-send-success-outbound.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20proofpoint-tappod-json-email-send-success-outbound&type=code)
- [salesforce-sf-json-app-activity-success-apianomalyeventstore](CodeChanges/salesforce-sf-json-app-activity-success-apianomalyeventstore.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20salesforce-sf-json-app-activity-success-apianomalyeventstore&type=code)
- [suricata-s-json-dns-request-success-eventtypedns](CodeChanges/suricata-s-json-dns-request-success-eventtypedns.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-dns-request-success-eventtypedns&type=code)
- [suricata-s-json-dns-request-success-eventtypemdns](CodeChanges/suricata-s-json-dns-request-success-eventtypemdns.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-dns-request-success-eventtypemdns&type=code)
- [suricata-s-json-dns-response-success-eventtypedns](CodeChanges/suricata-s-json-dns-response-success-eventtypedns.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-dns-response-success-eventtypedns&type=code)
- [suricata-s-json-endpoint-authentication-eventtypeldap](CodeChanges/suricata-s-json-endpoint-authentication-eventtypeldap.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-endpoint-authentication-eventtypeldap&type=code)
- [suricata-s-json-endpoint-eventtypekrb5](CodeChanges/suricata-s-json-endpoint-eventtypekrb5.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-endpoint-eventtypekrb5&type=code)
- [suricata-s-json-file-read-success-eventtypetftp](CodeChanges/suricata-s-json-file-read-success-eventtypetftp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-file-read-success-eventtypetftp&type=code)
- [suricata-s-json-ftp-traffic-success-eventtypeftp](CodeChanges/suricata-s-json-ftp-traffic-success-eventtypeftp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-ftp-traffic-success-eventtypeftp&type=code)
- [suricata-s-json-http-traffic-http](CodeChanges/suricata-s-json-http-traffic-http.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-http-traffic-http&type=code)
- [suricata-s-json-network-notification-success-eventtypeanomaly](CodeChanges/suricata-s-json-network-notification-success-eventtypeanomaly.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-network-notification-success-eventtypeanomaly&type=code)
- [suricata-s-json-network-notification-success-eventtypesmb](CodeChanges/suricata-s-json-network-notification-success-eventtypesmb.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-network-notification-success-eventtypesmb&type=code)
- [suricata-s-json-network-notification-success-eventtypestats](CodeChanges/suricata-s-json-network-notification-success-eventtypestats.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-network-notification-success-eventtypestats&type=code)
- [suricata-s-json-network-traffic-success-eventtypedcerpc](CodeChanges/suricata-s-json-network-traffic-success-eventtypedcerpc.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-network-traffic-success-eventtypedcerpc&type=code)
- [suricata-s-json-network-traffic-success-eventtypequic](CodeChanges/suricata-s-json-network-traffic-success-eventtypequic.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-network-traffic-success-eventtypequic&type=code)
- [suricata-s-json-network-traffic-success-eventtypesnmp](CodeChanges/suricata-s-json-network-traffic-success-eventtypesnmp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-network-traffic-success-eventtypesnmp&type=code)
- [suricata-s-json-rdp-traffic-success-eventtyperdp](CodeChanges/suricata-s-json-rdp-traffic-success-eventtyperdp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-rdp-traffic-success-eventtyperdp&type=code)
- [suricata-s-json-smtp-start-success-eventtypesmtp](CodeChanges/suricata-s-json-smtp-start-success-eventtypesmtp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-smtp-start-success-eventtypesmtp&type=code)
- [suricata-s-json-ssh-traffic-success-eventtypessh](CodeChanges/suricata-s-json-ssh-traffic-success-eventtypessh.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-ssh-traffic-success-eventtypessh&type=code)
- [suricata-s-json-vpn-authentication-success-eventtypeike](CodeChanges/suricata-s-json-vpn-authentication-success-eventtypeike.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20suricata-s-json-vpn-authentication-success-eventtypeike&type=code)
- [symantec-esc-json-email-inboundoutbound](CodeChanges/symantec-esc-json-email-inboundoutbound.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-esc-json-email-inboundoutbound&type=code)

#### Changed
<a id="changed"></a>
- [cisco-ie-kv-email-send-receive-summary](CodeChanges/cisco-ie-kv-email-send-receive-summary.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cisco-ie-kv-email-send-receive-summary&type=code)
- [dtexsystems-intercept-str-file-process-success-userdept](CodeChanges/dtexsystems-intercept-str-file-process-success-userdept.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20dtexsystems-intercept-str-file-process-success-userdept&type=code)
- [fortinet-fortiweb-kv-alert-trigger-success-attack](CodeChanges/fortinet-fortiweb-kv-alert-trigger-success-attack.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortiweb-kv-alert-trigger-success-attack&type=code)
- [hp-arubaos-str-ssh-start-session](CodeChanges/hp-arubaos-str-ssh-start-session.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20hp-arubaos-str-ssh-start-session&type=code)
- [microsoft-evsecurity-json-endpoint-login-fail-4625](CodeChanges/microsoft-evsecurity-json-endpoint-login-fail-4625.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-endpoint-login-fail-4625&type=code)
- [microsoft-evsecurity-mix-user-password-modify-4723](CodeChanges/microsoft-evsecurity-mix-user-password-modify-4723.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-mix-user-password-modify-4723&type=code)
- [netskope-sc-str-http-session-success-cloudapptransaction](CodeChanges/netskope-sc-str-http-session-success-cloudapptransaction.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-http-session-success-cloudapptransaction&type=code)
- [netskope-sc-str-http-session-success-transaction](CodeChanges/netskope-sc-str-http-session-success-transaction.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-http-session-success-transaction&type=code)
- [netskope-sc-str-http-session-success-webtransaction](CodeChanges/netskope-sc-str-http-session-success-webtransaction.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-http-session-success-webtransaction&type=code)
- [netskope-sc-str-http-session-websocket](CodeChanges/netskope-sc-str-http-session-websocket.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-str-http-session-websocket&type=code)

#### Changed Parsed Fields
<a id="changed-parsed-fields"></a>
- [amazon-awscloudtrail-json-image-modify-imageattribute](CodeChanges/amazon-awscloudtrail-json-image-modify-imageattribute.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20amazon-awscloudtrail-json-image-modify-imageattribute&type=code)
- [aws-waf-json-http-session-httprequest](CodeChanges/aws-waf-json-http-session-httprequest.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20aws-waf-json-http-session-httprequest&type=code)
- [checkpoint-ngfw-kv-network-traffic-fail-reject-1](CodeChanges/checkpoint-ngfw-kv-network-traffic-fail-reject-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20checkpoint-ngfw-kv-network-traffic-fail-reject-1&type=code)
- [checkpoint-ngfw-kv-network-traffic-success-accept-1](CodeChanges/checkpoint-ngfw-kv-network-traffic-success-accept-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20checkpoint-ngfw-kv-network-traffic-success-accept-1&type=code)
- [crowdstrike-falcon-json-process-close-terminateprocess](CodeChanges/crowdstrike-falcon-json-process-close-terminateprocess.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-process-close-terminateprocess&type=code)
- [crowdstrike-falcon-json-process-create-processrollup2stats](CodeChanges/crowdstrike-falcon-json-process-create-processrollup2stats.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-process-create-processrollup2stats&type=code)
- [crowdstrike-falcon-json-process-create-success-processrollup](CodeChanges/crowdstrike-falcon-json-process-create-success-processrollup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-process-create-success-processrollup&type=code)
- [crowdstrike-falcon-json-process-create-syntheticprocessrollup2](CodeChanges/crowdstrike-falcon-json-process-create-syntheticprocessrollup2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-process-create-syntheticprocessrollup2&type=code)
- [cyberark-pam-cef-user-switch-success-safe](CodeChanges/cyberark-pam-cef-user-switch-success-safe.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-pam-cef-user-switch-success-safe&type=code)
- [fortinet-fortigate-kv-endpoint-login-success-fail-systemevent](CodeChanges/fortinet-fortigate-kv-endpoint-login-success-fail-systemevent.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-endpoint-login-success-fail-systemevent&type=code)
- [fortinet-fortigate-kv-endpoint-logout-success-systemevent](CodeChanges/fortinet-fortigate-kv-endpoint-logout-success-systemevent.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-endpoint-logout-success-systemevent&type=code)
- [fortinet-fortigate-kv-network-app-fortigate](CodeChanges/fortinet-fortigate-kv-network-app-fortigate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-app-fortigate&type=code)
- [fortinet-fortigate-kv-network-notification-success-connectorevent](CodeChanges/fortinet-fortigate-kv-network-notification-success-connectorevent.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-notification-success-connectorevent&type=code)
- [fortinet-fortigate-kv-network-notification-success-fortiextender](CodeChanges/fortinet-fortigate-kv-network-notification-success-fortiextender.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-notification-success-fortiextender&type=code)
- [fortinet-fortigate-kv-network-notification-success-fortinet](CodeChanges/fortinet-fortigate-kv-network-notification-success-fortinet.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-notification-success-fortinet&type=code)
- [fortinet-fortigate-kv-network-notification-success-router](CodeChanges/fortinet-fortigate-kv-network-notification-success-router.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-notification-success-router&type=code)
- [fortinet-fortigate-kv-network-notification-success-system](CodeChanges/fortinet-fortigate-kv-network-notification-success-system.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-notification-success-system&type=code)
- [fortinet-fortigate-kv-network-notification-success-vpn](CodeChanges/fortinet-fortigate-kv-network-notification-success-vpn.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-notification-success-vpn&type=code)
- [fortinet-fortigate-kv-network-traffic-fail-traffic](CodeChanges/fortinet-fortigate-kv-network-traffic-fail-traffic.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-traffic-fail-traffic&type=code)
- [fortinet-fortigate-kv-network-traffic-success-traffic](CodeChanges/fortinet-fortigate-kv-network-traffic-success-traffic.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-kv-network-traffic-success-traffic&type=code)
- [github-g-json-app-activity-document_id](CodeChanges/github-g-json-app-activity-document_id.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-document_id&type=code)
- [github-g-json-app-activity-success-actorid](CodeChanges/github-g-json-app-activity-success-actorid.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-actorid&type=code)
- [github-g-json-app-activity-success-apirequest](CodeChanges/github-g-json-app-activity-success-apirequest.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-apirequest&type=code)
- [github-g-json-app-activity-success-integrationinstallation](CodeChanges/github-g-json-app-activity-success-integrationinstallation.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-integrationinstallation&type=code)
- [github-g-json-app-activity-success-preparedworkflowjob](CodeChanges/github-g-json-app-activity-success-preparedworkflowjob.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-preparedworkflowjob&type=code)
- [github-g-json-app-activity-success-pullrequestcreatereviewrequest](CodeChanges/github-g-json-app-activity-success-pullrequestcreatereviewrequest.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-pullrequestcreatereviewrequest&type=code)
- [github-g-json-app-activity-success-pullrequestreviewcommentcreate](CodeChanges/github-g-json-app-activity-success-pullrequestreviewcommentcreate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-pullrequestreviewcommentcreate&type=code)
- [github-g-json-app-activity-success-pullrequestreviewsubmit](CodeChanges/github-g-json-app-activity-success-pullrequestreviewsubmit.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-pullrequestreviewsubmit&type=code)
- [github-g-json-app-activity-success-secretscanningalert](CodeChanges/github-g-json-app-activity-success-secretscanningalert.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-secretscanningalert&type=code)
- [github-g-json-app-activity-success-secretscanningpushprotectionbypass](CodeChanges/github-g-json-app-activity-success-secretscanningpushprotectionbypass.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-secretscanningpushprotectionbypass&type=code)
- [github-g-json-app-activity-success-workflowscompletedworkflowrun](CodeChanges/github-g-json-app-activity-success-workflowscompletedworkflowrun.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-workflowscompletedworkflowrun&type=code)
- [github-g-json-app-activity-success-workflowscreatedworkflowrun](CodeChanges/github-g-json-app-activity-success-workflowscreatedworkflowrun.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-activity-success-workflowscreatedworkflowrun&type=code)
- [github-g-json-app-authentication-fail-accessrevoked](CodeChanges/github-g-json-app-authentication-fail-accessrevoked.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-authentication-fail-accessrevoked&type=code)
- [github-g-json-app-authentication-fail-authorizationdeauthorize](CodeChanges/github-g-json-app-authentication-fail-authorizationdeauthorize.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-authentication-fail-authorizationdeauthorize&type=code)
- [github-g-json-app-authentication-success-accessgranted](CodeChanges/github-g-json-app-authentication-success-accessgranted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-authentication-success-accessgranted&type=code)
- [github-g-json-app-authentication-success-authorizationgrant](CodeChanges/github-g-json-app-authentication-success-authorizationgrant.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-authentication-success-authorizationgrant&type=code)
- [github-g-json-app-authentication-success-businessssoresponse](CodeChanges/github-g-json-app-authentication-success-businessssoresponse.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-authentication-success-businessssoresponse&type=code)
- [github-g-json-app-notification-success-packagespackageversionpublished](CodeChanges/github-g-json-app-notification-success-packagespackageversionpublished.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-app-notification-success-packagespackageversionpublished&type=code)
- [github-g-json-branch-create-success-pullrequestcreate](CodeChanges/github-g-json-branch-create-success-pullrequestcreate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-branch-create-success-pullrequestcreate&type=code)
- [github-g-json-branch-modify-success-pullrequestmerge](CodeChanges/github-g-json-branch-modify-success-pullrequestmerge.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-branch-modify-success-pullrequestmerge&type=code)
- [github-g-json-branch-protection-disable-success-protectedbranchdestroy](CodeChanges/github-g-json-branch-protection-disable-success-protectedbranchdestroy.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-branch-protection-disable-success-protectedbranchdestroy&type=code)
- [github-g-json-branch-protection-enable-success-protectedbranchcreate](CodeChanges/github-g-json-branch-protection-enable-success-protectedbranchcreate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-branch-protection-enable-success-protectedbranchcreate&type=code)
- [github-g-json-branch-protection-modify-success-policyoverride](CodeChanges/github-g-json-branch-protection-modify-success-policyoverride.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-branch-protection-modify-success-policyoverride&type=code)
- [github-g-json-configuration-create-success-environmentcreate](CodeChanges/github-g-json-configuration-create-success-environmentcreate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-configuration-create-success-environmentcreate&type=code)
- [github-g-json-configuration-modify-success-addprotectionrule](CodeChanges/github-g-json-configuration-modify-success-addprotectionrule.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-configuration-modify-success-addprotectionrule&type=code)
- [github-g-json-configuration-modify-success-environmentupdateactionssecret](CodeChanges/github-g-json-configuration-modify-success-environmentupdateactionssecret.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-configuration-modify-success-environmentupdateactionssecret&type=code)
- [github-g-json-configuration-modify-success-updateprotectionrule](CodeChanges/github-g-json-configuration-modify-success-updateprotectionrule.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-configuration-modify-success-updateprotectionrule&type=code)
- [github-g-json-hook-delete-success-hookdestroy](CodeChanges/github-g-json-hook-delete-success-hookdestroy.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-hook-delete-success-hookdestroy&type=code)
- [github-g-json-hook-modify-success-hookconfigchanged](CodeChanges/github-g-json-hook-modify-success-hookconfigchanged.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-hook-modify-success-hookconfigchanged&type=code)
- [github-g-json-repository-create-success-gitclone](CodeChanges/github-g-json-repository-create-success-gitclone.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-repository-create-success-gitclone&type=code)
- [github-g-json-repository-pull-success-gitfetch](CodeChanges/github-g-json-repository-pull-success-gitfetch.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-repository-pull-success-gitfetch&type=code)
- [github-g-json-repository-pull-success-repodownloadzip](CodeChanges/github-g-json-repository-pull-success-repodownloadzip.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-repository-pull-success-repodownloadzip&type=code)
- [github-g-json-repository-push-success-gitpush](CodeChanges/github-g-json-repository-push-success-gitpush.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-repository-push-success-gitpush&type=code)
- [github-g-json-user-invite-success-org](CodeChanges/github-g-json-user-invite-success-org.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20github-g-json-user-invite-success-org&type=code)
- [hp-arubacpm-kv-radius-traffic-fail-authenticationfailed](CodeChanges/hp-arubacpm-kv-radius-traffic-fail-authenticationfailed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20hp-arubacpm-kv-radius-traffic-fail-authenticationfailed&type=code)
- [imperva-securesphere-leef-alert-trigger-success-alertdescription](CodeChanges/imperva-securesphere-leef-alert-trigger-success-alertdescription.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20imperva-securesphere-leef-alert-trigger-success-alertdescription&type=code)
- [mcafee-wg-kv-http-session-success-mwgaccess3](CodeChanges/mcafee-wg-kv-http-session-success-mwgaccess3.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20mcafee-wg-kv-http-session-success-mwgaccess3&type=code)
- [microsoft-azure-json-file-success-1](CodeChanges/microsoft-azure-json-file-success-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azure-json-file-success-1&type=code)
- [microsoft-azure-json-file-success-2](CodeChanges/microsoft-azure-json-file-success-2.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azure-json-file-success-2&type=code)
- [microsoft-azure-sk4-app-file-success-keybackup](CodeChanges/microsoft-azure-sk4-app-file-success-keybackup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azure-sk4-app-file-success-keybackup&type=code)
- [microsoft-azure-sk4-app-file-success-secretget](CodeChanges/microsoft-azure-sk4-app-file-success-secretget.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azure-sk4-app-file-success-secretget&type=code)
- [microsoft-azuremon-cef-app-activity-storageanalyticsdownloaded](CodeChanges/microsoft-azuremon-cef-app-activity-storageanalyticsdownloaded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azuremon-cef-app-activity-storageanalyticsdownloaded&type=code)
- [microsoft-defenderep-cef-service-create-serviceinstalled](CodeChanges/microsoft-defenderep-cef-service-create-serviceinstalled.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-defenderep-cef-service-create-serviceinstalled&type=code)
- [microsoft-defenderep-json-service-create-serviceinstalled](CodeChanges/microsoft-defenderep-json-service-create-serviceinstalled.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-defenderep-json-service-create-serviceinstalled&type=code)
- [microsoft-evsecurity-json-policy-modify-4946](CodeChanges/microsoft-evsecurity-json-policy-modify-4946.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-policy-modify-4946&type=code)
- [microsoft-evsecurity-json-service-create-success-4697](CodeChanges/microsoft-evsecurity-json-service-create-success-4697.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-service-create-success-4697&type=code)
- [microsoft-evsecurity-kv-service-create-success-4697](CodeChanges/microsoft-evsecurity-kv-service-create-success-4697.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-service-create-success-4697&type=code)
- [microsoft-evsecurity-kv-service-create-success-4697-1](CodeChanges/microsoft-evsecurity-kv-service-create-success-4697-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-service-create-success-4697-1&type=code)
- [microsoft-evsecurity-xml-endpoint-authentication-4774](CodeChanges/microsoft-evsecurity-xml-endpoint-authentication-4774.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-xml-endpoint-authentication-4774&type=code)
- [microsoft-evsecurity-xml-service-create-success-4697](CodeChanges/microsoft-evsecurity-xml-service-create-success-4697.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-xml-service-create-success-4697&type=code)
- [microsoft-evsystem-json-service-create-success-7045](CodeChanges/microsoft-evsystem-json-service-create-success-7045.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-json-service-create-success-7045&type=code)
- [microsoft-evsystem-kv-service-create-success-7045](CodeChanges/microsoft-evsystem-kv-service-create-success-7045.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-kv-service-create-success-7045&type=code)
- [microsoft-evsystem-str-service-create-success-7045](CodeChanges/microsoft-evsystem-str-service-create-success-7045.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-str-service-create-success-7045&type=code)
- [microsoft-evsystem-xml-service-create-success-7045](CodeChanges/microsoft-evsystem-xml-service-create-success-7045.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsystem-xml-service-create-success-7045&type=code)
- [microsoft-m365auditlogs-kv-app-login-success-teamssessionstarted](CodeChanges/microsoft-m365auditlogs-kv-app-login-success-teamssessionstarted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-app-login-success-teamssessionstarted&type=code)
- [microsoft-m365auditlogs-kv-app-notification-success-pageprefetched](CodeChanges/microsoft-m365auditlogs-kv-app-notification-success-pageprefetched.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-app-notification-success-pageprefetched&type=code)
- [microsoft-m365auditlogs-kv-email-send-sendas](CodeChanges/microsoft-m365auditlogs-kv-email-send-sendas.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-email-send-sendas&type=code)
- [microsoft-m365auditlogs-kv-email-send-success-send](CodeChanges/microsoft-m365auditlogs-kv-email-send-success-send.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-email-send-success-send&type=code)
- [microsoft-m365auditlogs-kv-email-send-success-sendonbehalf](CodeChanges/microsoft-m365auditlogs-kv-email-send-success-sendonbehalf.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-email-send-success-sendonbehalf&type=code)
- [microsoft-m365auditlogs-kv-endpoint-modify-success-updatedevice](CodeChanges/microsoft-m365auditlogs-kv-endpoint-modify-success-updatedevice.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-endpoint-modify-success-updatedevice&type=code)
- [microsoft-m365auditlogs-kv-file-download-filesyncdownloadedfull](CodeChanges/microsoft-m365auditlogs-kv-file-download-filesyncdownloadedfull.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-file-download-filesyncdownloadedfull&type=code)
- [microsoft-m365auditlogs-kv-file-download-success-exportartifact](CodeChanges/microsoft-m365auditlogs-kv-file-download-success-exportartifact.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-file-download-success-exportartifact&type=code)
- [microsoft-m365auditlogs-kv-file-property-modify-sharinginheritancebroken](CodeChanges/microsoft-m365auditlogs-kv-file-property-modify-sharinginheritancebroken.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-file-property-modify-sharinginheritancebroken&type=code)
- [microsoft-m365auditlogs-kv-file-read-success-pageviewed](CodeChanges/microsoft-m365auditlogs-kv-file-read-success-pageviewed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-file-read-success-pageviewed&type=code)
- [microsoft-m365auditlogs-kv-file-share-sharingset](CodeChanges/microsoft-m365auditlogs-kv-file-share-sharingset.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-file-share-sharingset&type=code)
- [microsoft-m365auditlogs-kv-file-unshare-sharingrevoked](CodeChanges/microsoft-m365auditlogs-kv-file-unshare-sharingrevoked.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-file-unshare-sharingrevoked&type=code)
- [microsoft-m365auditlogs-kv-group-create-groupadded](CodeChanges/microsoft-m365auditlogs-kv-group-create-groupadded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-group-create-groupadded&type=code)
- [microsoft-m365auditlogs-kv-group-member-add-addedtogroup](CodeChanges/microsoft-m365auditlogs-kv-group-member-add-addedtogroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-group-member-add-addedtogroup&type=code)
- [microsoft-m365auditlogs-kv-group-modify-updategroup](CodeChanges/microsoft-m365auditlogs-kv-group-modify-updategroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-group-modify-updategroup&type=code)
- [microsoft-m365auditlogs-kv-mailbox-item-create-create](CodeChanges/microsoft-m365auditlogs-kv-mailbox-item-create-create.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-mailbox-item-create-create&type=code)
- [microsoft-m365auditlogs-kv-report-read-success-viewreport](CodeChanges/microsoft-m365auditlogs-kv-report-read-success-viewreport.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-report-read-success-viewreport&type=code)
- [microsoft-m365auditlogs-kv-share-link-open-success-companylinkused](CodeChanges/microsoft-m365auditlogs-kv-share-link-open-success-companylinkused.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-share-link-open-success-companylinkused&type=code)
- [microsoft-m365auditlogs-kv-user-create-adduser](CodeChanges/microsoft-m365auditlogs-kv-user-create-adduser.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-user-create-adduser&type=code)
- [microsoft-m365auditlogs-kv-user-modify-changeuserlicense](CodeChanges/microsoft-m365auditlogs-kv-user-modify-changeuserlicense.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-user-modify-changeuserlicense&type=code)
- [microsoft-m365auditlogs-kv-user-modify-success-updateserviceprincipal](CodeChanges/microsoft-m365auditlogs-kv-user-modify-success-updateserviceprincipal.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-user-modify-success-updateserviceprincipal&type=code)
- [microsoft-m365auditlogs-kv-user-modify-success-updatestsrefreshtokenvalidfromtimestamp](CodeChanges/microsoft-m365auditlogs-kv-user-modify-success-updatestsrefreshtokenvalidfromtimestamp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-user-modify-success-updatestsrefreshtokenvalidfromtimestamp&type=code)
- [microsoft-m365auditlogs-kv-user-modify-updateuser](CodeChanges/microsoft-m365auditlogs-kv-user-modify-updateuser.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-m365auditlogs-kv-user-modify-updateuser&type=code)
- [microsoft-nps-xml-radius-traffic-fail-6274](CodeChanges/microsoft-nps-xml-radius-traffic-fail-6274.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-nps-xml-radius-traffic-fail-6274&type=code)
- [microsoft-o365-cef-app-file-success-addapplication](CodeChanges/microsoft-o365-cef-app-file-success-addapplication.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-addapplication&type=code)
- [microsoft-o365-cef-app-file-success-addgroup](CodeChanges/microsoft-o365-cef-app-file-success-addgroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-addgroup&type=code)
- [microsoft-o365-cef-app-file-success-addmembertorole](CodeChanges/microsoft-o365-cef-app-file-success-addmembertorole.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-addmembertorole&type=code)
- [microsoft-o365-cef-app-file-success-addownertogroup](CodeChanges/microsoft-o365-cef-app-file-success-addownertogroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-addownertogroup&type=code)
- [microsoft-o365-cef-app-file-success-addtogroup](CodeChanges/microsoft-o365-cef-app-file-success-addtogroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-addtogroup&type=code)
- [microsoft-o365-cef-app-file-success-adduser](CodeChanges/microsoft-o365-cef-app-file-success-adduser.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-adduser&type=code)
- [microsoft-o365-cef-app-file-success-channeladded](CodeChanges/microsoft-o365-cef-app-file-success-channeladded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-channeladded&type=code)
- [microsoft-o365-cef-app-file-success-channeldeleted](CodeChanges/microsoft-o365-cef-app-file-success-channeldeleted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-channeldeleted&type=code)
- [microsoft-o365-cef-app-file-success-crmdefaultactivity](CodeChanges/microsoft-o365-cef-app-file-success-crmdefaultactivity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-crmdefaultactivity&type=code)
- [microsoft-o365-cef-app-file-success-deletegroup](CodeChanges/microsoft-o365-cef-app-file-success-deletegroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-deletegroup&type=code)
- [microsoft-o365-cef-app-file-success-deleteuser](CodeChanges/microsoft-o365-cef-app-file-success-deleteuser.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-deleteuser&type=code)
- [microsoft-o365-cef-app-file-success-displayname](CodeChanges/microsoft-o365-cef-app-file-success-displayname.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-displayname&type=code)
- [microsoft-o365-cef-app-file-success-downloadreport](CodeChanges/microsoft-o365-cef-app-file-success-downloadreport.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-downloadreport&type=code)
- [microsoft-o365-cef-app-file-success-filedeleted](CodeChanges/microsoft-o365-cef-app-file-success-filedeleted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-filedeleted&type=code)
- [microsoft-o365-cef-app-file-success-filemodified](CodeChanges/microsoft-o365-cef-app-file-success-filemodified.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-filemodified&type=code)
- [microsoft-o365-cef-app-file-success-filemoved](CodeChanges/microsoft-o365-cef-app-file-success-filemoved.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-filemoved&type=code)
- [microsoft-o365-cef-app-file-success-filerenamed](CodeChanges/microsoft-o365-cef-app-file-success-filerenamed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-filerenamed&type=code)
- [microsoft-o365-cef-app-file-success-filesyncuploadedfull](CodeChanges/microsoft-o365-cef-app-file-success-filesyncuploadedfull.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-filesyncuploadedfull&type=code)
- [microsoft-o365-cef-app-file-success-fileupload](CodeChanges/microsoft-o365-cef-app-file-success-fileupload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-fileupload&type=code)
- [microsoft-o365-cef-app-file-success-foldercreated](CodeChanges/microsoft-o365-cef-app-file-success-foldercreated.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-foldercreated&type=code)
- [microsoft-o365-cef-app-file-success-groupupload](CodeChanges/microsoft-o365-cef-app-file-success-groupupload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-groupupload&type=code)
- [microsoft-o365-cef-app-file-success-harddelete](CodeChanges/microsoft-o365-cef-app-file-success-harddelete.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-harddelete&type=code)
- [microsoft-o365-cef-app-file-success-memberadded](CodeChanges/microsoft-o365-cef-app-file-success-memberadded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-memberadded&type=code)
- [microsoft-o365-cef-app-file-success-memberremoved](CodeChanges/microsoft-o365-cef-app-file-success-memberremoved.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-memberremoved&type=code)
- [microsoft-o365-cef-app-file-success-modifiedproperties](CodeChanges/microsoft-o365-cef-app-file-success-modifiedproperties.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-modifiedproperties&type=code)
- [microsoft-o365-cef-app-file-success-movetodeleteditems](CodeChanges/microsoft-o365-cef-app-file-success-movetodeleteditems.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-movetodeleteditems&type=code)
- [microsoft-o365-cef-app-file-success-refreshdataset](CodeChanges/microsoft-o365-cef-app-file-success-refreshdataset.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-refreshdataset&type=code)
- [microsoft-o365-cef-app-file-success-removememberfromgroup](CodeChanges/microsoft-o365-cef-app-file-success-removememberfromgroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-removememberfromgroup&type=code)
- [microsoft-o365-cef-app-file-success-restoreuser](CodeChanges/microsoft-o365-cef-app-file-success-restoreuser.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-restoreuser&type=code)
- [microsoft-o365-cef-app-file-success-rolechanged](CodeChanges/microsoft-o365-cef-app-file-success-rolechanged.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-rolechanged&type=code)
- [microsoft-o365-cef-app-file-success-serviceprincipal](CodeChanges/microsoft-o365-cef-app-file-success-serviceprincipal.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-serviceprincipal&type=code)
- [microsoft-o365-cef-app-file-success-tabadded](CodeChanges/microsoft-o365-cef-app-file-success-tabadded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-tabadded&type=code)
- [microsoft-o365-cef-app-file-success-tabupdated](CodeChanges/microsoft-o365-cef-app-file-success-tabupdated.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-tabupdated&type=code)
- [microsoft-o365-cef-app-file-success-updatedevice](CodeChanges/microsoft-o365-cef-app-file-success-updatedevice.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-updatedevice&type=code)
- [microsoft-o365-cef-app-file-success-updateuser](CodeChanges/microsoft-o365-cef-app-file-success-updateuser.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-updateuser&type=code)
- [microsoft-o365-cef-app-file-success-viewdashboard](CodeChanges/microsoft-o365-cef-app-file-success-viewdashboard.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-viewdashboard&type=code)
- [microsoft-o365-cef-app-file-success-viewreport](CodeChanges/microsoft-o365-cef-app-file-success-viewreport.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-file-success-viewreport&type=code)
- [microsoft-o365-cef-app-login-fail-userloginfailed](CodeChanges/microsoft-o365-cef-app-login-fail-userloginfailed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-login-fail-userloginfailed&type=code)
- [microsoft-o365-cef-app-login-success-user](CodeChanges/microsoft-o365-cef-app-login-success-user.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-app-login-success-user&type=code)
- [microsoft-o365-cef-user-password-reset-selfservice](CodeChanges/microsoft-o365-cef-user-password-reset-selfservice.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-user-password-reset-selfservice&type=code)
- [microsoft-o365-json-app-consent-grant-success-operation](CodeChanges/microsoft-o365-json-app-consent-grant-success-operation.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-app-consent-grant-success-operation&type=code)
- [microsoft-o365-json-app-modify-success-addowner](CodeChanges/microsoft-o365-json-app-modify-success-addowner.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-app-modify-success-addowner&type=code)
- [microsoft-o365-json-app-modify-success-updateapplication](CodeChanges/microsoft-o365-json-app-modify-success-updateapplication.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-app-modify-success-updateapplication&type=code)
- [microsoft-o365-kv-app-login-fail-workload](CodeChanges/microsoft-o365-kv-app-login-fail-workload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-app-login-fail-workload&type=code)
- [microsoft-o365-kv-file-delete-success-filedeleted](CodeChanges/microsoft-o365-kv-file-delete-success-filedeleted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-delete-success-filedeleted&type=code)
- [microsoft-o365-kv-file-delete-success-fileversions](CodeChanges/microsoft-o365-kv-file-delete-success-fileversions.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-delete-success-fileversions&type=code)
- [microsoft-o365-kv-file-delete-success-folderdeleted](CodeChanges/microsoft-o365-kv-file-delete-success-folderdeleted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-delete-success-folderdeleted&type=code)
- [microsoft-o365-kv-file-read-success-anonymouslinkused](CodeChanges/microsoft-o365-kv-file-read-success-anonymouslinkused.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-anonymouslinkused&type=code)
- [microsoft-o365-kv-file-read-success-clientviewsignaled](CodeChanges/microsoft-o365-kv-file-read-success-clientviewsignaled.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-clientviewsignaled&type=code)
- [microsoft-o365-kv-file-read-success-fileaccessed](CodeChanges/microsoft-o365-kv-file-read-success-fileaccessed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-fileaccessed&type=code)
- [microsoft-o365-kv-file-read-success-fileaccessedextended](CodeChanges/microsoft-o365-kv-file-read-success-fileaccessedextended.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-fileaccessedextended&type=code)
- [microsoft-o365-kv-file-read-success-filedownloaded](CodeChanges/microsoft-o365-kv-file-read-success-filedownloaded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-filedownloaded&type=code)
- [microsoft-o365-kv-file-read-success-filepreviewed](CodeChanges/microsoft-o365-kv-file-read-success-filepreviewed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-filepreviewed&type=code)
- [microsoft-o365-kv-file-read-success-pageviewed](CodeChanges/microsoft-o365-kv-file-read-success-pageviewed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-pageviewed&type=code)
- [microsoft-o365-kv-file-read-success-pageviewedextended](CodeChanges/microsoft-o365-kv-file-read-success-pageviewedextended.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-kv-file-read-success-pageviewedextended&type=code)
- [microsoft-o365-sk4-app-activity-success-addedtogroup](CodeChanges/microsoft-o365-sk4-app-activity-success-addedtogroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-activity-success-addedtogroup&type=code)
- [microsoft-o365-sk4-app-addowner](CodeChanges/microsoft-o365-sk4-app-addowner.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-addowner&type=code)
- [microsoft-o365-sk4-app-approleassign](CodeChanges/microsoft-o365-sk4-app-approleassign.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-approleassign&type=code)
- [microsoft-o365-sk4-app-file-move](CodeChanges/microsoft-o365-sk4-app-file-move.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-move&type=code)
- [microsoft-o365-sk4-app-file-send](CodeChanges/microsoft-o365-sk4-app-file-send.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-send&type=code)
- [microsoft-o365-sk4-app-file-setunifiedgroup](CodeChanges/microsoft-o365-sk4-app-file-setunifiedgroup.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-setunifiedgroup&type=code)
- [microsoft-o365-sk4-app-file-success-deviceupdate](CodeChanges/microsoft-o365-sk4-app-file-success-deviceupdate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-deviceupdate&type=code)
- [microsoft-o365-sk4-app-file-success-group](CodeChanges/microsoft-o365-sk4-app-file-success-group.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-group&type=code)
- [microsoft-o365-sk4-app-file-success-groupadd](CodeChanges/microsoft-o365-sk4-app-file-success-groupadd.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-groupadd&type=code)
- [microsoft-o365-sk4-app-file-success-groupunassign](CodeChanges/microsoft-o365-sk4-app-file-success-groupunassign.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-groupunassign&type=code)
- [microsoft-o365-sk4-app-file-success-groupupdate](CodeChanges/microsoft-o365-sk4-app-file-success-groupupdate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-groupupdate&type=code)
- [microsoft-o365-sk4-app-file-success-useradd](CodeChanges/microsoft-o365-sk4-app-file-success-useradd.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-useradd&type=code)
- [microsoft-o365-sk4-app-file-success-userdelete](CodeChanges/microsoft-o365-sk4-app-file-success-userdelete.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-userdelete&type=code)
- [microsoft-o365-sk4-app-file-success-userrestore](CodeChanges/microsoft-o365-sk4-app-file-success-userrestore.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-userrestore&type=code)
- [microsoft-o365-sk4-app-file-success-userupdate](CodeChanges/microsoft-o365-sk4-app-file-success-userupdate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-file-success-userupdate&type=code)
- [microsoft-o365-sk4-file-write-success-filemodified](CodeChanges/microsoft-o365-sk4-file-write-success-filemodified.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-file-write-success-filemodified&type=code)
- [microsoft-o365-sk4-file-write-success-fileuploaded](CodeChanges/microsoft-o365-sk4-file-write-success-fileuploaded.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-file-write-success-fileuploaded&type=code)
- [microsoft-windows-json-service-create-success-4697](CodeChanges/microsoft-windows-json-service-create-success-4697.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-windows-json-service-create-success-4697&type=code)
- [netskope-sc-json-alert-trigger-success-alertname](CodeChanges/netskope-sc-json-alert-trigger-success-alertname.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-json-alert-trigger-success-alertname&type=code)
- [netskope-sc-sk4-alert-trigger-success-actdetect](CodeChanges/netskope-sc-sk4-alert-trigger-success-actdetect.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-sk4-alert-trigger-success-actdetect&type=code)
- [okta-amfa-cef-alert-trigger-success-threatdetected](CodeChanges/okta-amfa-cef-alert-trigger-success-threatdetected.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-alert-trigger-success-threatdetected&type=code)
- [okta-amfa-cef-app-login-fail-appadloginbadpassword](CodeChanges/okta-amfa-cef-app-login-fail-appadloginbadpassword.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-app-login-fail-appadloginbadpassword&type=code)
- [okta-amfa-cef-app-login-fail-apprichclientloginfailure](CodeChanges/okta-amfa-cef-app-login-fail-apprichclientloginfailure.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-app-login-fail-apprichclientloginfailure&type=code)
- [okta-amfa-cef-app-login-fail-coreuserauthloginfailed](CodeChanges/okta-amfa-cef-app-login-fail-coreuserauthloginfailed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-app-login-fail-coreuserauthloginfailed&type=code)
- [okta-amfa-cef-app-login-success-appadloginsuccess](CodeChanges/okta-amfa-cef-app-login-success-appadloginsuccess.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-app-login-success-appadloginsuccess&type=code)
- [okta-amfa-cef-app-login-success-coreuserauthloginsuccess](CodeChanges/okta-amfa-cef-app-login-success-coreuserauthloginsuccess.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-app-login-success-coreuserauthloginsuccess&type=code)
- [okta-amfa-cef-app-login-success-userauthverify](CodeChanges/okta-amfa-cef-app-login-success-userauthverify.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-app-login-success-userauthverify&type=code)
- [okta-amfa-cef-user-password-reset-success-pwdreset](CodeChanges/okta-amfa-cef-user-password-reset-success-pwdreset.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-user-password-reset-success-pwdreset&type=code)
- [okta-amfa-cef-user-password-reset-success-pwdreset-1](CodeChanges/okta-amfa-cef-user-password-reset-success-pwdreset-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-cef-user-password-reset-success-pwdreset-1&type=code)
- [okta-amfa-mix-app-login-success-securitycontext](CodeChanges/okta-amfa-mix-app-login-success-securitycontext.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfa-mix-app-login-success-securitycontext&type=code)
- [okta-amfg-cef-endpoint-login-fail-attemptfail](CodeChanges/okta-amfg-cef-endpoint-login-fail-attemptfail.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfg-cef-endpoint-login-fail-attemptfail&type=code)
- [okta-amfg-cef-endpoint-login-fail-auth](CodeChanges/okta-amfg-cef-endpoint-login-fail-auth.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfg-cef-endpoint-login-fail-auth&type=code)
- [okta-amfg-cef-endpoint-login-fail-invalidtoken](CodeChanges/okta-amfg-cef-endpoint-login-fail-invalidtoken.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfg-cef-endpoint-login-fail-invalidtoken&type=code)
- [okta-amfg-cef-endpoint-login-success-attemptsuccess](CodeChanges/okta-amfg-cef-endpoint-login-success-attemptsuccess.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfg-cef-endpoint-login-success-attemptsuccess&type=code)
- [okta-amfg-json-endpoint-login-success-authviaradius](CodeChanges/okta-amfg-json-endpoint-login-success-authviaradius.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20okta-amfg-json-endpoint-login-success-authviaradius&type=code)
- [proofpoint-tap-json-email-receive-fail-threat](CodeChanges/proofpoint-tap-json-email-receive-fail-threat.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20proofpoint-tap-json-email-receive-fail-threat&type=code)
- [proofpoint-tap-json-email-receive-fail-threatinsight](CodeChanges/proofpoint-tap-json-email-receive-fail-threatinsight.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20proofpoint-tap-json-email-receive-fail-threatinsight&type=code)
- [symantec-dlp-csv-file-write-success-filewrite](CodeChanges/symantec-dlp-csv-file-write-success-filewrite.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-dlp-csv-file-write-success-filewrite&type=code)
- [symantec-dlp-kv-peripheral-storage-insert-success-allowedthedevice](CodeChanges/symantec-dlp-kv-peripheral-storage-insert-success-allowedthedevice.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-dlp-kv-peripheral-storage-insert-success-allowedthedevice&type=code)
- [symantec-endpointprotection-csv-app-activity-success-user1](CodeChanges/symantec-endpointprotection-csv-app-activity-success-user1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-csv-app-activity-success-user1&type=code)
- [symantec-endpointprotection-kv-alert-trigger-success-requestedaction](CodeChanges/symantec-endpointprotection-kv-alert-trigger-success-requestedaction.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-kv-alert-trigger-success-requestedaction&type=code)
- [symantec-endpointprotection-kv-alert-trigger-success-scanningyourcomputer](CodeChanges/symantec-endpointprotection-kv-alert-trigger-success-scanningyourcomputer.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-kv-alert-trigger-success-scanningyourcomputer&type=code)
- [symantec-endpointprotection-kv-app-notification-eventdescription](CodeChanges/symantec-endpointprotection-kv-app-notification-eventdescription.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-kv-app-notification-eventdescription&type=code)
- [symantec-endpointprotection-kv-network-traffic-fail-block](CodeChanges/symantec-endpointprotection-kv-network-traffic-fail-block.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-kv-network-traffic-fail-block&type=code)
- [symantec-endpointprotection-kv-network-traffic-fail-rule](CodeChanges/symantec-endpointprotection-kv-network-traffic-fail-rule.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-kv-network-traffic-fail-rule&type=code)
- [symantec-esc-json-email-receive-success-emailinfo](CodeChanges/symantec-esc-json-email-receive-success-emailinfo.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-esc-json-email-receive-success-emailinfo&type=code)
- [trendmicro-ds-json-service-create-success-7045](CodeChanges/trendmicro-ds-json-service-create-success-7045.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20trendmicro-ds-json-service-create-success-7045&type=code)
- [unix-unix-str-group-member-add-success-usermod](CodeChanges/unix-unix-str-group-member-add-success-usermod.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20unix-unix-str-group-member-add-success-usermod&type=code)
- [unix-unix-str-group-member-add-success-usermod-1](CodeChanges/unix-unix-str-group-member-add-success-usermod-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20unix-unix-str-group-member-add-success-usermod-1&type=code)
- [unix-unix-str-user-modify-usermod](CodeChanges/unix-unix-str-user-modify-usermod.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20unix-unix-str-user-modify-usermod&type=code)

#### Edit Attribute
<a id="edit-attribute"></a>
- [catonetworks-cc-json-vpn-http-success-connectivity](CodeChanges/catonetworks-cc-json-vpn-http-success-connectivity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20catonetworks-cc-json-vpn-http-success-connectivity&type=code)
- [cisco-cucm-kv-endpoint-authentication-fail-userlogging](CodeChanges/cisco-cucm-kv-endpoint-authentication-fail-userlogging.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cisco-cucm-kv-endpoint-authentication-fail-userlogging&type=code)
- [cisco-mma-kv-http-session-success-dst](CodeChanges/cisco-mma-kv-http-session-success-dst.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cisco-mma-kv-http-session-success-dst&type=code)
- [dell-sw-kv-alert-trigger-success-security](CodeChanges/dell-sw-kv-alert-trigger-success-security.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20dell-sw-kv-alert-trigger-success-security&type=code)
- [microsoft-azure-json-key-success-keyvault](CodeChanges/microsoft-azure-json-key-success-keyvault.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azure-json-key-success-keyvault&type=code)
- [microsoft-defenderep-sk4-registry-modify-advancedhunting](CodeChanges/microsoft-defenderep-sk4-registry-modify-advancedhunting.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-defenderep-sk4-registry-modify-advancedhunting&type=code)
- [microsoft-evsecurity-cef-service-create-success-5478](CodeChanges/microsoft-evsecurity-cef-service-create-success-5478.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-cef-service-create-success-5478&type=code)
- [microsoft-evsecurity-json-service-create-success-5478](CodeChanges/microsoft-evsecurity-json-service-create-success-5478.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-json-service-create-success-5478&type=code)
- [microsoft-evsecurity-kv-service-create-success-5478](CodeChanges/microsoft-evsecurity-kv-service-create-success-5478.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-service-create-success-5478&type=code)
- [microsoft-evsecurity-xml-service-create-success-5478](CodeChanges/microsoft-evsecurity-xml-service-create-success-5478.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-xml-service-create-success-5478&type=code)
- [microsoft-evsecurity-xml-service-create-success-5478-1](CodeChanges/microsoft-evsecurity-xml-service-create-success-5478-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-xml-service-create-success-5478-1&type=code)
- [microsoft-iis-str-http-session-deleteapi](CodeChanges/microsoft-iis-str-http-session-deleteapi.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-deleteapi&type=code)
- [microsoft-iis-str-http-session-getautodiscover](CodeChanges/microsoft-iis-str-http-session-getautodiscover.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getautodiscover&type=code)
- [microsoft-iis-str-http-session-getecp](CodeChanges/microsoft-iis-str-http-session-getecp.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getecp&type=code)
- [microsoft-iis-str-http-session-getews](CodeChanges/microsoft-iis-str-http-session-getews.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getews&type=code)
- [microsoft-iis-str-http-session-getmapi](CodeChanges/microsoft-iis-str-http-session-getmapi.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getmapi&type=code)
- [microsoft-iis-str-http-session-getmicrosoftserver](CodeChanges/microsoft-iis-str-http-session-getmicrosoftserver.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getmicrosoftserver&type=code)
- [microsoft-iis-str-http-session-getoab](CodeChanges/microsoft-iis-str-http-session-getoab.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getoab&type=code)
- [microsoft-iis-str-http-session-getowa](CodeChanges/microsoft-iis-str-http-session-getowa.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getowa&type=code)
- [microsoft-iis-str-http-session-getrpc](CodeChanges/microsoft-iis-str-http-session-getrpc.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getrpc&type=code)
- [microsoft-iis-str-http-session-getsignin](CodeChanges/microsoft-iis-str-http-session-getsignin.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getsignin&type=code)
- [microsoft-iis-str-http-session-getstatus](CodeChanges/microsoft-iis-str-http-session-getstatus.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-getstatus&type=code)
- [microsoft-iis-str-http-session-headoab](CodeChanges/microsoft-iis-str-http-session-headoab.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-iis-str-http-session-headoab&type=code)

#### Edit Regex Field
<a id="edit-regex-field"></a>
- [azure-azuread-json-app-activity-useractivitydisplayname](CodeChanges/azure-azuread-json-app-activity-useractivitydisplayname.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20azure-azuread-json-app-activity-useractivitydisplayname&type=code)
- [crowdstrike-falcon-json-endpoint-login-fail-userlogonfail](CodeChanges/crowdstrike-falcon-json-endpoint-login-fail-userlogonfail.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-endpoint-login-fail-userlogonfail&type=code)
- [crowdstrike-falcon-json-endpoint-login-fail-userlogonfail-1](CodeChanges/crowdstrike-falcon-json-endpoint-login-fail-userlogonfail-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-endpoint-login-fail-userlogonfail-1&type=code)
- [crowdstrike-falcon-json-endpoint-login-userlogin](CodeChanges/crowdstrike-falcon-json-endpoint-login-userlogin.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-endpoint-login-userlogin&type=code)
- [crowdstrike-falcon-json-file-delete-success-executabledeleted](CodeChanges/crowdstrike-falcon-json-file-delete-success-executabledeleted.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-delete-success-executabledeleted&type=code)
- [crowdstrike-falcon-json-file-read-success-criticalfileaccessed-1](CodeChanges/crowdstrike-falcon-json-file-read-success-criticalfileaccessed-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-read-success-criticalfileaccessed-1&type=code)
- [crowdstrike-falcon-json-file-write-success-directorycreate](CodeChanges/crowdstrike-falcon-json-file-write-success-directorycreate.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-directorycreate&type=code)
- [crowdstrike-falcon-json-file-write-success-dmpfilewritten](CodeChanges/crowdstrike-falcon-json-file-write-success-dmpfilewritten.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-dmpfilewritten&type=code)
- [crowdstrike-falcon-json-file-write-success-dwgfilewritten](CodeChanges/crowdstrike-falcon-json-file-write-success-dwgfilewritten.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-dwgfilewritten&type=code)
- [crowdstrike-falcon-json-file-write-success-olefilewritten](CodeChanges/crowdstrike-falcon-json-file-write-success-olefilewritten.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-olefilewritten&type=code)
- [crowdstrike-falcon-json-file-write-success-ooxmlfilewritten](CodeChanges/crowdstrike-falcon-json-file-write-success-ooxmlfilewritten.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-ooxmlfilewritten&type=code)
- [crowdstrike-falcon-json-file-write-success-pdffilewritten](CodeChanges/crowdstrike-falcon-json-file-write-success-pdffilewritten.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-pdffilewritten&type=code)
- [crowdstrike-falcon-json-file-write-success-pefilewritten](CodeChanges/crowdstrike-falcon-json-file-write-success-pefilewritten.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-file-write-success-pefilewritten&type=code)
- [crowdstrike-falcon-json-process-create-success-processroll](CodeChanges/crowdstrike-falcon-json-process-create-success-processroll.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-process-create-success-processroll&type=code)
- [crowdstrike-falcon-json-process-create-success-syntheticprocessroll](CodeChanges/crowdstrike-falcon-json-process-create-success-syntheticprocessroll.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-falcon-json-process-create-success-syntheticprocessroll&type=code)
- [microsoft-azuread-cef-app-login-clientappused](CodeChanges/microsoft-azuread-cef-app-login-clientappused.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-azuread-cef-app-login-clientappused&type=code)
- [microsoft-evnps-xml-radius-traffic-success-6272](CodeChanges/microsoft-evnps-xml-radius-traffic-success-6272.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evnps-xml-radius-traffic-success-6272&type=code)
- [microsoft-evsecurity-kv-ds-object-move-success-serviceobject](CodeChanges/microsoft-evsecurity-kv-ds-object-move-success-serviceobject.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-ds-object-move-success-serviceobject&type=code)
- [microsoft-evsecurity-kv-endpoint-unlock-success-4801-5](CodeChanges/microsoft-evsecurity-kv-endpoint-unlock-success-4801-5.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-kv-endpoint-unlock-success-4801-5&type=code)
- [microsoft-evsecurity-str-endpoint-notification-4627](CodeChanges/microsoft-evsecurity-str-endpoint-notification-4627.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-str-endpoint-notification-4627&type=code)
- [microsoft-evsecurity-xml-process-create-success-4688](CodeChanges/microsoft-evsecurity-xml-process-create-success-4688.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-evsecurity-xml-process-create-success-4688&type=code)
- [microsoft-nps-xml-radius-traffic-fail-6273](CodeChanges/microsoft-nps-xml-radius-traffic-fail-6273.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-nps-xml-radius-traffic-fail-6273&type=code)
- [microsoft-o365-cef-alert-trigger-success-alertdetected](CodeChanges/microsoft-o365-cef-alert-trigger-success-alertdetected.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-cef-alert-trigger-success-alertdetected&type=code)
- [microsoft-o365-json-create-email-item-success](CodeChanges/microsoft-o365-json-create-email-item-success.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-create-email-item-success&type=code)
- [microsoft-o365-json-delete-email-message-deleteditems](CodeChanges/microsoft-o365-json-delete-email-message-deleteditems.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-delete-email-message-deleteditems&type=code)
- [microsoft-o365-json-mail-access-mailitemsaccessed](CodeChanges/microsoft-o365-json-mail-access-mailitemsaccessed.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-mail-access-mailitemsaccessed&type=code)
- [microsoft-o365-json-mailbox-permission-modify-success-workload](CodeChanges/microsoft-o365-json-mailbox-permission-modify-success-workload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-mailbox-permission-modify-success-workload&type=code)
- [microsoft-o365-json-recipient-permission-modify-permissionmodify](CodeChanges/microsoft-o365-json-recipient-permission-modify-permissionmodify.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-recipient-permission-modify-permissionmodify&type=code)
- [microsoft-o365-json-role-create-success-addroledefinition](CodeChanges/microsoft-o365-json-role-create-success-addroledefinition.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-role-create-success-addroledefinition&type=code)
- [microsoft-o365-json-share-link-create-success-workload](CodeChanges/microsoft-o365-json-share-link-create-success-workload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-share-link-create-success-workload&type=code)
- [microsoft-o365-json-share-link-member-add-success-workload](CodeChanges/microsoft-o365-json-share-link-member-add-success-workload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-share-link-member-add-success-workload&type=code)
- [microsoft-o365-json-share-link-modify-success-workload](CodeChanges/microsoft-o365-json-share-link-modify-success-workload.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-share-link-modify-success-workload&type=code)
- [microsoft-o365-json-sharing-link-used-linkused](CodeChanges/microsoft-o365-json-sharing-link-used-linkused.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-sharing-link-used-linkused&type=code)
- [microsoft-o365-json-user-permission-modify-success-adddelegatedpermission](CodeChanges/microsoft-o365-json-user-permission-modify-success-adddelegatedpermission.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-json-user-permission-modify-success-adddelegatedpermission&type=code)
- [microsoft-o365-sk4-app-activity-success-movetofolder](CodeChanges/microsoft-o365-sk4-app-activity-success-movetofolder.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-o365-sk4-app-activity-success-movetofolder&type=code)
- [netskope-sc-json-app-activity-success-share](CodeChanges/netskope-sc-json-app-activity-success-share.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20netskope-sc-json-app-activity-success-share&type=code)
- [pan-ngfw-csv-http-session-9999](CodeChanges/pan-ngfw-csv-http-session-9999.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20pan-ngfw-csv-http-session-9999&type=code)
- [postgresql-p-str-database-activity-context](CodeChanges/postgresql-p-str-database-activity-context.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-p-str-database-activity-context&type=code)
- [postgresql-p-str-database-activity-detail](CodeChanges/postgresql-p-str-database-activity-detail.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-p-str-database-activity-detail&type=code)
- [postgresql-p-str-database-activity-fatal](CodeChanges/postgresql-p-str-database-activity-fatal.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-p-str-database-activity-fatal&type=code)
- [postgresql-p-str-database-activity-log](CodeChanges/postgresql-p-str-database-activity-log.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-p-str-database-activity-log&type=code)
- [postgresql-p-str-database-login-fail-password-doesnotmatch](CodeChanges/postgresql-p-str-database-login-fail-password-doesnotmatch.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-p-str-database-login-fail-password-doesnotmatch&type=code)
- [postgresql-p-str-database-query-success-statement](CodeChanges/postgresql-p-str-database-query-success-statement.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-p-str-database-query-success-statement&type=code)
- [symantec-endpointprotection-kv-app-activity-category](CodeChanges/symantec-endpointprotection-kv-app-activity-category.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20symantec-endpointprotection-kv-app-activity-category&type=code)
- [trendmicro-tippingpoint-cef-alert-trigger-success-unityone](CodeChanges/trendmicro-tippingpoint-cef-alert-trigger-success-unityone.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20trendmicro-tippingpoint-cef-alert-trigger-success-unityone&type=code)
- [unix-unix-str-endpoint-login-sshdconnectionfrom](CodeChanges/unix-unix-str-endpoint-login-sshdconnectionfrom.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20unix-unix-str-endpoint-login-sshdconnectionfrom&type=code)

#### Removed Parser
<a id="removed-parser"></a>
- [amazon-awscloudtrail-sk4-app-activity-success-redshift](CodeChanges/amazon-awscloudtrail-sk4-app-activity-success-redshift.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20amazon-awscloudtrail-sk4-app-activity-success-redshift&type=code)
- [exabeam-aba-json-ai-agent-activity](CodeChanges/exabeam-aba-json-ai-agent-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20exabeam-aba-json-ai-agent-activity&type=code)
- [tessian-ces-json-email-send-success-outbound](CodeChanges/tessian-ces-json-email-send-success-outbound.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20tessian-ces-json-email-send-success-outbound&type=code)

</details>

<details>
<summary><strong id="parsertemplate">ParserTemplate (25)</strong></summary>


### Change Types
- [Could Not Compare](#could-not-compare)
- [Added Parser Template](#added-parser-template)
- [Changed Parsed Fields](#changed-parsed-fields)
- [Edit Attribute](#edit-attribute)
- [Edit Regex Field](#edit-regex-field)

#### Could Not Compare
<a id="could-not-compare"></a>
- [cef-microsoft-o365-app-activity](CodeChanges/cef-microsoft-o365-app-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cef-microsoft-o365-app-activity&type=code)
- [cef-microsoft-o365-app-activity-1](CodeChanges/cef-microsoft-o365-app-activity-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cef-microsoft-o365-app-activity-1&type=code)
- [microsoft-json-events](CodeChanges/microsoft-json-events.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20microsoft-json-events&type=code)
- [o365-file-app-activity](CodeChanges/o365-file-app-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20o365-file-app-activity&type=code)

#### Added Parser Template
<a id="added-parser-template"></a>
- [cyberark-events](CodeChanges/cyberark-events.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cyberark-events&type=code)
- [json-suricata-eve](CodeChanges/json-suricata-eve.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20json-suricata-eve&type=code)
- [str-netskope-alerts-events](CodeChanges/str-netskope-alerts-events.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20str-netskope-alerts-events&type=code)

#### Changed Parsed Fields
<a id="changed-parsed-fields"></a>
- [azure-classicblob-json](CodeChanges/azure-classicblob-json.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20azure-classicblob-json&type=code)
- [azure-workspaceblob-json](CodeChanges/azure-workspaceblob-json.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20azure-workspaceblob-json&type=code)
- [cef-microsoft-app-activity](CodeChanges/cef-microsoft-app-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20cef-microsoft-app-activity&type=code)
- [crowdstrike-process-info](CodeChanges/crowdstrike-process-info.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-process-info&type=code)
- [fortinet-fortigate-cef-network-traffic-info](CodeChanges/fortinet-fortigate-cef-network-traffic-info.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20fortinet-fortigate-cef-network-traffic-info&type=code)
- [json-github-actions](CodeChanges/json-github-actions.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20json-github-actions&type=code)
- [json-microsoft-app-activity](CodeChanges/json-microsoft-app-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20json-microsoft-app-activity&type=code)
- [json-okta-auth](CodeChanges/json-okta-auth.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20json-okta-auth&type=code)
- [logrhythm-o365-app-activity](CodeChanges/logrhythm-o365-app-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20logrhythm-o365-app-activity&type=code)
- [logrhythm-o365-app-activity-1](CodeChanges/logrhythm-o365-app-activity-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20logrhythm-o365-app-activity-1&type=code)
- [logrhythm-o365-file-operation](CodeChanges/logrhythm-o365-file-operation.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20logrhythm-o365-file-operation&type=code)
- [m365-activity-json](CodeChanges/m365-activity-json.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20m365-activity-json&type=code)
- [o365-activity-template](CodeChanges/o365-activity-template.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20o365-activity-template&type=code)

#### Edit Attribute
<a id="edit-attribute"></a>
- [tessian-dlp-email-alert](CodeChanges/tessian-dlp-email-alert.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20tessian-dlp-email-alert&type=code)

#### Edit Regex Field
<a id="edit-regex-field"></a>
- [crowdstrike-auth-activity](CodeChanges/crowdstrike-auth-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20crowdstrike-auth-activity&type=code)
- [m365-activity](CodeChanges/m365-activity.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20m365-activity&type=code)
- [o365-file-share-link](CodeChanges/o365-file-share-link.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20o365-file-share-link&type=code)
- [postgresql-parser-str-1](CodeChanges/postgresql-parser-str-1.md) [🔍](https://github.com/search?q=repo%3AExabeamLabs%2FContent-Library-CIM2%20postgresql-parser-str-1&type=code)

</details>