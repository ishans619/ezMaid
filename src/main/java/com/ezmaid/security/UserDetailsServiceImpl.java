package com.ezmaid.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezmaid.entity.User;
import com.ezmaid.exception.UserInactiveException;
import com.ezmaid.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	public UserDetailsServiceImpl(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userService.getUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

		if(!user.getIsActive()) {
			throw new UserInactiveException(String.format("User %s is inactive, please contact our support team.", username));  
		}
		List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
		return mapUserToCustomUserDetails(user, authorities);
	}

	private CustomUserDetails mapUserToCustomUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
		CustomUserDetails customUserDetails = new CustomUserDetails();

		if (user.getCustomer()!=null) {
			customUserDetails.setId(user.getCustomer().getCustomerId());
			customUserDetails.setIsVerified(user.getCustomer().getIsVerified());
		} else if (user.getMaid()!=null) {
			customUserDetails.setId(user.getMaid().getMaidId());
			customUserDetails.setIsVerified(user.getMaid().getIsVerified());
		} else if (user.getAdmin()!=null) {
			customUserDetails.setId(user.getAdmin().getAdminId());
		}

		customUserDetails.setUsername(user.getUsername());
		customUserDetails.setPassword(user.getPassword());

		if (user.getCustomer()!=null) {
			customUserDetails.setName(user.getCustomer().getfName());
			customUserDetails.setEmail(user.getCustomer().getEmail());
		} else if (user.getMaid()!=null) {
			customUserDetails.setName(user.getMaid().getfName());
			customUserDetails.setEmail(user.getMaid().getEmail());
		} else if (user.getAdmin()!=null) {
			customUserDetails.setName(user.getAdmin().getfName());
			customUserDetails.setEmail(user.getAdmin().getEmail());
		}

		customUserDetails.setAuthorities(authorities);

		return customUserDetails;
	}
}
