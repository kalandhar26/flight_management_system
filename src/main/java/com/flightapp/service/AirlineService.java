package com.flightapp.service;

import java.util.List;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.dto.AirlineResponse;
import com.flightapp.entities.Airline;

public interface AirlineService {
	
	// method to add airline and it return air line
	AirlineRequest saveAirline(Airline airline);
	
	// method to view All airlines
	List<AirlineResponse> getAllAirlines();
	
	//method to view airline by Id
	AirlineResponse getAirlineById(int id);
	
	// method top modify airline details
	AirlineResponse updateAirline(Airline airline, int id);
	
	//method to delete airline
	void deleteAirlineById(int id);
	
	// method to get both Airline and Flight Details
	// List<AirlineResponse> getAllAirlinesWithFlightDetails();
	
	// method to get both Airline and Flight Details with airline Id
	// AirlineResponse getAirlinesByIdWithFlightDetails(int id);

}
