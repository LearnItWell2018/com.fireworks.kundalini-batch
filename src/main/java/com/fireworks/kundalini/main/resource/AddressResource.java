package com.fireworks.kundalini.main.resource;

public class AddressResource {

	private String id;
	private String pinCode;
	private String street;
	private String roomOrFlatNo;
	private String nearestLandMark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getRoomOrFlatNo() {
		return roomOrFlatNo;
	}
	public void setRoomOrFlatNo(String roomOrFlatNo) {
		this.roomOrFlatNo = roomOrFlatNo;
	}
	public String getNearestLandMark() {
		return nearestLandMark;
	}
	public void setNearestLandMark(String nearestLandMark) {
		this.nearestLandMark = nearestLandMark;
	}
}