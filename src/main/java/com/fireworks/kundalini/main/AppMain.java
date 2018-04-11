package com.fireworks.kundalini.main;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fireworks.kundalini.main.resource.CustomerOrder;
import com.fireworks.kundalini.writer.OrderBill;

@SpringBootApplication
public class AppMain {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String className = AppMain.class.getName();
		Logger LOG = Logger.getLogger(className);
		LOG.info("Starting Batch - " + className);
		String rootDir = "E:/java/workspace/com.fireworks.kundalini-batch/src/main/resources/img/items/";
		String input = "{\"customerAddress\":[{\"id\":\"1\",\"nearestLandMark\":\"Near SDF Building\",\"pincode\":\"700091\",\"roomorflatno\":\"BIPL, Omega building, GP Block, Sector V, Salt Lake City, 8th Floor Reception.\",\"street\":\"BIPL, Omega building, GP Block, Sector V, Salt Lake City, 8th Floor Reception.\"}],\"orderDetails\":{\"orderList\":[{\"productId\":\"SHELL-002\",\"itemCount\":7,\"itemPrice\":\"80\",\"itemImage\":\""
				+ rootDir
				+ "shell_2.jpg\",\"itemDesc\":\"SHELL-002\"},{\"productId\":\"SHELL-003\",\"itemCount\":10,\"itemPrice\":\"80\",\"itemImage\":\""
				+ rootDir
				+ "shell_3.jpg\",\"itemDesc\":\"SHELL-003\"},{\"productId\":\"POTS-004\",\"itemCount\":2,\"itemPrice\":\"30\",\"itemImage\":\""
				+ rootDir
				+ "serpent.jpg\",\"itemDesc\":\"POTS-004\"},{\"productId\":\"TORCH-002\",\"itemCount\":3,\"itemPrice\":\"30\",\"itemImage\":\""
				+ rootDir + "torch_l_1.jpg\",\"itemDesc\":\"TORCH-002\"}]},\"customerMail\":\"9163848578\"}";
		LOG.info("input String is :\n " + input);
		ObjectMapper mapper = new ObjectMapper();

		CustomerOrder readValue = mapper.readValue(input, CustomerOrder.class);

		LOG.info("In class " + className);

		System.out.println("Main run successfully" + readValue);
		new OrderBill(readValue, "order");
		
		

		SpringApplication context = new SpringApplication(AppMain.class);
		ConfigurableApplicationContext ctx= context.run(args);
		
		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
		Job job = ctx.getBean("kundaliniJob",  Job.class);

		try {

			JobParametersBuilder jobBuilder = new JobParametersBuilder();
			jobBuilder.addString("customerJSON", args[0]);
			JobParameters jobParameters = jobBuilder.toJobParameters();
			JobExecution execution = jobLauncher.run(job, jobParameters);

			System.out.println("Completion Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}
}