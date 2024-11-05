package com.ezmaid.dto;

public class ChangePassDTO {

	private String username; 
	private String currpassword;
	private String newpassword;
	
	public ChangePassDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ChangePassDTO(String username, String currpassword, String newpassword) {
		super();
		this.username = username;
		this.currpassword = currpassword;
		this.newpassword = newpassword;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCurrpassword() {
		return currpassword;
	}
	public void setCurrpassword(String currpassword) {
		this.currpassword = currpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
	@Override
	public String toString() {
		return "ChangePassDTO [username=" + username + ", currpassword=" + currpassword + ", newpassword=" + newpassword
				+ "]";
	}
	
}
