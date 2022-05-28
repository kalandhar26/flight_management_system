package com.flightapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.AirlineResponse;
import com.flightapp.entities.Airline;
import com.flightapp.service.AirlineService;

@RestController
@RequestMapping("/api")
public class AirlineFlightController {

	@Autowired
	private AirlineService airlineService;

	public AirlineFlightController(AirlineService airlineService) {
		super();
		this.airlineService = airlineService;
	}

	// Rest API to get all airlines along with Flight Details
	// http://localhost:8080/api/airline/flight
	@GetMapping("/airline/flight")
	public List<AirlineResponse> getAllAirlines() {
		List<Airline> airlines = airlineService.getAllAirlines();
		List<AirlineResponse> responselist = new ArrayList<>();

		airlines.forEach(a -> {
			AirlineResponse airlineResponse = new AirlineResponse();
			airlineResponse.setAirlineName(a.getAirlineName());
			airlineResponse.setAirlineAddress(a.getAirlineAddress());
			airlineResponse.setAirlineContactNumber(a.getAirlineContactNumber());
			Airline a2 = a;
			a2.getFlights().forEach(x -> {
				airlineResponse.setFlightCapacity(x.getFlightCapacity());
				airlineResponse.setFlightNumber(x.getFlightNumber());
			});

			responselist.add(airlineResponse);

		});
		return responselist;
	}
	
	// Rest API to get airline by Id along with Flight Details
	// http://localhost:8080/api/airline/flight/7
	@GetMapping("/airline/flight/{id}")
	public AirlineResponse getAllAirlinesById(@PathVariable int id) {
		Airline airline = airlineService.getAirlineById(id);
		AirlineResponse response = new AirlineResponse();
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
