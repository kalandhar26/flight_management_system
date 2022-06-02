package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.dto.AirlineRequest;
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
	public AirlineRequest saveAirline(Airline airline) {

		airline = airlineRepository.save(airline);

		AirlineRequest request = new AirlineRequest();
		request.setAirlineId(airline.getAirlineId());
		request.setAirlineAddress(airline.getAirlineAddress());
		request.setAirlineContactNumber(airline.getAirlineContactNumber());
		request.setAirlineName(airline.getAirlineName());
		return request;
	}

	@Override
	public List<AirlineResponse> getAllAirlines() {
		List<Airline> airlines = airlineRepository.findAll();
		List<AirlineResponse> responselist = new ArrayList<>();

		airlines.forEach(airline -> {
			AirlineResponse airlineResponse = new AirlineResponse();
			airlineResponse.setAirlineId(airline.getAirlineId());
			airlineResponse.setAirlineName(airline.getAirlineName());
			airlineResponse.setAirlineAddress(airline.getAirlineAddress());
			airlineResponse.setAirlineContactNumber(airline.getAirlineContactNumber());
			responselist.add(airlineResponse);
		});

		return responselist;
	}

	@Override
	public AirlineResponse getAirlineById(int id) {
		Airline airline = airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException("Airline", "Id", id));
		AirlineResponse response = new AirlineResponse();
		response.setAirlineId(airline.getAirlineId());
		response.setAirlineName(airline.getAirlineName());
		response.setAirlineAddress(airline.getAirlineAddress());
		response.setAirlineContactNumber(airline.getAirlineContactNumber());
		return response;
	}

	@Override
	public AirlineResponse updateAirline(Airline airline, int id) {
		// Checking Airline existing or not in DB
		Airline existingAirline = airlineRepository.findById(id)
				.orElseThrow(() -> new AirlineNotFoundException("Airline", "Id", id));
		existingAirline.setAirlineName(airline.getAirlineName());
		existingAirline.setAirlineContactNumber(airline.getAirlineContactNumber());
		existingAirline.setAirlineAddress(airline.getAirlineAddress());
		// Saving the modified data
		airlineRepository.save(existingAirline);
		// Passing Data to Response Class
		AirlineResponse response = new AirlineResponse();
		response.setAirlineId(existingAirline.getAirlineId());
		response.setAirlineName(existingAirline.getAirlineName());
		response.setAirlineAddress(existingAirline.getAirlineAddress());
		response.setAirlineContactNumber(existingAirline.getAirlineContactNumber());
		
		return response;
		
	}

	@Override
	public void deleteAirlineById(int id) {
		// Checking Airline existing or not in DB
		airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException("Airline", "Id", id));
		// If Present deleting the airline
		airlineRepository.deleteById(id);

	}

//	@Override
//	public List<AirlineResponse> getAllAirlinesWithFlightDetails() {
//		List<Airline> airlines = airlineRepository.findAll();
//		List<AirlineResponse> responselist = new ArrayList<>();
//
//		airlines.forEach(airline -> {
//			AirlineResponse airlineResponse = new AirlineResponse();
//			airlineResponse.setAirlineId(airline.getAirlineId());
//			airlineResponse.setAirlineName(airline.getAirlineName());
//			airlineResponse.setAirlineAddress(airline.getAirlineAddress());
//			airlineResponse.setAirlineContactNumber(airline.getAirlineContactNumber());
//			airlineResponse.setFlights(airline.getFlights());
//			
//			====================================No Use========================
//			
//			  Airline airline2 = airline; airline2.getFlights().forEach(flight -> {
//			 airlineResponse.setFlightCapacity(flight.getFlightCapacity());
//			  airlineResponse.setFlightNumber(flight.getFlightNumber()); });
//			 
//			
//			================No Use============================================
//
//			responselist.add(airlineResponse);
//
//		});
//		return responselist;
//	}
//
//	@Override
//	public AirlineResponse getAirlinesByIdWithFlightDetails(int id) {
//		Airline airline = airlineRepository.findById(id)
//				.orElseThrow(() -> new AirlineNotFoundException("Airline", "Id", id));
//		AirlineResponse response = new AirlineResponse();
//		response.setAirlineId(airline.getAirlineId());
//		response.setAirlineName(airline.getAirlineName());
//		response.setAirlineAddress(airline.getAirlineAddress());
//		response.setAirlineContactNumber(airline.getAirlineContactNumber());
//		
//		====================================No Use========================
//		response.setFlights(airline.getFlights());
//		airline.getFlights().forEach(x -> {
//		response.setFlightCapacity(x.getFlightCapacity());
//			response.setFlightNumber(x.getFlightNumber());
//		});
//		
//		====================================No Use========================
//		return response;
//	}

}
