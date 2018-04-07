package com.fireworks.kundalini.main.resource;

import java.util.ArrayList;

public class ItemResource {

	private String productId;
	private int itemCount; 
	private double itemPrice;
	private static ArrayList <ItemResource> itemList= new ArrayList<ItemResource>();
	
	public static ArrayList<ItemResource> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<ItemResource> itemList) {
		this.itemList = itemList;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
}
