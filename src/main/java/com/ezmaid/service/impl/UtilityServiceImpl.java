package com.ezmaid.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ezmaid.security.CustomUserDetails;
import com.ezmaid.service.UtilityService;

@Service
public class UtilityServiceImpl implements UtilityService {

	@Override
	public String getLoggedInUserId() {
		
		String loggedInUserId = "";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	      Object principal = authentication.getPrincipal();
	      if (principal instanceof UserDetails) {
	    	  CustomUserDetails userDetails = (CustomUserDetails) principal;
	    	  loggedInUserId = userDetails.getId();
	        // You can also get other user details like authorities, password, etc. from userDetails
	        // ...
	      } else {
	        // handle the case where principal is not a UserDetails object
	        // ...
	      }
	    } else {
	      // handle the case where authentication is null or not authenticated
	      // ...
	    }
	    
	    return loggedInUserId;
	}

}
