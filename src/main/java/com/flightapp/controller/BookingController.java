package com.flightapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.BookingRequest;
import com.flightapp.dto.BookingResponse;
import com.flightapp.entities.Booking;
import com.flightapp.service.BookingService;

@RestController
@RequestMapping("/api")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/booking")
	public ResponseEntity<Booking> bookFLight(@Valid @RequestBody BookingRequest request) {
		return new ResponseEntity<Booking>(bookingService.bookFlightTicket(request), HttpStatus.CREATED);
	}

	@GetMapping("/booking/email/{email}")
	public ResponseEntity<BookingResponse> getBookingDetailsWithEmail(@PathVariable("email") String email) {
		return new ResponseEntity<BookingResponse>(bookingService.getBookingDetailswithEmail(email), HttpStatus.OK);
	}

	@GetMapping("/booking/pnr/{pnrnumber}")
	public ResponseEntity<BookingResponse> getBookingDetailsWithpnrNumber(@PathVariable("pnrnumber") long pnrNumber) {
		return new ResponseEntity<BookingResponse>(bookingService.getBookingDetailswithpnrNumber(pnrNumber),
				HttpStatus.OK);
	}

}
