package com.flightapp.service;

import java.util.List;

import com.flightapp.entities.Airline;

public interface AirlineService {
	
	// method to add airline and it return air line
	Airline saveAirline(Airline airline);
	
	// method to view All airlines
	List<Airline> getAllAirlines();
	
	//method to view airline by Id
	Airline getAirlineById(int id);
	
	// method top modify airline details
	Airline updateAirline(Airline airline, int id);
	
	//method to delete airline
	void deleteAirlineById(int id);

}
