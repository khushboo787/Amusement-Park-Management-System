package com.JoyLand.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<ErrorDetails> methodArgumentInvalidHandler(MethodArgumentNotValidException manv, WebRequest req) {
		log.error("Invalid data for customer object");
		
		ErrorDetails ed = new ErrorDetails();		
		ed.setTimestamp(LocalDateTime.now());
		List<ObjectError> allErrors = manv.getAllErrors();
		String message = String.join(", ", allErrors.toString());
		ed.setMessage(message);
		ed.setUri(req.getDescription(false));
		
		return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex, WebRequest req) {
		ErrorDetails ed = new ErrorDetails();
		
		ed.setTimestamp(LocalDateTime.now());
		ed.setMessage(ex.getMessage());
		ed.setUri(req.getDescription(false));
		
		return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorDetails> CustomerExceptionHandler(CustomerException ms, WebRequest req) {
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), ms.getMessage(), req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> AdminExceptionHandler(AdminException ms, WebRequest req) {
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), ms.getMessage(), req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<ErrorDetails> TicketExceptionHandler(TicketException ms, WebRequest req) {
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), ms.getMessage(), req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ActivityException.class)
	public ResponseEntity<ErrorDetails> ActivityExceptionHandler(ActivityException ms, WebRequest req) {
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), ms.getMessage(), req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> NoHandlerExceptionHandler(NoHandlerFoundException ms, WebRequest req) {
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), ms.getMessage(), req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}

}