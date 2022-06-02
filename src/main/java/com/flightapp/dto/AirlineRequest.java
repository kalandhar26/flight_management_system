package com.flightapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirlineRequest {
	
	private int airlineId;
	private String airlineName;
	private long airlineContactNumber;
	private String airlineAddress;
	

}
