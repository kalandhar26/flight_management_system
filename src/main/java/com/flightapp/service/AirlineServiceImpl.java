package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.dto.AirlineResponse;
import com.flightapp.entities.Airline;
import com.flightapp.exceptions.AirlineNotFoundException;
import com.flightapp.repos.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	public AirlineServiceImpl(AirlineRepository airlineRepository) {
		super();
		this.airlineRepository = airlineRepository;
	}

	@Override
	public Airline saveAirline(Airline airline) {
		
		return airlineRepository.save(airline);
	}

	@Override
	public List<Airline> getAllAirlines() {		
		return airlineRepository.findAll();
	}

	@Override
	public Airline getAirlineById(int id) {
		return airlineRepository.findById(id).orElseThrow(
				() -> new AirlineNotFoundException("Airline", "Id", id));
	}

	@Override
	public Airline updateAirline(Airline airline, int id) {
		// Checking Airline existing or not in DB
		Airline existingAirline = airlineRepository.findById(id).orElseThrow(
				() -> new AirlineNotFoundException("Airline", "Id", id));
		
		existingAirline.setAirlineName(airline.getAirlineName());
		existingAirline.setAirlineContactNumber(airline.getAirlineContactNumber());
		existingAirline.setAirlineAddress(airline.getAirlineAddress());
		// Saving the modified data 
		return airlineRepository.save(existingAirline);
	}

	@Override
	public void deleteAirlineById(int id) {
		// Checking Airline existing or not in DB
				airlineRepository.findById(id).orElseThrow(
						() -> new AirlineNotFoundException("Airline", "Id", id));
		// If Present deleting the airline
				airlineRepository.deleteById(id);	

	}

	@Override
	public List<AirlineResponse> getAllAirlinesWithFlightDetails() {
		List<Airline> airlines = airlineRepository.findAll();
		List<AirlineResponse> responselist = new ArrayList<>();

		airlines.forEach(airline -> {
			AirlineResponse airlineResponse = new AirlineResponse();
			airlineResponse.setAirlineId(airline.getAirlineId());
			airlineResponse.setAirlineName(airline.getAirlineName());
			airlineResponse.setAirlineAddress(airline.getAirlineAddress());
			airlineResponse.setAirlineContactNumber(airline.getAirlineContactNumber());
			Airline airline2 = airline;
			airline2.getFlights().forEach(flight -> {
				airlineResponse.setFlightCapacity(flight.getFlightCapacity());
				airlineResponse.setFlightNumber(flight.getFlightNumber());
			});

			responselist.add(airlineResponse);

		});
		return responselist;
	}

	@Override
	public AirlineResponse getAirlinesByIdWithFlightDetails(int id) {
		Airline airline = airlineRepository.findByAirlineId(id);
		AirlineResponse response = new AirlineResponse();
		response.setAirlineId(airline.getAirlineId());
		response.setAirlineName(airline.getAirlineName());
		response.setAirlineAddress(airline.getAirlineAddress());
		response.setAirlineContactNumber(airline.getAirlineContactNumber());
		airline.getFlights().forEach(x -> {
			response.setFlightCapacity(x.getFlightCapacity());
			response.setFlightNumber(x.getFlightNumber());
		});
		return response;
	}

}
