package com.flightapp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.flightapp.dto.ScheduleRequest;
import com.flightapp.dto.ScheduleResponse;
import com.flightapp.entities.Schedule;

public interface ScheduleService {
	
	ScheduleRequest saveSchedule(ScheduleRequest request);
	
	ScheduleResponse getScheduleById(int id);
	
	List<ScheduleResponse> getAllSchedules();
	
	ScheduleResponse updateSchedule(Schedule schedule, int id);
	
	void deleteScheduleById(int id);
	
	ScheduleResponse searchScheduleWithFlightNumber(int flightNumber);
	
	List<ScheduleResponse> searchSchedulesWithSourceLocation(String sourceLocation);
	
	List<ScheduleResponse> searchSchedulesWithDestinationLocation(String destinationLocation);
	
	List<ScheduleResponse> searchScheduleWithDepartureDate(LocalDateTime departureDateTime);
	
	List<ScheduleResponse> searchScheduleWithArrivalDateTime(LocalDateTime arrivalDateTime);
	
	List<ScheduleResponse> searchScheduleWithSourceAndDestinationLocation(String sourceLocation, String destinationLocation);
	
	List<ScheduleResponse> searchScheduleWithDepartureAndArrivalDateTime(LocalDateTime departureDateTime,LocalDateTime arrivalDateTime);
	
	List<ScheduleResponse> searchScheduleWithSourceAndDestinationAndDepartureAndArrivalDateTime(String sourceLocation, String destinationLocation,LocalDateTime departureDateTime,LocalDateTime arrivalDateTime);

}
