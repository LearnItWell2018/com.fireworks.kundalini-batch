package com.fireworks.kundalini.main.configration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fireworks.kundalini.main.resource.CustomerOrder;
import com.fireworks.kundalini.task.CollectCustomerOrderTasklet;
import com.fireworks.kundalini.task.MailerTasklet;
import com.fireworks.kundalini.task.OrderBillTasklet;

@Configuration
@EnableBatchProcessing
@ComponentScan("com.fireworks.kundalini")
@PropertySource("batch.properties")
public class BatchConfigration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean("customerOrders")
	public List<CustomerOrder> getCustomerOrder() {
		return new ArrayList<>();
	}
	
	@Bean("kundaliniJob")
	public Job importUserJob() {
		return jobBuilderFactory.get("kundaliniJob").incrementer(new RunIdIncrementer()).start(stepCollectGenerate())
				.next(stepMail()).build();
	}

	@StepScope
	private Step stepMail() {
		return stepBuilderFactory.get("stepRead").tasklet(sendMail()).build();
	}
	
	@StepScope
	private Step stepCollectGenerate() {
		return stepBuilderFactory.get("stepRead").tasklet(collectCustomerOrders()).tasklet(generateBill()).build();
	}

	private Tasklet sendMail() {
		return new MailerTasklet();
	}
	
	private Tasklet collectCustomerOrders() {
		return new CollectCustomerOrderTasklet();
	}
	
	private Tasklet generateBill() {
		return new OrderBillTasklet();
	}

}
