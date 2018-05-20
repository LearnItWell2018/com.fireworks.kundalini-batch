package com.fireworks.kundalini.task;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fireworks.kundalini.crud.resource.CustomerOrder;
import com.fireworks.kundalini.db.CustomerOrderRepository;


public class MailerTasklet implements Tasklet {

	Environment env;
	
	CustomerOrderRepository customerOrderRepository;

	@Autowired
	public MailerTasklet(Environment env, CustomerOrderRepository customerOrderRepository) {
		this.env = env;
		this.customerOrderRepository = customerOrderRepository;
	}

	public void send(String customerMail, String pdfPath) throws MessagingException {

		Session session = Session.getDefaultInstance(provideProperty(), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(env.getProperty("mail.from"), env.getProperty("mail.pass"));
			}
		});

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(env.getProperty("mail.from")));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(customerMail));
		message.setSubject(prepareSubject());
		message.setText(composeMessage());
		message.setContent(getMessagePart(pdfPath));
		Transport.send(message);

	}

	private String composeMessage() {
		return "Dear Customer";
	}

	private String prepareSubject() {
		return env.getProperty("mail.subject");
	}

	private Properties provideProperty() {
		Properties props = new Properties();
		props.put("mail.smtp.host", env.getProperty("mail.host"));
		props.put("mail.smtp.socketFactory.port", env.getProperty("mail.port"));
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", env.getProperty("mail.port"));
		return props;
	}

	private Multipart getMessagePart(String pdfPath) {
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = null;
		MimeBodyPart attachPart;
		try {
			messageBodyPart.setContent(composeMessage(), "text/html");

			multipart = new MimeMultipart();
			attachPart = new MimeBodyPart();

			multipart.addBodyPart(messageBodyPart);
			attachPart.attachFile(pdfPath);
			multipart.addBodyPart(attachPart);

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
		return multipart;

	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		System.out.println("execute called ");
		
		File pdfPath = new File(env.getProperty("pdfPath"));

		for (File file : pdfPath.listFiles()) {
			String fileName = file.getName();
			String newFileName = env.getProperty("pdfRepoPath") + fileName.split("-")[1];
			FileUtils.copyFile(file, new File(newFileName));
			FileUtils.forceDelete(file);
			this.send(fileName.split("-")[0], newFileName);
			String orderId = fileName.split("-")[1].replace("KUND", "");
			orderId = orderId.replace(".pdf", "");
			updateCustomerOrderStatus(orderId);
		}
		return null;
	}
	
	private void updateCustomerOrderStatus(String orderId) {
		if (customerOrderRepository.existsById(orderId)) {
			CustomerOrder customerOrder = customerOrderRepository.findById(orderId).get();
			customerOrder.setOrderStatus("MAIL_SENT");
			customerOrderRepository.save(customerOrder);
		}
	}
}