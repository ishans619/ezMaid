package com.ezmaid.util;

public interface AppConstants {
	
	String ADMIN = "Admin";
    String SUPER_ADMIN = "SuperAdmin";
    String CUSTOMER = "Customer";
    String MAID = "Maid";
 
    String REQUEST_TYPE_OPEN = "Open";
    String REQUEST_TYPE_CLOSE = "Close";
    
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "auth-api";
    String TOKEN_AUDIENCE = "ezmaid";
    
    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    
    String VALIDATION_FAILED_DETAIL = "Form fields are invalid, please correct and resubmit!";
    
    public static final String BEARER_KEY_SECURITY_SCHEME = "bearer-key";
}
