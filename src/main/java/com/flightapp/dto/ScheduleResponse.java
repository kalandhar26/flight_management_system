package com.flightapp.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
	
	private int ScheduleId;
	private String sourceLocation;
	private String destinationLocation;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private String departureDateTime;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private String arrivalDateTime;
	private double ticketPrice;
	private int availableSeats;
	private String status;	
	private int flightNumber;
	private int flightCapacity;
	private String airlineName;

}
