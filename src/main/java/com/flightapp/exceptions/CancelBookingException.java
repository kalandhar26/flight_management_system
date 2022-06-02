package com.flightapp.exceptions;

public class CancelBookingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CancelBookingException(String messgae) {
		super("You Cannot Cancel ticket in Last 24 hours");

	}

}
