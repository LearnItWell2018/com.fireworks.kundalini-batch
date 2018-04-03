package com.fireworks.kundalini;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fireworks.kundalini.model.CrackerVo;

@SpringBootApplication
public class AppMain {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		Logger LOG = Logger.getLogger(AppMain.class.getName());
		//String input = args[0];

		ObjectMapper mapper = new ObjectMapper();
		 String testJson = "{\n" + "  \"user\": {\n" + "    \"0\": {\n" + "      \"firstName\": \"Monica\",\n" + "      \"lastName\": \"Belluci\"\n" + "    },\n" + "    \"1\": {\n" + "      \"firstName\": \"John\",\n" + "      \"lastName\": \"Smith\"\n" + "    },\n" + "    \"2\": {\n" + "      \"firstName\": \"Owen\",\n" + "      \"lastName\": \"Hargreaves\"\n" + "    }\n" + "  }\n" + "}";
		User readValue = mapper.readValue(testJson, User.class);

		LOG.info("In class " + AppMain.class.getName());
		LOG.warn("In class " + AppMain.class.getName());
		LOG.error("In class " + AppMain.class.getName());
		LOG.debug("In class " + AppMain.class.getName());
		System.out.println("Main run successfully"+readValue);

	}
}