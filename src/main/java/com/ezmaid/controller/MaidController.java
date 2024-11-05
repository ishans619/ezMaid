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
import com.ezmaid.entity.Maid;
import com.ezmaid.exception.ErrorDetails;
import com.ezmaid.service.MaidService;
import com.ezmaid.service.UtilityService;
import com.ezmaid.util.AppConstants;
import com.ezmaid.util.AppUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class MaidController {

	private static final Logger log = LoggerFactory.getLogger(MaidController.class);

	private MaidService maidService;
	private UtilityService utilityService;

	public MaidController(MaidService maidService, UtilityService utilityService) {
		super();
		this.maidService = maidService;
		this.utilityService = utilityService;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/maids")
	public String update(@RequestBody SignUpRequest maidDTO) {
		log.info("maid data passed = "+ maidDTO);
		Maid existingMaid = maidService.fetchOne(maidDTO.getId());
		BeanUtils.copyProperties(maidDTO, existingMaid);
		log.info("Copied values to maid: {}", existingMaid);
		String maidId = maidService.updateMaid(existingMaid);
		return maidId;
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping(path = "/maids/verify")
	public String verify(@RequestBody SignUpRequest maidDTO) {
		log.info("maid data passed = "+ maidDTO);
		Maid existingMaid = maidService.fetchOne(maidDTO.getId());
		
		existingMaid.setIsVerified(true);
		existingMaid.setLstUpdtBy(utilityService.getLoggedInUserId());
		existingMaid.setLstUpdtDate(LocalDate.now());
		
		String maidId = maidService.updateMaid(existingMaid);
		return maidId;
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/maids/{maidId}")
	public Maid fetchOne(@PathVariable("maidId") String maidId) {
		return maidService.fetchOne(maidId); 
	}

	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/maids")
	public List<Maid> fetchAll() {
		return maidService.fetchAll(); 
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@GetMapping(path = "/maids/customers")
	public List<Maid> fetchAllForCustomer() {
		return maidService.fetchAllVerified(); 
	}
	
	@Operation(security = {@SecurityRequirement(name = AppConstants.BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping(path = "/maids/{maidId}")
	public String delete(@PathVariable("maidId") String maidId) {
		maidService.deleteOne(maidId); 
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
