package com.fireworks.kundalini.model;

public class UserVo {

	private String firstName;
	private String lastName;
	private String emailId;
	private String contact;
	private String address;

	public UserVo(String firstName, String lastName, String emailId, String contact, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contact = contact;
		this.address = address;
	}

	public UserVo() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserVo [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId + ", contact="
				+ contact + ", address=" + address + "]";
	}

}
