package com.fireworks.kundalini.task;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fireworks.kundalini.main.resource.CustomerOrder;

public class CollectCustomerOrderTasklet implements Tasklet{

	@Autowired
	Environment env;
	
	@Autowired
	List<CustomerOrder> customerOrders;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		//Connect Mongo to get data and add to the customerOrders bean 
		customerOrders.add(new CustomerOrder());
		return null;
	}

}
