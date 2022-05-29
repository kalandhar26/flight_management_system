package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.dto.ScheduleRequest;
import com.flightapp.dto.ScheduleResponse;
import com.flightapp.entities.Airline;
import com.flightapp.entities.Flight;
import com.flightapp.entities.Schedule;
import com.flightapp.exceptions.ScheduleNotFoundException;
import com.flightapp.repos.AirlineRepository;
import com.flightapp.repos.FlightRepository;
import com.flightapp.repos.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirlineRepository airlineRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public Schedule saveSchedule(ScheduleRequest request) {
		Flight flight = flightRepository.findByFlightNumber(request.getFlightNumber());
		Airline airline = airlineRepository.findByAirlineId(request.getAirlineId());
		
		Schedule schedule = new Schedule();
		schedule.setSourceLocation(request.getSourceLocation());
		schedule.setDestinationLocation(request.getDestinationLocation());
		schedule.setDepartureDateTime(request.getDepartureDateTime());
		schedule.setArrivalDateTime(request.getArrivalDateTime());
		schedule.setTicketPrice(request.getTicketPrice());
		schedule.setAvailableSeats(request.getAvailableSeats());
		schedule.setStatus(request.getStatus());
		schedule.setFlight(flight);
		schedule.setAirline(airline);
		
		return scheduleRepository.save(schedule);
	}

	@Override
	public ScheduleResponse getFlightAndScheduleDetailsByScheduleId(int id) {
		Schedule schedule = scheduleRepository.findByScheduleId(id);
		ScheduleResponse response = new ScheduleResponse();
		response.setScheduleId(schedule.getScheduleId());
		response.setSourceLocation(schedule.getSourceLocation());
		response.setDestinationLocation(schedule.getDestinationLocation());;
		response.setDepartureDateTime(schedule.getDepartureDateTime());
		response.setArrivalDateTime(schedule.getArrivalDateTime());
		response.setTicketPrice(schedule.getTicketPrice());
		response.setAvailableSeats(schedule.getAvailableSeats());
		response.setStatus(schedule.getStatus());
		response.setFlightNumber(schedule.getFlight().getFlightNumber());
		response.setFlightCapacity(schedule.getFlight().getFlightCapacity());
		response.setAirlineName(schedule.getAirline().getAirlineName());
		return response;
	}

	@Override
	public List<ScheduleResponse> getAllFlightsAndScheduleDetails() {
		List<Schedule> schedules = scheduleRepository.findAll();
		List<ScheduleResponse> responseList = new ArrayList<>();
		
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());;
			response.setDepartureDateTime(schedule.getDepartureDateTime());
			response.setArrivalDateTime(schedule.getArrivalDateTime());
			response.setTicketPrice(schedule.getTicketPrice());
			response.setAvailableSeats(schedule.getAvailableSeats());
			response.setStatus(schedule.getStatus());
			response.setFlightNumber(schedule.getFlight().getFlightNumber());
			response.setFlightCapacity(schedule.getFlight().getFlightCapacity());
			response.setAirlineName(schedule.getAirline().getAirlineName());
			
			responseList.add(response);			
		});
		
		return responseList;
	}

	@Override
	public Schedule updateSchedule(Schedule schedule, int id) {
	
		Schedule existingSchedule = scheduleRepository.findById(id).orElseThrow(
				()-> new ScheduleNotFoundException("Schedule", "id", id));
		existingSchedule.setSourceLocation(schedule.getSourceLocation());
		existingSchedule.setDestinationLocation(schedule.getDestinationLocation());
		existingSchedule.setDepartureDateTime(schedule.getDepartureDateTime());
		existingSchedule.setArrivalDateTime(schedule.getArrivalDateTime());
		existingSchedule.setAvailableSeats(schedule.getAvailableSeats());
		existingSchedule.setTicketPrice(schedule.getTicketPrice());
		existingSchedule.setStatus(schedule.getStatus());
		
		return scheduleRepository.save(existingSchedule);

		
		
	}

	@Override
	public void deleteScheduleById(int id) {
		
		scheduleRepository.findById(id).orElseThrow(
				()-> new ScheduleNotFoundException("Schedule", "id", id));
		
		scheduleRepository.deleteById(id);
		
	}

}
