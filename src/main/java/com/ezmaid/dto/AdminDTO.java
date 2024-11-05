package com.ezmaid.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AdminDTO {
	
	private String adminId;
	
	@NotBlank(message = "First Name is mandatory")
	private String fName;
	
	private String mName;
	
	@NotBlank(message = "Last Name is mandatory")
	private String lName;
	
	@NotBlank(message = "Contact number is mandatory")
	@Pattern(regexp="(^$|[1-9]{1}[0-9]{9})", message = "Contact Number Invalid")
	private String contactNumber;
	
	@NotBlank(message = "Address is a mandatory")
	private String address;
	
	@NotBlank(message = "Email is a mandatory")
	@Email(message = "Invalid email")
	private String email;
	
	@NotBlank(message = "Username is a mandatory")
	private String username;
	
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdminDTO(String adminId, String fName, String mName, String lName, String contactNumber, String address, String email, String username) {
		super();
		this.adminId = adminId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.email = email;
		this.username = username;
	}

	public String getAdminId() {
		return adminId;
	}

	public AdminDTO setAdminId(String adminId) {
		this.adminId = adminId;
		return this;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AdminDTO [adminId=" + adminId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName
				+ ", contactNumber=" + contactNumber + ", address=" + address + ", email=" + email + ", username=" + username + "]";
	}
	

}
