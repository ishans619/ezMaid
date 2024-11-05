package com.ezmaid.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.AuthResponse;
import com.ezmaid.dto.LoginRequest;
import com.ezmaid.dto.SignUpRequest;
import com.ezmaid.entity.Customer;
import com.ezmaid.entity.Maid;
import com.ezmaid.entity.User;
import com.ezmaid.exception.DuplicatedUserInfoException;
import com.ezmaid.exception.ErrorDetails;
import com.ezmaid.exception.UserInactiveException;
import com.ezmaid.security.CustomUserDetails;
import com.ezmaid.security.TokenProvider;
import com.ezmaid.service.CustomerService;
import com.ezmaid.service.MaidService;
import com.ezmaid.service.UserService;
import com.ezmaid.util.AppConstants;
import com.ezmaid.util.AppUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final MaidService maidService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

	public AuthController(UserService userService, MaidService maidService, CustomerService customerService,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
		super();
		this.userService = userService;
		this.maidService = maidService;
		this.customerService = customerService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	@PostMapping("/authenticate")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUsername()));
        }
        
        Customer savedCustomer=null;
        Maid savedMaid=null;
        String token ="";

        if (signUpRequest.getIsCustomer()) {
			// map details to Customer
        	Customer customerToBeSaved = new Customer();
        	customerToBeSaved.setfName(signUpRequest.getfName());
        	customerToBeSaved.setmName(signUpRequest.getmName());
        	customerToBeSaved.setlName(signUpRequest.getlName());
        	customerToBeSaved.setEmail(signUpRequest.getEmail());
        	customerToBeSaved.setContactNumber(signUpRequest.getContactNumber());
        	customerToBeSaved.setAdharCardNumber(signUpRequest.getAdharCardNumber());
        	customerToBeSaved.setPanCardNumber(signUpRequest.getPanCardNumber());
        	customerToBeSaved.setAddress(signUpRequest.getAddress());
        	
        	User userToBeSaved = new User();
        	userToBeSaved.setUsername(signUpRequest.getUsername());
        	userToBeSaved.setRole(AppConstants.CUSTOMER);
        	userToBeSaved.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        	userToBeSaved.setIsActive(true);

        	customerToBeSaved.setUser(userToBeSaved);
        	userToBeSaved.setCustomer(customerToBeSaved);
        	
        	// call service to save customer
        	savedCustomer = customerService.saveCustomer(customerToBeSaved);
        	token = authenticateAndGetToken(savedCustomer.getUser().getUsername(), signUpRequest.getPassword());
        	
		} else {
			// map details to Maid
			Maid maidToBeSaved = new Maid();
			
        	maidToBeSaved.setfName(signUpRequest.getfName());
        	maidToBeSaved.setmName(signUpRequest.getmName());
        	maidToBeSaved.setlName(signUpRequest.getlName());
        	maidToBeSaved.setEmail(signUpRequest.getEmail());
        	maidToBeSaved.setContactNumber(signUpRequest.getContactNumber());
        	maidToBeSaved.setAdharCardNumber(signUpRequest.getAdharCardNumber());
        	maidToBeSaved.setPanCardNumber(signUpRequest.getPanCardNumber());
        	maidToBeSaved.setAddress(signUpRequest.getAddress());
        	
        	User userToBeSaved = new User();
        	userToBeSaved.setUsername(signUpRequest.getUsername());
        	userToBeSaved.setRole(AppConstants.MAID);
        	userToBeSaved.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        	userToBeSaved.setIsActive(true);

        	maidToBeSaved.setUser(userToBeSaved);
        	userToBeSaved.setMaid(maidToBeSaved);
        	
        	// call service to save maid
        	savedMaid = maidService.saveMaid(maidToBeSaved);
        	
        	token = authenticateAndGetToken(savedMaid.getUser().getUsername(), signUpRequest.getPassword());
		}
        
        return new AuthResponse(token);
    }

    private String authenticateAndGetToken(String username, String password) throws UserInactiveException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		StringBuilder sbError = new StringBuilder();
		
		ex.getBindingResult().getAllErrors().forEach(AppUtils.fetchErrors(sbError));
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				AppConstants.VALIDATION_FAILED_DETAIL, sbError.toString()); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}
}
