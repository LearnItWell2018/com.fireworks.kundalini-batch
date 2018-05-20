package com.fireworks.kundalini.main.configration;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fireworks.kundalini.crud.resource.CustomerOrder;
import com.fireworks.kundalini.db.CustomerOrderRepository;
import com.fireworks.kundalini.helper.PDFGeneratorHelper;
import com.fireworks.kundalini.processor.CustomerOrderProcessor;
import com.fireworks.kundalini.task.MailerTasklet;
import com.fireworks.kundalini.task.QrCodeGeneratorTasklet;
import com.fireworks.kundalini.writer.CustomerOrderWriter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableBatchProcessing
@ComponentScan("com.fireworks.kundalini")
@PropertySource("batch.properties")
@EnableMongoRepositories("com.fireworks.kundalini.db")
public class BatchConfiguration extends AbstractMongoConfiguration {
	
	@Autowired
	Environment env;

	@Autowired
	CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean("kundaliniJobPDF")
	public Job kundaliniJobPDF() {
		return jobBuilderFactory.get("kundaliniJobPDF").incrementer(new RunIdIncrementer()).start(stepCollectOrderAndProcess()).build();
	}

	@Bean("kundaliniJobMail")
	public Job kundaliniJobMail() {
		return jobBuilderFactory.get("kundaliniJobMail").incrementer(new RunIdIncrementer()).start(stepMAIL()).build();
	}
	
	@Bean("kundaliniJobStockGenerate")
	public Job kundaliniJobStockGenerate() {
		return jobBuilderFactory.get("kundaliniJobStockGenerate").incrementer(new RunIdIncrementer()).start(stepGenerateProduct()).build();
	}
	
	@StepScope
	private Step stepCollectOrderAndProcess() {
		return stepBuilderFactory.get("stepCollectOrderAndProcess").<CustomerOrder, CustomerOrder>chunk(1).reader(customerOrderRead()).processor(getOrderProcessor()).writer(getOrderWriter()).build();
	}
	
	@StepScope
	private Step stepMAIL() {
		return stepBuilderFactory.get("stepMAIL").tasklet(sendMail()).build();
	}
	
	@StepScope
	private Step stepGenerateProduct() {
		return stepBuilderFactory.get("stepGenerateProduct").tasklet(qrCodeGen()).build();
	}
	
	
	public @Bean MongoDbFactory getMongoDbFactory()  {
		return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
	}

	public @Bean MongoTemplate getMongoTemplate()  {
		return new MongoTemplate(getMongoDbFactory());
	}
	
	private MongoItemReader<CustomerOrder> customerOrderRead() {
		MongoItemReader<CustomerOrder> customerOrderReader = new MongoItemReader<>();
		customerOrderReader.setTemplate(getMongoTemplate());
		customerOrderReader.setCollection("orders");
		customerOrderReader.setName("mongoItemReader");
		customerOrderReader.setTargetType(CustomerOrder.class);
		customerOrderReader.setQuery("{orderStatus : 'TRIGGER_MAIL' }");
		customerOrderReader.setSort(new HashMap<String, Direction>());
		return customerOrderReader;
	}
	
	private CustomerOrderProcessor getOrderProcessor() {
		return new CustomerOrderProcessor();
	}

	private CustomerOrderWriter getOrderWriter() {
		return new CustomerOrderWriter(getHelper());
	}
	
	private Tasklet sendMail() {
		return new MailerTasklet(env, customerOrderRepository);
	}
	
	private Tasklet qrCodeGen() {
		return new QrCodeGeneratorTasklet(env);
	}
	
	@Override
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(env.getProperty("mongo.uri"));
		return new MongoClient(uri);
	}

	@Override
	protected String getDatabaseName() {
		return env.getProperty("mongo.db");
	}
	
	public PDFGeneratorHelper getHelper() {
		return new PDFGeneratorHelper(env);
	}
	
}
