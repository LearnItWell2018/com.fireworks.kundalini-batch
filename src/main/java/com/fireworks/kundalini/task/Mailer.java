package com.fireworks.kundalini.task;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.fireworks.kundalini.main.resource.CustomerOrder;

public class Mailer implements Tasklet {

	@Autowired
	CustomerOrder customerOrder;
	
	public void send(CustomerOrder customerOrder) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("learnitwell2018@gmail.com", "gurukul2018");
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("learnitwell2018@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(customerOrder.getCustomerMail()));
			message.setSubject(prepareSubject(customerOrder));
			message.setText(composeMessage(customerOrder));
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	private String composeMessage(CustomerOrder customerOrder) {
		return "Mail Message";
	}

	private String prepareSubject(CustomerOrder customerOrder) {
		return "Subject";
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		this.send(customerOrder);
		return null;
	}
}