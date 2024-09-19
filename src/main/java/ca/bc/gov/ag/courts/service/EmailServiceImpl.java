package ca.bc.gov.ag.courts.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.MessageCollectionResponse;

import ca.bc.gov.ag.courts.model.EmailMessage;
import ca.bc.gov.ag.courts.model.MailMessage; 

/**
 * 
 * This service contains the same implemention of the methods used by the eFax EmalService - Do not change. 
 * 
 * @author 176899
 * 
 */
@Service
public class EmailServiceImpl implements EmailService {
	
	private MSGraphServiceImpl gService; 
	
	public EmailServiceImpl (MSGraphServiceImpl gService) {
		this.gService = gService; 
	}

	@Override
	public List<EmailMessage> getInboxEmails() throws Exception {
		
		MessageCollectionResponse responses = gService.GetMessages(true);
		
		//TODO - Mapping to EmailMessage required here. 
		// https://medium.com/thefreshwrites/guide-to-object-mapping-with-modelmapper-in-java-1dd8f517faf2
		
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(Message.class, EmailMessage.class)
		  .addMappings(mapper -> {
			  // Note: The following method, getBodyPreview(). seems to supply what we want
			  // here. That being a string. If the getBody().getContent() is used, this is likely to be HTML. 
		      mapper.map(src -> src.getBodyPreview(), EmailMessage::setBody); 
		      mapper.map(src -> src.getSubject(), EmailMessage::setSubject);
		      mapper.map(src -> "text/plain", EmailMessage::setContentType);
		  });
		
		List<EmailMessage> o = new ArrayList<EmailMessage>();
		for (Message message:  responses.getValue()) {
			EmailMessage e = modelMapper.map(message, EmailMessage.class);
			o.add(e);
		}
		
		return o;
	}

	@Override
	public void deleteEmail(EmailMessage emailMessage) throws Exception {
		gService.deleteMessage(emailMessage);
	}

	@Override
	public void sendMessage(MailMessage mailMessage) throws Exception {
		gService.sendMessage(mailMessage, true);
	}

}
