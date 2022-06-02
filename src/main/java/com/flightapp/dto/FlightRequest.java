package com.flightapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest { 

	// This DTO Class is to send data to Flight Table along with Airline Id
	// In Order to save a Flight Details in Flight Table I need Airline Id to which Flight is Associated.
	// Multiple Flights belongs to One Airline (ManyToOne)
	private int flightNumber;
	private int flightCapacity;
	private int airlineId;
	
}
