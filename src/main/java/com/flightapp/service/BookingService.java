package com.flightapp.service;

import com.flightapp.dto.BookingRequest;
import com.flightapp.dto.BookingResponse;
import com.flightapp.entities.Booking;

public interface BookingService {

	Booking bookFlightTicket(BookingRequest request);

	void cancelBooking(int id);
	
	BookingResponse getBookingDetailswithEmail(String email); 
	
	BookingResponse getBookingDetailswithpnrNumber(long pnrNumber);

}
