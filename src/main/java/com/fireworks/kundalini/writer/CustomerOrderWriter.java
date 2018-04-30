package com.fireworks.kundalini.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fireworks.kundalini.crud.resource.CustomerOrder;
import com.fireworks.kundalini.helper.Helper;


public class CustomerOrderWriter implements ItemWriter<CustomerOrder>{

	
	Helper helper;
	
	@Autowired
	public CustomerOrderWriter(Helper helper) {
		this.helper = helper;
	}
	
	@Override
	public void write(List<? extends CustomerOrder> arg0) throws Exception {
		helper.generatePDF(arg0.get(0), Integer.toString(arg0.get(0).hashCode()));
	}
}
