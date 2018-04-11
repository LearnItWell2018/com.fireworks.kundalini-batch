package com.fireworks.kundalini.main.configration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fireworks.kundalini.main.resource.CustomerOrder;
import com.fireworks.kundalini.task.MailerTasklet;

@Configuration
@EnableBatchProcessing
@ComponentScan("com.fireworks.kundalini")
@PropertySource("batch.properties")
public class BatchConfigration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean("customerOrder")
	public CustomerOrder getCustomerOrder() {
		return new CustomerOrder();
	}

	@Bean("kundaliniJob")
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).start(stepPDF())
				.next(stepMail()).build();
	}

	private Step stepMail() {
		return stepBuilderFactory.get("stepRead").tasklet(getResourceDetails()).build();
	}

	private Tasklet getResourceDetails() {
		return new MailerTasklet();
	}

	private Step stepPDF() {
		return stepBuilderFactory.get("stepPDF").<String, CustomerOrder>chunk(1).reader(new ItemReader<String>() {

			public String read()
					throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				// TODO Auto-generated method stub
				return null;
			}
		}).processor(new ItemProcessor<String, CustomerOrder>() {

			public CustomerOrder process(String item) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		}).build();
	}

}
