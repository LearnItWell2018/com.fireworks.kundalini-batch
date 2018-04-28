package com.fireworks.kundalini.main;

import java.io.IOException;
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

@SpringBootApplication
public class AppMain {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		SpringApplication context = new SpringApplication(AppMain.class);
		ConfigurableApplicationContext ctx= context.run(args);
		
		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
		Job job = ctx.getBean("kundaliniJob",  Job.class);

		try {

			JobParametersBuilder jobBuilder = new JobParametersBuilder();
			JobParameters jobParameters = jobBuilder.toJobParameters();
			JobExecution execution = jobLauncher.run(job, jobParameters);

			System.out.println("Completion Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}
}