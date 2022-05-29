package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.dto.FlightRequest;
import com.flightapp.dto.FlightResponse;
import com.flightapp.entities.Airline;
import com.flightapp.entities.Flight;
import com.flightapp.exceptions.FlightNotFoundException;
import com.flightapp.repos.AirlineRepository;
import com.flightapp.repos.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AirlineRepository airlineRepository;

	public FlightServiceImpl(FlightRepository flightRepository, AirlineRepository airlineRepository) {
		super();
		this.flightRepository = flightRepository;
		this.airlineRepository = airlineRepository;
	}

	// Implementation method to add flight
	@Override
	public Flight saveFlight(FlightRequest request) {
		Airline airline = airlineRepository.findByAirlineId(request.getAirlineId());
		Flight flight = new Flight();
		flight.setFlightCapacity(request.getFlightCapacity());
		flight.setAirline(airline);

		return flightRepository.save(flight);
	}

	// Implementation method to get All flights
	@Override
	public Iterable<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	// Implementation method to get All flights by flightName
	@Override
	public Iterable<Flight> getFlightsByAirlineName(String airlineName) {
		List<Flight> flights = new ArrayList<Flight>();
		flightRepository.findByAirlineAirlineName(airlineName).forEach(flights::add);
		;
		return flights;
	}

	// Implementation method to get flight by flight Number
	@Override
	public Flight getFlightByNumber(int number) {
		return flightRepository.findById(number)
				.orElseThrow(() -> new FlightNotFoundException("Flight", "number", number));
	}

	// Implementation method to update flight
	@Override
	public Flight updateFlight(Flight flight, int number) {
		// Checking Flight available in DB or not
		Flight existingFlight = flightRepository.findById(number)
				.orElseThrow(() -> new FlightNotFoundException("Flight", "number", number));
		existingFlight.setFlightCapacity(flight.getFlightCapacity());
		// Saving updated data in DB
		return flightRepository.save(existingFlight);

	}

	// Implementation method to delete flight
	@Override
	public void deleteFlightByNumber(int number) {

		// Checking Flight available in DB or not
		flightRepository.findById(number).orElseThrow(() -> new FlightNotFoundException("Flight", "number", number));
		// If present delete the flight
		flightRepository.deleteById(number);

	}

	@Override
	public FlightResponse getFlightByIdAlongWithAirlineName(int fnumber) {
		Flight flight = flightRepository.findByFlightNumber(fnumber);
		FlightResponse response = new FlightResponse();
		response.setFlightNumber(flight.getFlightNumber());
		response.setFlightCapacity(flight.getFlightCapacity());
		response.setAirlineName(flight.getAirline().getAirlineName());
		response.setAirlineId(flight.getAirline().getAirlineId());
		return response;
	}

	@Override
	public List<FlightResponse> getAllFlightsAlongWithAirlineName() {
		List<Flight> flights = flightRepository.findAll();
		List<FlightResponse> responseList = new ArrayList<>();
		
		flights.forEach(flight -> {
			FlightResponse response = new FlightResponse();
			response.setAirlineId(flight.getAirline().getAirlineId());
			response.setAirlineName(flight.getAirline().getAirlineName());
			response.setFlightCapacity(flight.getFlightCapacity());
			response.setFlightNumber(flight.getFlightNumber());
			
			responseList.add(response);
		});
		return responseList;
	}

}
