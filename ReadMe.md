 eFaxMS Graph Prototype

Used to develop and exercise MS Graph API Mail operations necessary for eFax AWS replacement, Aug 2024

## Environmental Variables: 

MSG_AUTHORITY <-- Supplied from BC Gov.  
MSG_CLIENTID <-- Supplied from BC Gov. Also referred to as a 'tenant Id'   
MSG_EMAIL_ACCOUNT <-- Supplied from BC Gov. Scoped email account to use for this demo.   
MSG_ENDPOINT <-- Always https://graph.microsoft.com/  
MSG_EXPIRY_THRESHOLD <-- Number of days out from secret key expiry date to start with the nag emails to refresh the key.  
MSG_SECRETKEY <-- The secret key  


## Notes: 

- An Azure application must be set up prior to using this prototype.
- Configuration and permissions need to be set for the application. 
- Permissions needing to be configured are tied to the set of operations called from MS Graph API (e.g., each operation has it's own set of permissions that need to be set by the application admin). 
- The Azure application for eFax will be used in app-only as opposed to delegate mode (See https://learn.microsoft.com/en-us/graph/auth-v2-service?tabs=http) 
- Access token generation uses OAuth2 - Client Credential Flow (under the hood. if using the SDKs, no need to know this).   
- Microsoft SDKs are used in this project. You could make similar calls using a purely RESTful calls however using the SDK is easier once you learn how to use it.
- If you wish, you could set up a Microsoft o365 Sandbox account to play around with this stuff before you use a BC Government provisioned Azure Application to access MS Graph. Down side to this is the email operations don't work right. I tried this first. See https://learn.microsoft.com/en-us/office/developer-program/microsoft-365-developer-program-get-started
	
### What I don't know is: 
- Are the calls to the MS Graph API blocking?	

### This application provides the following operation set: 

- GET localhost:8080/email/getMail
- POST localhost:8080/email/sendMail
- POST localhost:8080/email/deleteMail
	
See the accompanying POSTMAN collection for how to call each operation. 
	
	
	
	
	

	