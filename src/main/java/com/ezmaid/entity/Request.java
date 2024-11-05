package com.ezmaid.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "request")
public class Request {

	@Id
	@Column(name = "rqst_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String rqstId;
	
	@Column(name = "rqst_type")
	private String rqstType;
	
	@Column(name = "rqst_title")
	private String rqstTitle;
	
	@Column(name = "rqst_description")
	private String rqstDescription;
	
	@Column(name = "is_fulfilled")
	private Boolean isFulfilled;
	
	@Column(name = "is_cancelled")
	private Boolean isCancelled;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "is_finalized")
	private Boolean isFinalized;
	
	@Column(name = "cust_rating")
	private Float custRating;
	
	@Column(name = "crtd_date")
	private LocalDate crtdDate;
	
	@Column(name = "lst_updt_date")
	private LocalDate lstUpdtDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
//	@JsonIgnore
//	@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "request")
	@JsonIgnoreProperties("request")
//	@JsonManagedReference 
	private List<RequestDetail> requestDetail;

	public Request() {
		// TODO Auto-generated constructor stub
	}
	
	public Request(String rqstId, String rqstType, String rqstTitle, String rqstDescription, Boolean isFulfilled, Boolean isCancelled,
			String status, Boolean isFinalized, Float custRating, LocalDate crtdDate, LocalDate lstUpdtDate) {
		super();
		this.rqstId = rqstId;
		this.rqstType = rqstType;
		this.rqstTitle = rqstTitle;
		this.rqstDescription = rqstDescription;
		this.isFulfilled = isFulfilled;
		this.isCancelled = isCancelled;
		this.status = status;
		this.isFinalized = isFinalized;
		this.custRating = custRating;
		this.crtdDate = crtdDate;
		this.lstUpdtDate = lstUpdtDate;
	}

	public String getRqstId() {
		return rqstId;
	}

	public void setRqstId(String rqstId) {
		this.rqstId = rqstId;
	}
	
	public String getRqstType() {
		return rqstType;
	}

	public void setRqstType(String rqstType) {
		this.rqstType = rqstType;
	}

	public String getRqstTitle() {
		return rqstTitle;
	}

	public void setRqstTitle(String rqstTitle) {
		this.rqstTitle = rqstTitle;
	}

	public String getRqstDescription() {
		return rqstDescription;
	}

	public void setRqstDescription(String rqstDescription) {
		this.rqstDescription = rqstDescription;
	}

	public Boolean getIsFulfilled() {
		return isFulfilled;
	}

	public void setIsFulfilled(Boolean isFulfilled) {
		this.isFulfilled = isFulfilled;
	}

	public Boolean getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsFinalized() {
		return isFinalized;
	}

	public void setIsFinalized(Boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

	public Float getCustRating() {
		return custRating;
	}

	public void setCustRating(Float custRating) {
		this.custRating = custRating;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<RequestDetail> getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(List<RequestDetail> requestDetail) {
		this.requestDetail = requestDetail;
	}
	
	@Override
	public String toString() {
		return "Request [rqstId=" + rqstId + ", rqstType=" + rqstType + ", rqstTitle=" + rqstTitle + ", rqstDescription=" + rqstDescription
				+ ", isFulfilled=" + isFulfilled + ", isCancelled=" + isCancelled + ", status=" + status
				+ ", isFinalized=" + isFinalized + ", custRating=" + custRating + ", crtdDate=" + crtdDate
				+ ", lstUpdtDate=" + lstUpdtDate + ", customer=" + customer + "]";
	}
	
}
