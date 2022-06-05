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
	
	List<ScheduleResponse> searchScheduleWithDepartureDate(String departureDateTime);
	
	List<ScheduleResponse> searchScheduleWithArrivalDateTime(String arrivalDateTime);
	
	List<ScheduleResponse> searchScheduleWithSourceAndDestinationLocation(String sourceLocation, String destinationLocation);
	
	List<ScheduleResponse> searchScheduleWithDepartureAndArrivalDateTime(String departureDateTime,String arrivalDateTime);
	
	List<ScheduleResponse> searchScheduleWithSourceAndDestinationAndDepartureAndArrivalDateTime(String sourceLocation, String destinationLocation,String departureDateTime,String arrivalDateTime);

}
