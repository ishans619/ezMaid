package com.ezmaid.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String adminId;
	
	@Column(name = "f_name")
	private String fName;
	
	@Column(name = "m_name")
	private String mName;
	
	@Column(name = "l_name")
	private String lName;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "is_super_admin")
	private Boolean isSuperAdmin; 
	
	@Column(name = "crtd_date")
	private LocalDate crtdDate;
	
	@Column(name = "lst_updt_date")
	private LocalDate lstUpdtDate;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}


	public Admin(String adminId, String fName, String mName, String lName, String contactNumber, String address,
			String email, LocalDate crtdDate, LocalDate lstUpdtDate, Boolean isSuperAdmin) {
		super();
		this.adminId = adminId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.email = email;
		this.crtdDate = crtdDate;
		this.lstUpdtDate = lstUpdtDate;
		this.isSuperAdmin = isSuperAdmin;
	}


	public String getAdminId() {
		return adminId;
	}


	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	
	public LocalDate getCrtdDate() {
		return crtdDate;
	}


	public void setCrtdDate(LocalDate crtdDate) {
		this.crtdDate = crtdDate;
	}


	public LocalDate getLstUpdtDate() {
		return lstUpdtDate;
	}


	public void setLstUpdtDate(LocalDate lstUpdtDate) {
		this.lstUpdtDate = lstUpdtDate;
	}


	public Boolean getIsSuperAdmin() {
		return isSuperAdmin;
	}


	public void setIsSuperAdmin(Boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName
				+ ", contactNumber=" + contactNumber + ", address=" + address + ", email=" + email + ", crtdDate=" + crtdDate + ", lstUpdtDate="
				+ lstUpdtDate + ", isSuperAdmin=" + isSuperAdmin + "]";
	}
	
}