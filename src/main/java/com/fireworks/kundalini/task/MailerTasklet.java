package com.fireworks.kundalini.task;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fireworks.kundalini.main.resource.CustomerOrder;

public class MailerTasklet implements Tasklet {

	@Autowired
	Environment env;
	
	@Autowired
	CustomerOrder customerOrder;
	
	public void send(CustomerOrder customerOrder) {
		
		Session session = Session.getDefaultInstance(provideProperty(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(env.getProperty("mail.from"), env.getProperty("mail.pass"));
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(env.getProperty("mail.from")));
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
	
	private Properties provideProperty() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		return props;
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		this.send(customerOrder);
		return null;
	}
}