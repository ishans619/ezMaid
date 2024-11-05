package com.ezmaid.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MaidNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleMaidNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleAdminNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(RequestNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleRequestNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(PasswordMismatchException.class)
	public final ResponseEntity<ErrorDetails> handlePasswordMismatchException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(RequestFinalizeException.class)
	public final ResponseEntity<ErrorDetails> handleRequestFinalizeException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(UserInactiveException.class)
	public final ResponseEntity<ErrorDetails> handleUserInactiveException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<ErrorDetails> handleAuthenticationException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	
}
