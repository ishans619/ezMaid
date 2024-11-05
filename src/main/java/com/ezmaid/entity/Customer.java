package com.ezmaid.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String customerId;
	
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
	
	@Column(name = "adhar_card_number")
	private String adharCardNumber;
	
	@Column(name = "pan_card_number")
	private String panCardNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "is_first_login")
	private Boolean isFirstLogin;
	
	@Column(name = "is_verified")
	private Boolean isVerified;
	
	@Column(name = "rating")
	private Float rating;
	
	@Column(name = "crtd_date")
	private LocalDate crtdDate;
	
	@Column(name = "lst_updt_date")
	private LocalDate lstUpdtDate;
	
	@Column(name = "lst_updt_by")
	private String lstUpdtBy; 
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@JsonIgnoreProperties("customer")
	@JsonIgnore
//	@JsonManagedReference
	private List<Request> request;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String customerId, String fName, String mName, String lName, String contactNumber, String address,
			String adharCardNumber, String panCardNumber, String email, Boolean isFirstLogin,
			Boolean isVerified, Float rating, LocalDate crtdDate, LocalDate lstUpdtDate, String lstUpdtBy) {
		super();
		this.customerId = customerId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.adharCardNumber = adharCardNumber;
		this.panCardNumber = panCardNumber;
		this.email = email;
		this.isFirstLogin = isFirstLogin;
		this.isVerified = isVerified;
		this.rating = rating;
		this.crtdDate = crtdDate;
		this.lstUpdtDate = lstUpdtDate;
		this.lstUpdtBy = lstUpdtBy;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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


	public String getAdharCardNumber() {
		return adharCardNumber;
	}


	public void setAdharCardNumber(String adharCardNumber) {
		this.adharCardNumber = adharCardNumber;
	}


	public String getPanCardNumber() {
		return panCardNumber;
	}


	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}


	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}


	public Boolean getIsVerified() {
		return isVerified;
	}


	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
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


	public String getLstUpdtBy() {
		return lstUpdtBy;
	}


	public void setLstUpdtBy(String lstUpdtBy) {
		this.lstUpdtBy = lstUpdtBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName
				+ ", contactNumber=" + contactNumber + ", address=" + address + ", adharCardNumber=" + adharCardNumber
				+ ", panCardNumber=" + panCardNumber + ", email=" + email + ", isFirstLogin="
				+ isFirstLogin + ", isVerified=" + isVerified + ", rating=" + rating + ", crtdDate=" + crtdDate + ", lstUpdtDate="
				+ lstUpdtDate + ", lstUpdtBy=" + lstUpdtBy + "]";
	}
	
}