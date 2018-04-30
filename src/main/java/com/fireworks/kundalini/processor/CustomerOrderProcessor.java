package com.fireworks.kundalini.processor;

import org.springframework.batch.item.ItemProcessor;

import com.fireworks.kundalini.crud.resource.CustomerOrder;

public class CustomerOrderProcessor implements ItemProcessor<CustomerOrder, CustomerOrder>{

	@Override
	public CustomerOrder process(CustomerOrder customerOrder) throws Exception {
		return customerOrder;
	}

}
