package com.ezmaid.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezmaid.dto.SignUpRequest;
import com.ezmaid.entity.Customer;
import com.ezmaid.exception.ErrorDetails;
import com.ezmaid.service.CustomerService;
import com.ezmaid.service.UtilityService;
import com.ezmaid.util.AppConstants;
import com.ezmaid.util.AppUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private CustomerService customerService;
	private UtilityService utilityService;

	public CustomerController(CustomerService customerService, UtilityService utilityService) {
		super();
		this.customerService = customerService;
		this.utilityService = utilityService;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/customers")
	public String update(@RequestBody SignUpRequest customerDTO) {
		log.info("customer data passed = "+ customerDTO);
		Customer existingCustomer = customerService.fetchOne(customerDTO.getId());
		BeanUtils.copyProperties(customerDTO, existingCustomer);
		log.info("Copied values to customer: {}", existingCustomer);
		String custId = customerService.updateCustomer(existingCustomer);
		return custId;
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/customers/verify")
	public String verify(@RequestBody SignUpRequest customerDTO) {
		
		Customer existingCustomer = customerService.fetchOne(customerDTO.getId());
		
		existingCustomer.setIsVerified(true);
		existingCustomer.setLstUpdtBy(utilityService.getLoggedInUserId());
		existingCustomer.setLstUpdtDate(LocalDate.now());
		
		String custId = customerService.updateCustomer(existingCustomer);
		return custId;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/customers/{custId}")
	public Customer fetchOne(@PathVariable("custId") String custId) {
		return customerService.fetchOne(custId); 
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/customers")
	public List<Customer> fetchAll() {
		List<Customer> custList = customerService.fetchAll();
		
		for (Customer customer : custList) {
			customer.setRequest(null);
		}
		
		return custList; 
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping(path = "/customers/{custId}")
	public String delete(@PathVariable("custId") String custId) {
		customerService.deleteOne(custId); 
		return "deleted";
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
