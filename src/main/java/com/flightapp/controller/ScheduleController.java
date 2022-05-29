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
	public ResponseEntity<Schedule> saveSchedule(@Valid @RequestBody ScheduleRequest request) {
		return new ResponseEntity<Schedule>(scheduleService.saveSchedule(request), HttpStatus.CREATED);
	}

	// API to view All Schedules
	// http://localhost:8080/api/schedule
	@GetMapping("/schedule")
	public List<ScheduleResponse> getAllScheduledFlightDetails() {
		return scheduleService.getAllFlightsAndScheduleDetails();
	}

	// API to view a Schedule by Id
	// http://localhost:8080/api/schedule/3
	@GetMapping("/schedule/{id}")
	public ResponseEntity<ScheduleResponse> getScheduledFlightDetailsByScheduleId(@PathVariable int id) {
		return new ResponseEntity<ScheduleResponse>(scheduleService.getFlightAndScheduleDetailsByScheduleId(id),
				HttpStatus.OK);
	}

	// Rest API to get airline by Id
	// http://localhost:8080/api/schedule/1
	@PutMapping("/schedule/{id}")
	public ResponseEntity<Schedule> updateScheduleById(@RequestBody Schedule schedule, @PathVariable("id") int id) {
		return new ResponseEntity<Schedule>(scheduleService.updateSchedule(schedule, id), HttpStatus.OK);
	}

	// Rest API to delete flight
	// http://localhost:8080/api/schedule/1
	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<String> deleteScheduleById(@PathVariable("id") int id) {
		scheduleService.deleteScheduleById(id);
		return new ResponseEntity<String>("Schedule deleted Successfully", HttpStatus.OK);
	}

}
