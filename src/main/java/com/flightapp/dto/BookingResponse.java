package com.flightapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

	private int bookingId;

	private String email;

	private long pnrNumber;

	private int flightNumber;

	private String airlineName;

	private int passengerId;

	private String passengerName;

	private String passengerGender;

	private int passengerAge;

	private int seatNumber;

	private Boolean optforMeals;

	private String mealType;

}
