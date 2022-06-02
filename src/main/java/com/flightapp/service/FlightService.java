package com.flightapp.service;

import java.util.List;

import com.flightapp.dto.FlightRequest;
import com.flightapp.dto.FlightResponse;
import com.flightapp.entities.Flight;

public interface FlightService {

	// method to add flight and it return flight
	FlightRequest saveFlight(FlightRequest request);

	// method to view All Flights available
	List<FlightResponse> getAllFlights();

	// method to view flight by its number
	FlightResponse getFlightByNumber(int number);

	// method to modify Flight Details
	FlightResponse updateFlight(Flight flight, int number);

	// method to delete flight by its number
	void deleteFlightByNumber(int number);

	// method to get AllFlight by AirlineName
	//	List<FlightResponse> getFlightsByAirlineName(String airlineName);

	// get Flight Details along with AirlineName
	// FlightResponse getFlightByIdAlongWithAirlineName(int fnumber);

	// get All Flights Details with Airline Name
	// List<FlightResponse> getAllFlightsAlongWithAirlineName();

}
