package com.ezmaid.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "request_details")
public class RequestDetail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "is_accepted")
	private Boolean isAccepted;
	
	@Column(name = "is_rejected")
	private Boolean isRejected;
	
	@Column(name = "is_cust_accepted")
	private Boolean isCustAccepted;
	
	@Column(name = "is_cust_rejected")
	private Boolean isCustRejected;
	
	@Column(name = "proposed_amt")
	private BigDecimal proposedAmt; 
	
	@Column(name = "final_amt")
	private BigDecimal finalAmt;
	
	@Column(name = "damage_compensation_amt")
	private BigDecimal damageCompensationAmt;
	
	@Column(name = "tip")
	private BigDecimal tip;
	
	@Column(name = "is_absent")
	private Boolean isAbsent;
	
	@Column(name = "maid_rating")
	private Float maidRating;
	
	@Column(name = "lst_updt_date")
	private LocalDate lstUpdtDate;
	
	@Column(name = "lst_updt_by")
	private String lstUpdtBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maid_id")
//	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonBackReference
	private Maid maid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rqst_id")
	@JsonIgnore
//	@JsonBackReference
	private Request request;
	
	public RequestDetail() {
		// TODO Auto-generated constructor stub
	}

	public RequestDetail(Long id, Boolean isAccepted, Boolean isRejected, Boolean isCustAccepted,
			Boolean isCustRejected, BigDecimal proposedAmt, BigDecimal finalAmt,
			BigDecimal damageCompensationAmt, BigDecimal tip, Boolean isAbsent, Float maidRating, LocalDate lstUpdtDate, String lstUpdtBy) {
		super();
		this.id = id;
		this.isAccepted = isAccepted;
		this.isRejected = isRejected;
		this.isCustAccepted = isCustAccepted;
		this.isCustRejected = isCustRejected;
		this.proposedAmt = proposedAmt;
		this.finalAmt = finalAmt;
		this.damageCompensationAmt = damageCompensationAmt;
		this.tip = tip;
		this.isAbsent = isAbsent;
		this.maidRating = maidRating;
		this.lstUpdtDate = lstUpdtDate;
		this.lstUpdtBy = lstUpdtBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Boolean getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(Boolean isRejected) {
		this.isRejected = isRejected;
	}

	public Boolean getIsCustAccepted() {
		return isCustAccepted;
	}

	public void setIsCustAccepted(Boolean isCustAccepted) {
		this.isCustAccepted = isCustAccepted;
	}

	public Boolean getIsCustRejected() {
		return isCustRejected;
	}

	public void setIsCustRejected(Boolean isCustRejected) {
		this.isCustRejected = isCustRejected;
	}
	
	public BigDecimal getProposedAmt() {
		return proposedAmt;
	}

	public void setProposedAmt(BigDecimal proposedAmt) {
		this.proposedAmt = proposedAmt;
	}

	public BigDecimal getFinalAmt() {
		return finalAmt;
	}

	public void setFinalAmt(BigDecimal finalAmt) {
		this.finalAmt = finalAmt;
	}

	public BigDecimal getDamageCompensationAmt() {
		return damageCompensationAmt;
	}

	public void setDamageCompensationAmt(BigDecimal damageCompensationAmt) {
		this.damageCompensationAmt = damageCompensationAmt;
	}

	public BigDecimal getTip() {
		return tip;
	}

	public void setTip(BigDecimal tip) {
		this.tip = tip;
	}

	public Boolean getIsAbsent() {
		return isAbsent;
	}

	public void setIsAbsent(Boolean isAbsent) {
		this.isAbsent = isAbsent;
	}

	public Float getMaidRating() {
		return maidRating;
	}

	public void setMaidRating(Float maidRating) {
		this.maidRating = maidRating;
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

	public Maid getMaid() {
		return maid;
	}

	public void setMaid(Maid maid) {
		this.maid = maid;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "RequestDetail [id=" + id + ", isAccepted=" + isAccepted + ", isRejected=" + isRejected
				+ ", isCustAccepted=" + isCustAccepted + ", isCustRejected=" + isCustRejected + ", proposedAmt=" + proposedAmt + ", finalAmt=" + finalAmt
				+ ", damageCompensationAmt=" + damageCompensationAmt + ", tip=" + tip + ", isAbsent=" + isAbsent + ", maidRating=" + maidRating
				+ ", lstUpdtDate=" + lstUpdtDate + ", lstUpdtBy=" + lstUpdtBy + ", maid=" + maid + ", request="
				+ request + "]";
	}
}
