package com.fireworks.kundalini.writer;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.batch.item.ItemWriter;

import com.fireworks.kundalini.main.resource.CustomerOrder;

public class Mailer implements ItemWriter<CustomerOrder> {

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

	@Override
	public void write(List<? extends CustomerOrder> arg0) throws Exception {
		CustomerOrder customerOrder = arg0.get(0);
		this.send(customerOrder);
	}

	private String composeMessage(CustomerOrder customerOrder) {
		return "Mail Message";
	}

	private String prepareSubject(CustomerOrder customerOrder) {
		return "Subject";
	}
}