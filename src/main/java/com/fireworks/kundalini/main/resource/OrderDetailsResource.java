package com.fireworks.kundalini.main.resource;

import java.util.Date;
import java.util.List;

public class OrderDetailsResource {
	/*
	 * "productId" : "POTS-001", "productImgPath" : "/img/img.jpg", "brand" :
	 * "SIVKASHI", "itemName": "Red POTS", "itemPrice": "sds", "itemStock": "sds",
	 * "itemActive": "true"
	 */
	private String orderTotal;
	private String orderTAXAmount;
	private String deliveryCharges;
	private Date orderDate;
	private int deliveryAddressID;
	private Date deliveryDate;
	List <ItemResource> orderList = ItemResource.getItemList();

}