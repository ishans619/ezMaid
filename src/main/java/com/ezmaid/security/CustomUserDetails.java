package com.ezmaid.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Boolean isVerified;
    private Collection<? extends GrantedAuthority> authorities;

    
//    Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. 
    public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}
    
    public CustomUserDetails(String id, String username, String password, String name, String email, Boolean isVerified,
    		Collection<? extends GrantedAuthority> authorities) {
    	super();
    	this.id = id;
    	this.username = username;
    	this.password = password;
    	this.name = name;
    	this.email = email;
    	this.isVerified = isVerified;
    	this.authorities = authorities;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

	@Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", isVerified=" + isVerified + ", authorities=" + authorities + "]";
	}
	
}
