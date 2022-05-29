package com.flightapp.service;

import java.util.List;

import com.flightapp.dto.FlightRequest;
import com.flightapp.dto.FlightResponse;
import com.flightapp.entities.Flight;

public interface FlightService {

	// method to add flight and it return flight
	Flight saveFlight(FlightRequest request);

	// method to view All Flights available
	Iterable<Flight> getAllFlights();

	// method to get AllFlight by AirlineName
	Iterable<Flight> getFlightsByAirlineName(String airlineName);

	// method to view flight by its number
	Flight getFlightByNumber(int number);

	// method to modify Flight Details
	Flight updateFlight(Flight flight, int number);

	// method to delete flight by its number
	void deleteFlightByNumber(int number);
	
	// get Flight Details along with AirlineName
	FlightResponse getFlightByIdAlongWithAirlineName(int fnumber);
	
	//get All Flights Details with Airline Name
	List<FlightResponse> getAllFlightsAlongWithAirlineName();

}
