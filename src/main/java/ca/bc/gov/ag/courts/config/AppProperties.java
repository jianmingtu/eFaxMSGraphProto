package ca.bc.gov.ag.courts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
public class AppProperties {
	
	@Value("${msg.clientId}")
	private String msgClientId;
	
	@Value("${msg.authority}")
	private String msgAuthority;
	
	@Value("${msg.secretKey}")
	private String msgSecretKey;
	
	@Value("${msg.endpointHost}")
	private String msgEndpointHost;
	
	@Value("${msg.email.account}")
	private String msgEmailAccount; 
	
	// Number of days before MS Graph API Secret Key Expiry Date to start sending notifications. 
	@Value("${msg.secretKey.expiry.threshold}")
	private String msgSecretKeyExpiryThreshold; 

	public String getMsgClientId() {
		return msgClientId;
	}

	public String getMsgAuthority() {
		return msgAuthority;
	}

	public String getMsgSecretKey() {
		return msgSecretKey;
	}

	public String getMsgEndpointHost() {
		return msgEndpointHost;
	}

	public String getMsgEmailAccount() {
		return msgEmailAccount;
	}

	public String getMsgSecretKeyExpiryThreshold() {
		return msgSecretKeyExpiryThreshold;
	}
	
}