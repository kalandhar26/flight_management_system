package com.flightapp.dto;

import java.util.List;

import com.flightapp.entities.Passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

	private int bookingId;

	private String email;

	private int flightNumber;
	
	private String airlineName;
	
	List<Passenger> passengers;
}
