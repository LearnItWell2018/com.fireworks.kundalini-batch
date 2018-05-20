package com.fireworks.kundalini.main;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppMain {
	public static void main(String[] args) {

		SpringApplication context = new SpringApplication(AppMain.class);
		ConfigurableApplicationContext ctx = context.run(args);

		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
		Job job = ctx.getBean("kundaliniJobPDF", Job.class);
		try {

			JobParametersBuilder jobBuilder = new JobParametersBuilder();
			JobParameters jobParameters = jobBuilder.toJobParameters();
			JobExecution execution = jobLauncher.run(job, jobParameters);

			System.out.println("Completion Status kundaliniJobPDF : " + execution.getStatus());

			if (BatchStatus.COMPLETED.equals(execution.getStatus())) {
				job = ctx.getBean("kundaliniJobMail", Job.class);
				execution = jobLauncher.run(job, jobParameters);
				System.out.println("Completion Status kundaliniJobMail : " + execution.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}