
package com.fireworks.kundalini.main.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "pincode",
    "street",
    "roomorflatno",
    "nearestLandMark"
})
public class CustomerAddress {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("pincode")
    private String pincode;
    @JsonProperty("street")
    private String street;
    @JsonProperty("roomorflatno")
    private String roomorflatno;
    @JsonProperty("nearestLandMark")
    private String nearestLandMark;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("pincode")
    public String getPincode() {
        return pincode;
    }

    @JsonProperty("pincode")
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("roomorflatno")
    public String getRoomorflatno() {
        return roomorflatno;
    }

    @JsonProperty("roomorflatno")
    public void setRoomorflatno(String roomorflatno) {
        this.roomorflatno = roomorflatno;
    }

    @JsonProperty("nearestLandMark")
    public String getNearestLandMark() {
        return nearestLandMark;
    }

    @JsonProperty("nearestLandMark")
    public void setNearestLandMark(String nearestLandMark) {
        this.nearestLandMark = nearestLandMark;
    }

   
}
