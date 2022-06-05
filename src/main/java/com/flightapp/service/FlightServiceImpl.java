package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.flightapp.dto.FlightRequest;
import com.flightapp.dto.FlightResponse;
import com.flightapp.entities.Airline;
import com.flightapp.entities.Flight;
import com.flightapp.exceptions.AirlineNotFoundException;
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
	public FlightRequest saveFlight(FlightRequest request) {
		Airline airline = airlineRepository.findByAirlineId(request.getAirlineId());
		if(null != airline || !ObjectUtils.isEmpty(airline)) {
		Flight flight = new Flight();
		flight.setFlightNumber(request.getFlightNumber());
		flight.setFlightCapacity(request.getFlightCapacity());
		flight.setAirline(airline);
		flightRepository.save(flight);

		request.setAirlineId(airline.getAirlineId());
		request.setFlightCapacity(flight.getFlightCapacity());
		request.setFlightNumber(flight.getFlightNumber());

		return request;
		}else
		{
			throw new AirlineNotFoundException("Airline", "Id", request.getAirlineId());
		}
	}

	// Implementation method to get All flights
	@Override
	public List<FlightResponse> getAllFlights() {
		List<Flight> flights = flightRepository.findAll();
		List<FlightResponse> responseList = new ArrayList<>();
		flights.forEach(flight -> {
			FlightResponse flightResponse = new FlightResponse();
			flightResponse.setFlightNumber(flight.getFlightNumber());
			flightResponse.setFlightCapacity(flight.getFlightCapacity());
			flightResponse.setAirlineId(flight.getAirline().getAirlineId());
			flightResponse.setAirlineName(flight.getAirline().getAirlineName());
			flightResponse.setAirlineContactNumber(flight.getAirline().getAirlineContactNumber());
			flightResponse.setAirlineAddress(flight.getAirline().getAirlineAddress());
			responseList.add(flightResponse);
			
		});
		
		return responseList;
	}
	
	// Implementation method to get flight by flight Number
		@Override
		public FlightResponse getFlightByNumber(int number) {
			Flight flight = flightRepository.findById(number).orElseThrow(()-> new FlightNotFoundException("Flight", "number", number));
			
			FlightResponse response = new FlightResponse();
			response.setAirlineId(flight.getAirline().getAirlineId());
			response.setAirlineName(flight.getAirline().getAirlineName());
			response.setAirlineContactNumber(flight.getAirline().getAirlineContactNumber());
			response.setAirlineAddress(flight.getAirline().getAirlineAddress());
			response.setFlightCapacity(flight.getFlightCapacity());
			response.setFlightNumber(flight.getFlightNumber());
			
			return response;
		}

		// Implementation method to update flight
		@Override
		public FlightResponse updateFlight(Flight flight, int number) {
			// Checking Flight available in DB or not
			Flight existingFlight = flightRepository.findById(number)
					.orElseThrow(() -> new FlightNotFoundException("Flight", "number", number));
			existingFlight.setFlightCapacity(flight.getFlightCapacity());
			// Saving updated data in DB
			flightRepository.save(existingFlight);
			// Passing Data to Response Class Object
			FlightResponse response = new FlightResponse();
			response.setFlightCapacity(existingFlight.getFlightCapacity());
			response.setFlightNumber(existingFlight.getFlightNumber());
			response.setAirlineId(existingFlight.getAirline().getAirlineId());
			response.setAirlineName(existingFlight.getAirline().getAirlineName());
			response.setAirlineAddress(existingFlight.getAirline().getAirlineAddress());
			response.setAirlineContactNumber(existingFlight.getAirline().getAirlineContactNumber());
			 return response;
		}

		// Implementation method to delete flight
		@Override
		public void deleteFlightByNumber(int number) {
			// Checking Flight available in DB or not
			flightRepository.findById(number).orElseThrow(() -> new FlightNotFoundException("Flight", "number", number));
			// If present delete the flight
			flightRepository.deleteById(number);

		}
		
		
	// Implementation method to get All flights by flightName
//	@Override
//	public List<FlightResponse> getFlightsByAirlineName(String airlineName) {
//		List<FlightResponse> flights = new ArrayList<>();	
//		flightRepository.findByAirlineAirlineName(airlineName).forEach(flights::add);
//		return flights;
//	}

		
	/*
	 * @Override public FlightResponse getFlightByIdAlongWithAirlineName(int
	 * fnumber) { Flight flight = flightRepository.findByFlightNumber(fnumber);
	 * FlightResponse response = new FlightResponse();
	 * response.setFlightNumber(flight.getFlightNumber());
	 * response.setFlightCapacity(flight.getFlightCapacity());
	 * response.setAirlineName(flight.getAirline().getAirlineName());
	 * response.setAirlineId(flight.getAirline().getAirlineId()); return response; }
	 * 
	 * @Override public List<FlightResponse> getAllFlightsAlongWithAirlineName() {
	 * List<Flight> flights = flightRepository.findAll(); List<FlightResponse>
	 * responseList = new ArrayList<>();
	 * 
	 * flights.forEach(flight -> { FlightResponse response = new FlightResponse();
	 * response.setAirlineId(flight.getAirline().getAirlineId());
	 * response.setAirlineName(flight.getAirline().getAirlineName());
	 * response.setFlightCapacity(flight.getFlightCapacity());
	 * response.setFlightNumber(flight.getFlightNumber());
	 * 
	 * responseList.add(response); }); return responseList; }
	 */

}
