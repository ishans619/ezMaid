package com.ezmaid.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RequestDTO {

	private String rqstId;
	
	@NotBlank(message = "Request title is mandatory")
	private String rqstType;
	
	@NotBlank(message = "Request title is mandatory")
	private String rqstTitle;
	
	@NotBlank(message = "Request description is mandatory")
	private String rqstDesc;
	
//	@NotBlank(message = "Choose atleast one maid to raise your request")
	@Size(min = 1, message = "You should select at least one maid to raise your request!")
	List<String> maidIds;

	List<Float> maidRatings;
	
	Float custRating;
	
	public RequestDTO(String rqstId, String rqstType, String rqstTitle, String rqstDesc, List<String> maidIds, List<Float> maidRatings, Float custRating) {
		super();
		this.rqstId = rqstId;
		this.rqstType = rqstType;
		this.rqstTitle = rqstTitle;
		this.rqstDesc = rqstDesc;
		this.maidIds = maidIds;
		this.maidRatings = maidRatings;
		this.custRating = custRating;
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
	
	public String getRqstDesc() {
		return rqstDesc;
	}
	
	public void setRqstDesc(String rqstDesc) {
		this.rqstDesc = rqstDesc;
	}

	public List<String> getMaidIds() {
		return maidIds;
	}

	public void setMaidIds(List<String> maidIds) {
		this.maidIds = maidIds;
	}

	public List<Float> getMaidRatings() {
		return maidRatings;
	}

	public void setMaidRatings(List<Float> maidRatings) {
		this.maidRatings = maidRatings;
	}

	public Float getCustRating() {
		return custRating;
	}

	public void setCustRating(Float custRating) {
		this.custRating = custRating;
	}

	@Override
	public String toString() {
		return "RequestDTO [rqstId=" + rqstId + ", rqstType=" + rqstType + ", rqstTitle=" + rqstTitle + ", rqstDesc="
				+ rqstDesc + ", maidIds=" + maidIds + ", maidRatings=" + maidRatings + ", custRating=" + custRating + "]";
	}
	
}
