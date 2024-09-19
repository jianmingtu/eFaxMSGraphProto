package ca.bc.gov.ag.courts.service;

import java.util.List;

import ca.bc.gov.ag.courts.model.EmailMessage;
import ca.bc.gov.ag.courts.model.MailMessage;

public interface EmailService {

    /**
     * Returns a collection of efax emails from the inbox folder.
     *
     * @return
     * @throws Exception 
     */
    List<EmailMessage> getInboxEmails() throws Exception;

    /**
     * Moves the specified email to the "Deleted Items" folder
     * @param emailMessage
     * @throws Exception 
     */
    void deleteEmail(EmailMessage emailMessage) throws Exception;
    
    /**
     * Sends an email (including any attachments) using Microsoft Exchange.
     * 
     * @param mailMessage
     */
    public void sendMessage(final MailMessage mailMessage) throws Exception;



    
}