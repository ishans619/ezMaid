package com.ezmaid.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.AdminDTO;
import com.ezmaid.entity.Admin;
import com.ezmaid.entity.User;
import com.ezmaid.exception.ErrorDetails;
import com.ezmaid.service.AdminService;
import com.ezmaid.util.AppConstants;
import com.ezmaid.util.AppUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
public class AdminController {

	private AdminService adminService;
	private final PasswordEncoder passwordEncoder;

	public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
		super();
		this.adminService = adminService;
		this.passwordEncoder = passwordEncoder;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PostMapping(path = "/admins")
	public ResponseEntity<String> save(@Valid @RequestBody AdminDTO adminDTO) {
		System.out.println("adminDTO = "+ adminDTO);
		Admin admin = new Admin();
		BeanUtils.copyProperties(adminDTO, admin);
		System.out.println("Copied values to admin: " + admin);
		
		User userToBeSaved = new User();
    	userToBeSaved.setUsername(adminDTO.getUsername());
    	userToBeSaved.setRole(AppConstants.ADMIN);
    	userToBeSaved.setPassword(passwordEncoder.encode(adminDTO.getUsername() + adminDTO.getContactNumber().substring(6)));
    	userToBeSaved.setIsActive(true);
    	
    	admin.setUser(userToBeSaved);
    	userToBeSaved.setAdmin(admin);
    	
		String adminId = adminService.saveAdmin(admin);
		return ResponseEntity.ok(adminId);
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/admins")
	public String update(@Valid @RequestBody AdminDTO adminDTO) {

		System.out.println("adminDTO = "+ adminDTO);
		Admin existingAdmin = adminService.fetchOne(adminDTO.getAdminId());
		BeanUtils.copyProperties(adminDTO, existingAdmin);
		System.out.println("Copied values to admin: " + existingAdmin);
		String adminId = adminService.updateAdmin(existingAdmin);
		return adminId;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/admins/{adminId}")
	public Admin fetchOne(@PathVariable("adminId") String adminId) {
		return adminService.fetchOne(adminId); 
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/admins")
	public List<Admin> fetchAll() {
		return adminService.fetchAll(); 
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping(path = "/admins/{adminId}")
	public String delete(@PathVariable("adminId") String adminId) {
		return adminService.deleteOne(adminId); 
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


