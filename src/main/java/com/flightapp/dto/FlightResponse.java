package com.flightapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
	
	private int flightNumber;
	private int flightCapacity;
	private int airlineId;
	private String airlineName;
	private String airlineAddress;
	private long airlineContactNumber;
	

}
