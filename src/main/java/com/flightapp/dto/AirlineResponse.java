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
	
	private int airlineId;
	private String airlineAddress;
	private long airlineContactNumber;
	private String airlineName;
	private int flightNumber;
	private int flightCapacity;

}
