package com.flightapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineResponse {
	// This DTO Class is to fetch Airline Data and All Flight under Particular Airline using Airline Details
	// Get All Airlines along with Flight Details or Get a Single Airline along with Flight Details
	// One Airline Can have Many Flights (OneToMany)
	private int airlineId;
	private String airlineAddress;
	private long airlineContactNumber;
	private String airlineName;

}
