package com.flightapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
	

	public Iterable<Flight> findByAirlineAirlineName(String airlineName);
	
	public Flight findByFlightNumber(int fnumber);

}
