package com.flightapp.service;

import java.util.List;

import com.flightapp.dto.ScheduleRequest;
import com.flightapp.dto.ScheduleResponse;
import com.flightapp.entities.Schedule;

public interface ScheduleService {
	
	Schedule saveSchedule(ScheduleRequest request);
	
	ScheduleResponse getFlightAndScheduleDetailsByScheduleId(int id);
	
	List<ScheduleResponse> getAllFlightsAndScheduleDetails();
	
	Schedule updateSchedule(Schedule schedule, int id);
	
	void deleteScheduleById(int id);

}
