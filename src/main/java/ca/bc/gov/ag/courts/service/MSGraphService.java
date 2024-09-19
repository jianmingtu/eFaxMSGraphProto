/**
 * 
 */
package ca.bc.gov.ag.courts.service;

import com.microsoft.graph.models.MessageCollectionResponse;

import ca.bc.gov.ag.courts.model.EmailMessage;
import ca.bc.gov.ag.courts.model.MailMessage;

/**
 * MS Graph Service Interface
 * 
 * @author 176899
 *
 */
public interface MSGraphService {
	
	public MessageCollectionResponse GetMessages(boolean attachment) throws Exception;
	public void sendMessage(MailMessage mailMessage, boolean saveToSentItems) throws Exception; 
	public void deleteMessage(EmailMessage emailMessage) throws Exception; 
	public String getPasswordCredentialsExpiryDate(); 
	
}
