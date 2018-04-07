package com.fireworks.kundalini.main.resource;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderTotal",
    "orderTAXAmount",
    "deliveryCharges",
    "orderDate",
    "deliveryAddressID",
    "deliveryDate",
    "orderList"
})
public class OrderDetails {

    @JsonProperty("orderTotal")
    private String orderTotal;
    @JsonProperty("orderTAXAmount")
    private String orderTAXAmount;
    @JsonProperty("deliveryCharges")
    private String deliveryCharges;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("deliveryAddressID")
    private Integer deliveryAddressID;
    @JsonProperty("deliveryDate")
    private String deliveryDate;
    @JsonProperty("orderList")
    private List<OrderList> orderList = null;

    @JsonProperty("orderTotal")
    public String getOrderTotal() {
        return orderTotal;
    }

    @JsonProperty("orderTotal")
    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    @JsonProperty("orderTAXAmount")
    public String getOrderTAXAmount() {
        return orderTAXAmount;
    }

    @JsonProperty("orderTAXAmount")
    public void setOrderTAXAmount(String orderTAXAmount) {
        this.orderTAXAmount = orderTAXAmount;
    }

    @JsonProperty("deliveryCharges")
    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    @JsonProperty("deliveryCharges")
    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    @JsonProperty("orderDate")
    public String getOrderDate() {
        return orderDate;
    }

    @JsonProperty("orderDate")
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @JsonProperty("deliveryAddressID")
    public Integer getDeliveryAddressID() {
        return deliveryAddressID;
    }

    @JsonProperty("deliveryAddressID")
    public void setDeliveryAddressID(Integer deliveryAddressID) {
        this.deliveryAddressID = deliveryAddressID;
    }

    @JsonProperty("deliveryDate")
    public String getDeliveryDate() {
        return deliveryDate;
    }

    @JsonProperty("deliveryDate")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @JsonProperty("orderList")
    public List<OrderList> getOrderList() {
        return orderList;
    }

    @JsonProperty("orderList")
    public void setOrderList(List<OrderList> orderList) {
        this.orderList = orderList;
    }

   

}
