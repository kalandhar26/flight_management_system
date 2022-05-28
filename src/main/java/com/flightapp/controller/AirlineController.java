package com.flightapp.controller;

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

import com.flightapp.entities.Airline;
import com.flightapp.service.AirlineService;

@RestController
@RequestMapping("/api")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	public AirlineController(AirlineService airlineService) {
		super();
		this.airlineService = airlineService;
	}

	// Rest API to create a an airline
	// http://localhost:8080/api/airline
	@PostMapping("/airline")
	public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
		return new ResponseEntity<Airline>(airlineService.saveAirline(airline), HttpStatus.CREATED);
	}

	
	 @GetMapping("/airline")
	 public Iterable<Airline> getAllAirlines(){
	 return airlineService.getAllAirlines();
	 }


	// Rest API to get airline by Id
	// http://localhost:8080/api/airline/1
	@GetMapping("/airline/{id}")
	public ResponseEntity<Airline> getAirlineById(@PathVariable("id") int id) {
		return new ResponseEntity<Airline>(airlineService.getAirlineById(id), HttpStatus.OK);
	}

	// Rest API to get airline by Id
	// http://localhost:8080/api/airline/1
	@PutMapping("/airline/{id}")
	public ResponseEntity<Airline> updateAirlineById(@RequestBody Airline airline, @PathVariable("id") int id) {
		return new ResponseEntity<Airline>(airlineService.updateAirline(airline, id), HttpStatus.OK);
	}

	// Rest API to delete Airline
	// http://localhost:8080/api/airline/1
	@DeleteMapping("/airline/{id}")
	public ResponseEntity<String> deleteAirlineById(@PathVariable("id") int id) {
		airlineService.deleteAirlineById(id);
		return new ResponseEntity<String>("Airline deleted Successfully", HttpStatus.OK);
	}

}
