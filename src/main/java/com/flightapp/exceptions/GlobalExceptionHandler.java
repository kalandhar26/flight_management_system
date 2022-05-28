package com.flightapp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Handling standard Exception
	
	@ExceptionHandler
	public ResponseEntity<?> handleAirlineNotFoundException(AirlineNotFoundException exception, WebRequest request){
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionDetails,HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleFlightNotFoundException(FlightNotFoundException exception, WebRequest request){
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionDetails,HttpStatus.NOT_FOUND);	
	}
	
	// Handling Global Exceptions
	@ExceptionHandler
	public ResponseEntity<?> handleGlobalExceptions(Exception exception, WebRequest request){
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionDetails,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	


}
