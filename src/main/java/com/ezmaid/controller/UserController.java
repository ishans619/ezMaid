package com.ezmaid.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.ChangePassDTO;
import com.ezmaid.entity.User;
import com.ezmaid.exception.PasswordMismatchException;
import com.ezmaid.exception.UserNotFoundException;
import com.ezmaid.service.UserService;
import com.ezmaid.service.UtilityService;
import com.ezmaid.util.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private UtilityService utilityService;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserService userService, UtilityService utilityService, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.utilityService = utilityService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/changepass")
	public Boolean changePassword(@RequestBody ChangePassDTO changePassDTO) {
		
		User existingUser = userService.getUserByUsername(changePassDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("Username %s not found", changePassDTO.getUsername())));
		
		if (!passwordEncoder.matches(changePassDTO.getCurrpassword(), existingUser.getPassword())) {
			throw new PasswordMismatchException("Current password entered is not correct!");
		}
		
		existingUser.setPassword(passwordEncoder.encode(changePassDTO.getNewpassword()));
		userService.saveUser(existingUser);
		
		return true;
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/deactivate")
	public Boolean deactivateUser(@RequestBody ChangePassDTO changePassDTO) {
		
		User existingUser = userService.getUserByUsername(changePassDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("Username %s not found", changePassDTO.getUsername())));
		
		existingUser.setIsActive(false);
		userService.saveUser(existingUser);
		
		return true;
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/activate")
	public Boolean activateUser(@RequestBody ChangePassDTO changePassDTO) {
		
		User existingUser = userService.getUserByUsername(changePassDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("Username %s not found", changePassDTO.getUsername())));
		
		existingUser.setIsActive(true);
		userService.saveUser(existingUser);
		
		return true;
	}
}
