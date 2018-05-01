package com.fireworks.kundalini.writer;

import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fireworks.kundalini.crud.resource.CustomerOrder;
import com.fireworks.kundalini.helper.PDFGeneratorHelper;


public class CustomerOrderWriter implements ItemWriter<CustomerOrder>{

	
	PDFGeneratorHelper helper;
	
	@Autowired
	public CustomerOrderWriter(PDFGeneratorHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public void write(List<? extends CustomerOrder> customerOrder) throws Exception {
		helper.generatePDF(customerOrder.get(0), helper.env.getProperty("order.pdfname.pre") + new Date().getTime());
	}
}
