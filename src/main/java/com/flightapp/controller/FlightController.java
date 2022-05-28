package com.flightapp.controller;

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
import com.flightapp.entities.Airline;
import com.flightapp.entities.Flight;
import com.flightapp.service.AirlineService;
import com.flightapp.service.FlightService;

@RestController
@RequestMapping("/api")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private AirlineService airlineService;

	public FlightController(FlightService flightService, AirlineService airlineService) {
		super();
		this.flightService = flightService;
		this.airlineService = airlineService;
	}

	// Rest API to create a an flight
	// http://localhost:8080/api/flight
	@PostMapping("/flight")
	public ResponseEntity<Flight> createFlight(@Valid @RequestBody FlightRequest request) {
		Airline airline = airlineService.getAirlineById(request.getAirlineId());
		Flight flight = new Flight();
		flight.setFlightCapacity(request.getFlightCapacity());
		flight.setAirline(airline);

		return new ResponseEntity<Flight>(flightService.saveFlight(flight), HttpStatus.CREATED);
	}

	// =======================================================
	// Rest API to create a an flight
	// http://localhost:8080/api/flight
//	@PostMapping("/flight")
//	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
//		System.out.println(flight.toString());
//		return new ResponseEntity<Flight>(flightService.saveFlight(flight), HttpStatus.CREATED);
//	}

	// ===================================================
	// Rest API to get all airlines
	// http://localhost:8080/api/flight
	@GetMapping("/flight")
	public Iterable<Flight> getAllFlights() {
		return flightService.getAllFlights();
	}

	// Rest API to get all airlines
	// http://localhost:8080/api/flight/airline/airline_name
	@GetMapping("/flight/airline/{name}")
	public Iterable<Flight> getAllFlightsByAirlineName(@PathVariable("name") String name) {
		return flightService.getFlightsByAirlineName(name);
	}

	// Rest API to get flight by Id
	// http://localhost:8080/api/flight/1
	@GetMapping("/flight/{id}")
	public ResponseEntity<Flight> getAirlineById(@PathVariable("id") int id) {
		return new ResponseEntity<Flight>(flightService.getFlightByNumber(id), HttpStatus.OK);
	}

	// Rest API to get flight by Id
	// http://localhost:8080/api/flight/1
	@PutMapping("/flight/{id}")
	public ResponseEntity<Flight> updateAirlineById(@RequestBody Flight flight, @PathVariable("id") int id) {
		return new ResponseEntity<Flight>(flightService.updateFlight(flight, id), HttpStatus.OK);
	}

	// Rest API to delete flight
	// http://localhost:8080/api/flight/1
	@DeleteMapping("/flight/{id}")
	public ResponseEntity<String> deleteAirlineById(@PathVariable("id") int id) {
		flightService.deleteFlightByNumber(id);
		return new ResponseEntity<String>("Flight deleted Successfully", HttpStatus.OK);
	}

}
