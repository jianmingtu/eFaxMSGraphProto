package ca.bc.gov.ag.courts.efax.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.ag.courts.model.EmailMessage;
import ca.bc.gov.ag.courts.model.MailMessage;
import ca.bc.gov.ag.courts.service.EmailServiceImpl;

@RestController
public class EmailTestController {

	Logger logger = LoggerFactory.getLogger(EmailTestController.class);
	
	private EmailServiceImpl eService; 
	
	public EmailTestController(EmailServiceImpl eService) {
		this.eService = eService; 
	}
	
	@GetMapping("/email/getMail")
	public ResponseEntity<List<EmailMessage>> getMail() throws Exception {
		try {
			List<EmailMessage> response = eService.getInboxEmails();
			return new ResponseEntity<List<EmailMessage>>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<EmailMessage>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/email/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody MailMessage mailMessage) {
		try {
			eService.sendMessage(mailMessage);
			return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/email/deleteMail")
	public ResponseEntity<String> deleteMail(@RequestBody EmailMessage emailMessage) {
		try {
			eService.deleteEmail(emailMessage);
			return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
