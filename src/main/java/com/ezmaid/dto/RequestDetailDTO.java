package com.ezmaid.dto;

public class RequestDetailDTO {

	private Long id;

	public RequestDetailDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestDetailDTO(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RequestDetailDTO [id=" + id + "]";
	}

}
