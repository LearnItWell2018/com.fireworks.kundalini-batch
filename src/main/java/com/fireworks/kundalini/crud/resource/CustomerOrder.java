package com.fireworks.kundalini.crud.resource;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerMail",
    "customerMobile",
    "orderDetails",
    "customerAddress",
    "orderStatus"
})
public class CustomerOrder {

    @Id
    @JsonProperty
    private String id;
    
    @JsonProperty
    public String getId() {
		return id;
	}
    @JsonProperty
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("customerMail")
    private String customerMail;
    @JsonProperty("customerMobile")
    private String customerMobile;
    @JsonProperty("orderStatus")
    private String orderStatus;
	@JsonProperty("orderDetails")
    private OrderDetails orderDetails;
    @JsonProperty("customerAddress")
    private List<CustomerAddress> customerAddress = null;

    @JsonProperty("customerAddress")
    public List<CustomerAddress> getCustomerAddress() {
        return customerAddress;
    }

    @JsonProperty("customerAddress")
    public void setCustomerAddress(List<CustomerAddress> customerAddress) {
        this.customerAddress = customerAddress;
    }

	@JsonProperty("customerMail")
    public String getCustomerMail() {
        return customerMail;
    }

    @JsonProperty("customerMail")
    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    @JsonProperty("customerMobile")
    public String getCustomerMobile() {
        return customerMobile;
    }

    @JsonProperty("customerMobile")
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    @JsonProperty("orderDetails")
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    @JsonProperty("orderDetails")
    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    @JsonProperty("orderStatus")
    public String getOrderStatus() {
 		return orderStatus;
 	}

    @JsonProperty("orderStatus")
 	public void setOrderStatus(String orderStatus) {
 		this.orderStatus = orderStatus;
 	}

}
