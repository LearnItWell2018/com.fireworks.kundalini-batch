package com.fireworks.kundalini.main.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productId",
    "itemCount",
    "itemPrice"
})
public class OrderList {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("itemCount")
    private String itemCount;
    @JsonProperty("itemPrice")
    private String itemPrice;
    @JsonProperty("itemImage")
    private String itemImage;
    @JsonProperty("itemDesc")
    private String itemDesc;

    @JsonProperty("itemImage")
    public String getItemImage() {
		return itemImage;
	}

    @JsonProperty("itemImage")
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	@JsonProperty("itemDesc")
	public String getItemDesc() {
		return itemDesc;
	}

	@JsonProperty("itemDesc")
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	@JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty("itemCount")
    public String getItemCount() {
        return itemCount;
    }

    @JsonProperty("itemCount")
    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    @JsonProperty("itemPrice")
    public String getItemPrice() {
        return itemPrice;
    }

    @JsonProperty("itemPrice")
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

   

}
