package com.flightapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.FlightRequest;
import com.flightapp.dto.FlightResponse;
import com.flightapp.entities.Flight;
import com.flightapp.service.FlightService;

@RestController
@RequestMapping("/api")
public class FlightController {

	@Autowired
	private FlightService flightService;

	public FlightController(FlightService flightService) {
		super();
		this.flightService = flightService;
	}

	// Rest API to create a an flight
	// http://localhost:8080/api/flight
	@PostMapping("/flight")
	public ResponseEntity<FlightRequest> createFlight(@Valid @RequestBody FlightRequest request) {
		return new ResponseEntity<FlightRequest>(flightService.saveFlight(request), HttpStatus.CREATED);
	}

	// Rest API to get all airlines
	// http://localhost:8080/api/flight
	@GetMapping("/flight")
	public List<FlightResponse> getAllFlights() {
		return flightService.getAllFlights();
	}


	// Rest API to get flight by Id
	// http://localhost:8080/api/flight/1
	@GetMapping("/flight/{id}")
	public ResponseEntity<FlightResponse> getAirlineById(@PathVariable("id") int id) {
		return new ResponseEntity<FlightResponse>(flightService.getFlightByNumber(id), HttpStatus.OK);
	}

	// Rest API to get flight by Id
	// http://localhost:8080/api/flight/1
	@PutMapping("/flight/{id}")
	public ResponseEntity<FlightResponse> updateAirlineById(@RequestBody Flight flight, @PathVariable("id") int id) {
		return new ResponseEntity<FlightResponse>(flightService.updateFlight(flight, id), HttpStatus.OK);
	}

	// Rest API to delete flight
	// http://localhost:8080/api/flight/1
	@DeleteMapping("/flight/{id}")
	public ResponseEntity<String> deleteAirlineById(@PathVariable("id") int id) {
		flightService.deleteFlightByNumber(id);
		return new ResponseEntity<String>("Flight deleted Successfully", HttpStatus.OK);
	}
	
//	 Rest API to get all airlines
//	 http://localhost:8080/api/flight/airline/airline_name
//	@GetMapping("/flight/airline/{name}")
//	public List<FlightResponse> getAllFlightsByAirlineName(@PathVariable("name") String name) {
//		return flightService.getFlightsByAirlineName(name);
//	}

//	http://localhost:8080/api/flight/airlines/3
//	@GetMapping("/flight/flight/{id}")
//	public ResponseEntity<FlightResponse> getFlightDetailswithAirlineNameByFlightNumber(@PathVariable int id) {
//		return new ResponseEntity<FlightResponse>(flightService.getFlightByIdAlongWithAirlineName(id), HttpStatus.OK);
//
//	}
//
//	 http://localhost:8080/api/flight/airlines
//	@GetMapping("/flight/flight")
//	public List<FlightResponse> getAllFlightDetailswithAirlineNameByFlightNumber() {
//		return flightService.getAllFlightsAlongWithAirlineName();
//	}

}
