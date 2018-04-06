package com.fireworks.kundalini.main;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AppMain {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String className = AppMain.class.getName();
		Logger LOG = Logger.getLogger(AppMain.class.getName());
		LOG.info("Starting Batch " + className);
		String input = args[0];
		ObjectMapper mapper = new ObjectMapper();

		User readValue = mapper.readValue(input, User.class);

		LOG.info("In class " + className);

		System.out.println("Main run successfully" + readValue);

	}
}