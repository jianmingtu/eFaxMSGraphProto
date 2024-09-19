package ca.bc.gov.ag.courts.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ca.bc.gov.ag.courts.config.AppProperties;
import ca.bc.gov.ag.courts.service.MSGraphService;
import ca.bc.gov.ag.courts.service.MSGraphServiceImpl;
import ca.bc.gov.ag.courts.utils.EFaxConstants;
import jakarta.annotation.PostConstruct;

/**
 * 
 * Monitors the MS Graph API Secret Key password expiration date. 
 * 
 * Once the threshold is reached, notification emails are sent as a reminder to refresh key. 
 * 
 */
@Component
public class ExpiryMonitor {
	
	private static final Logger logger = LoggerFactory.getLogger(ExpiryMonitor.class);
	
	private AppProperties props; 
	
	private MSGraphService mService; 
	
	public ExpiryMonitor(AppProperties props, MSGraphServiceImpl mService) {
		this.props = props; 
		this.mService = mService; 
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat(EFaxConstants.dateFormat);
	
	@PostConstruct
	private void postConstruct() throws ParseException {
		logger.info("MS Graph API Credential Expiry Monitor started.");
		this.checkExpiryDt();
	}

	/**
	 * 
	 * Check MS Graph API Credential Expiration once each day. 
	 * 
	 * @throws ParseException
	 */
	@Scheduled(cron = "0 0 1 * * *")
	public void checkExpiryDt() throws ParseException {
		
		logger.info("MS Graph API Credential Expiry Monitor - Check Expiry Date called.");
		
		Date nowDt = new Date();
		
		// MS Graph API credential (Secret Key) expiry date. 
		Date expiryDt = sdf.parse(mService.getPasswordCredentialsExpiryDate());
		
		long diff = getDifferenceDays(nowDt, expiryDt);
		logger.debug("Delta between now and MS Graph API Secret Key credential expiration date is:  " + diff + " days.");
		
		if (diff <= Long.parseLong(props.getMsgSecretKeyExpiryThreshold())) {
			logger.info("Sending notification to renew MS Graph API Secret Key. Presently " + diff + "days from expiration.");
			// TODO - finish me. 
			//nService.sendNotification(diff);
		}
	}
	
	private long getDifferenceDays(Date nowDt, Date expiryDt) {
	    long diff = expiryDt.getTime() - nowDt.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}
