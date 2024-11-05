package com.ezmaid.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
    
    @Column(name = "is_active")
    private Boolean isActive;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Customer customer;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Maid maid;
    
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Admin admin;
    
    public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String password, String role, Customer customer, Maid maid, Admin admin, Boolean isActive) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.customer = customer;
		this.maid = maid;
		this.admin = admin;
		this.isActive=isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Maid getMaid() {
		return maid;
	}

	public void setMaid(Maid maid) {
		this.maid = maid;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", customer="
				+ customer + ", maid=" + maid + ", admin=" + admin + ", isActive=" + isActive + "]";
	}
}
