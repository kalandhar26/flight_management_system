package com.flightapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.flightapp.dto.ScheduleRequest;
import com.flightapp.dto.ScheduleResponse;
import com.flightapp.entities.Schedule;
import com.flightapp.service.ScheduleService;

@RestController
@RequestMapping("/api")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	// API to add a Schedule
	// http://localhost:8080/api/schedule
	@PostMapping("/schedule")
	public ResponseEntity<ScheduleRequest> saveSchedule(@Valid @RequestBody ScheduleRequest request) {
		return new ResponseEntity<ScheduleRequest>(scheduleService.saveSchedule(request), HttpStatus.CREATED);
	}

	// API to view All Schedules
	// http://localhost:8080/api/schedule
	@GetMapping("/schedule")
	public List<ScheduleResponse> getAllScheduledFlightDetails() {
		return scheduleService.getAllSchedules();
	}

	// API to view a Schedule by Id
	// http://localhost:8080/api/schedule/3
	@GetMapping("/schedule/{id}")
	public ResponseEntity<ScheduleResponse> getScheduledFlightDetailsByScheduleId(@PathVariable int id) {
		return new ResponseEntity<ScheduleResponse>(scheduleService.getScheduleById(id), HttpStatus.OK);
	}

	// Rest API to get airline by Id
	// http://localhost:8080/api/schedule/1
	@PutMapping("/schedule/{id}")
	public ResponseEntity<ScheduleResponse> updateScheduleById(@RequestBody Schedule schedule,
			@PathVariable("id") int id) {
		return new ResponseEntity<ScheduleResponse>(scheduleService.updateSchedule(schedule, id), HttpStatus.OK);
	}

	// Rest API to delete flight
	// http://localhost:8080/api/schedule/1
	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<String> deleteScheduleById(@PathVariable("id") int id) {
		scheduleService.deleteScheduleById(id);
		return new ResponseEntity<String>("Schedule deleted Successfully", HttpStatus.OK);
	}

	// http://localhost:8080/api/schedule/source/chennai
	@GetMapping("/schedule/source/{sourceLocation}")
	public List<ScheduleResponse> viewSchedulesWithSourceLocation(
			@PathVariable(name = "sourceLocation") String sourceLocation) {
		return scheduleService.searchSchedulesWithSourceLocation(sourceLocation);
	}

	// http://localhost:8080/api/schedule/destination/Mumbai
	@GetMapping("/schedule/destination/{destinationLocation}")
	public List<ScheduleResponse> viewSchedulesWithDestinationLocation(
			@PathVariable(name = "destinationLocation") String destinationLocation) {
		return scheduleService.searchSchedulesWithDestinationLocation(destinationLocation);
	}

	// http://localhost:8080/api/schedule/departure/2022-01-01 08:00:00.000000
	@GetMapping("/schedule/departure/{departureDateTime}")
	public List<ScheduleResponse> viewScheduleWithDepartureDate(
			@PathVariable(name = "departureDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss.SSSSSS") LocalDateTime departureDateTime) {
		return scheduleService.searchScheduleWithDepartureDate(departureDateTime);
	}

	// http://localhost:8080/api/schedule/arrival/2022-01-02 08:00:00.000000
	@GetMapping("/schedule/arrival/{arrivalDateTime}")
	public List<ScheduleResponse> searchScheduleWithArrivalDateTime(
			@PathVariable(name = "arrivalDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss.SSSSSS") LocalDateTime arrivalDateTime) {
		return scheduleService.searchScheduleWithArrivalDateTime(arrivalDateTime);
	}

	// http://localhost:8080/api/schedule/source/Delhi/destination/Mumbai
	@GetMapping("/schedule/source/{sourceLocation}/destination/{destinationLocation}")
	public List<ScheduleResponse> searchScheduleWithSourceAndDestinationLocation(
			@PathVariable(name = "sourceLocation") String sourceLocation,
			@PathVariable(name = "destinationLocation") String destinationLocation) {
		return scheduleService.searchScheduleWithSourceAndDestinationLocation(sourceLocation, destinationLocation);
	}

	// http://localhost:8080/api/schedule/departure/2022-01-01 08:00:00.000000/arrival/2022-01-01 08:00:00.000000
	@GetMapping("/schedule/departure/{departureDateTime}/arrival/{arrivalDateTime}")
	public List<ScheduleResponse> searchScheduleWithDepartureAndArrivalDateTime(
			@PathVariable(name = "departureDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss.SSSSSS") LocalDateTime departureDateTime,
			@PathVariable(name = "arrivalDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss.SSSSSS") LocalDateTime arrivalDateTime) {
		return scheduleService.searchScheduleWithDepartureAndArrivalDateTime(departureDateTime, arrivalDateTime);
	}

	// http://localhost:8080/api/schedule/source/Mumbai/destination/Delhi/departure/2022-01-01 08:00:00.000000/arrival/2022-01-01 08:00:00.000000
	@GetMapping("/schedule/source/{sourceLocation}/destination/{destinationLocation}/departure/{departureDateTime}/arrival/{arrivalDateTime}")
	public List<ScheduleResponse> searchScheduleWithSourceAndDestinationAndDepartureAndArrivalDateTime(
			@PathVariable(name = "sourceLocation") String sourceLocation,
			@PathVariable(name = "destinationLocation") String destinationLocation,
			@PathVariable(name = "departureDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss.SSSSSS") LocalDateTime departureDateTime,
			@PathVariable(name = "arrivalDateTime") @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss.SSSSSS") LocalDateTime arrivalDateTime) {
		return scheduleService.searchScheduleWithSourceAndDestinationAndDepartureAndArrivalDateTime(sourceLocation,
				destinationLocation, departureDateTime, arrivalDateTime);
	}
	
	// http://localhost:8080/api/schedule/flight_number/1234
	@GetMapping("/schedule/flight_number/{flightNumber}")
	public ResponseEntity<ScheduleResponse> searchScheduleByFlightNumber(@PathVariable(name="flightNumber") int flightNumber){
		return new ResponseEntity<ScheduleResponse>(scheduleService.searchScheduleWithFlightNumber(flightNumber),HttpStatus.OK);
	}
	}
