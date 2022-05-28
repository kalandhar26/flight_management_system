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
	
	private int flightNumber;
	private int flightCapacity;
	private String airlineName;
	private int airlineId;
}
