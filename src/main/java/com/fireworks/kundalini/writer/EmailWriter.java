package com.fireworks.kundalini.writer;

import com.fireworks.kundalini.helper.Mailer;


public class EmailWriter {
	public void Write() {
		
		String message = null;
		String from = "learnitwell2018@gmail.com";
		String to = null;
		String subject = null;
		String password = "gurukul2018";
		
		Mailer.send(from,password,to,subject,message);
	}
}