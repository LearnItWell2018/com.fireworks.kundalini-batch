package com.fireworks.kundalini.model;

public class CrackerVo {
	/*
	 * "productId" : "POTS-001", "productImgPath" : "/img/img.jpg", "brand" :
	 * "SIVKASHI", "itemName": "Red POTS", "itemPrice": "sds", "itemStock": "sds",
	 * "itemActive": "true"
	 */
	private String productId = null;
	private String productImgPath = null;
	private String brand = null;
	private String itemName = null;
	private double itemPrice = 0d;
	private int itemStock = 0;
	private boolean itemActive = false;

	public CrackerVo() {
		super();
	}

	@Override
	public String toString() {
		return "CrackerVo [productId=" + productId + ", productImgPath=" + productImgPath + ", brand=" + brand
				+ ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemStock=" + itemStock + ", itemActive="
				+ itemActive + "]";
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductImgPath() {
		return productImgPath;
	}

	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public boolean isItemActive() {
		return itemActive;
	}

	public void setItemActive(boolean itemActive) {
		this.itemActive = itemActive;
	}

}
