package com.fireworks.kundalini.writer;

import com.fireworks.kundalini.helper.Mailer;
import com.fireworks.kundalini.main.resource.CustomerOrder;

public class EmailWriter {
	public void Write() {
		
		String message = null;
		String from = "learnitwell2018@gmail.com";
		String to = null;
		String subject = null;
		String password = "gurukul2018";
		
		CustomerOrder.getCustomerMail();
		
		subject = "Kundalini - Order Confirmation - "+orderId;
		message = "Hello "+UserName+",\n Thanks for placing order with us.Your order details are:\n"+orderDetails+
		"\nYou can track your order at "+Link+"\nThanks,\n"+kundiliniSignature;
		/*messageBodyPart = new MimeBodyPart();
		String filename = "/home/manisha/file.txt";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);*/
		
		
		
		
		//from,password,to,subject,message 
		Mailer.send(from,password,to,subject,message);
	}
}