package com.fireworks.kundalini;

import org.apache.log4j.Logger;

public class AppMain {
	public static void main(String[] args) {

		Logger LOG = Logger.getLogger(AppMain.class.getName());
		/*String input = args[0];
		String output = args[1];
		*/
		LOG.info("In class "+AppMain.class.getName());
		LOG.warn("In class "+AppMain.class.getName());
		LOG.error("In class "+AppMain.class.getName());
		LOG.debug("In class "+AppMain.class.getName());
		System.out.println("Main run successfully");

	}
}
