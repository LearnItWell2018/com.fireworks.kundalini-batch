package com.fireworks.kundalini.task;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.fireworks.kundalini.main.resource.CustomerOrder;

public class OrderBillTasklet implements Tasklet{

	@Autowired
	List<CustomerOrder> customerOrders;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		System.out.println("Bill generate");
		return null;
	}

}
