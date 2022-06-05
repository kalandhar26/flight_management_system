package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.flightapp.dto.ScheduleRequest;
import com.flightapp.dto.ScheduleResponse;
import com.flightapp.entities.Flight;
import com.flightapp.entities.Schedule;
import com.flightapp.exceptions.FlightNotFoundException;
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
	public ScheduleRequest saveSchedule(ScheduleRequest request) {
		Flight flight = flightRepository.findByFlightNumber(request.getFlightNumber());
		// Airline airline = airlineRepository.findByAirlineId(request.getAirlineId());
		// Airline airline =
		// airlineRepository.findByAirlineName(request.getAirlineName());
		if (null != flight || !ObjectUtils.isEmpty(flight)) {

			Schedule schedule = new Schedule();
			schedule.setSourceLocation(request.getSourceLocation());
			schedule.setDestinationLocation(request.getDestinationLocation());
			schedule.setDepartureDateTime(request.getDepartureDateTime());
			schedule.setArrivalDateTime(request.getArrivalDateTime());
			schedule.setTicketPrice(request.getTicketPrice());
			schedule.setAvailableSeats(request.getAvailableSeats());
			schedule.setStatus(request.getStatus());
			schedule.setFlight(flight);
//		schedule.setAirline(airline);
			schedule.setAirline(flight.getAirline());
			scheduleRepository.save(schedule);

			request.setScheduleId(schedule.getScheduleId());
			request.setSourceLocation(schedule.getSourceLocation());
			request.setDestinationLocation(schedule.getDestinationLocation());
			request.setDepartureDateTime(schedule.getDepartureDateTime());
			request.setArrivalDateTime(schedule.getArrivalDateTime());
			request.setTicketPrice(schedule.getTicketPrice());
			request.setAvailableSeats(schedule.getAvailableSeats());
			request.setStatus(schedule.getStatus());
			request.setFlightNumber(flight.getFlightNumber());
//		request.setAirlineId(airline.getAirlineId());
//		request.setAirlineName(airline.getAirlineName());
			request.setAirlineName(flight.getAirline().getAirlineName());

			return request;
		} else {
			throw new FlightNotFoundException("Flight", "number", request.getFlightNumber());
		}

	}

	@Override
	public ScheduleResponse getScheduleById(int id) {
		Schedule schedule = scheduleRepository.findById(id)
				.orElseThrow(() -> new ScheduleNotFoundException("Schedule", "id", id));
		ScheduleResponse response = new ScheduleResponse();
		response.setScheduleId(schedule.getScheduleId());
		response.setSourceLocation(schedule.getSourceLocation());
		response.setDestinationLocation(schedule.getDestinationLocation());
		;
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
	public List<ScheduleResponse> getAllSchedules() {
		List<Schedule> schedules = scheduleRepository.findAll();
		List<ScheduleResponse> responseList = new ArrayList<>();

		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
			;
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
	public ScheduleResponse updateSchedule(Schedule schedule, int id) {

		Schedule existingSchedule = scheduleRepository.findById(id)
				.orElseThrow(() -> new ScheduleNotFoundException("Schedule", "id", id));
		existingSchedule.setSourceLocation(schedule.getSourceLocation());
		existingSchedule.setDestinationLocation(schedule.getDestinationLocation());
		existingSchedule.setDepartureDateTime(schedule.getDepartureDateTime());
		existingSchedule.setArrivalDateTime(schedule.getArrivalDateTime());
		existingSchedule.setAvailableSeats(schedule.getAvailableSeats());
		existingSchedule.setTicketPrice(schedule.getTicketPrice());
		existingSchedule.setStatus(schedule.getStatus());
		scheduleRepository.save(existingSchedule);

		ScheduleResponse response = new ScheduleResponse();
		response.setScheduleId(existingSchedule.getScheduleId());
		response.setSourceLocation(existingSchedule.getSourceLocation());
		response.setDestinationLocation(existingSchedule.getDestinationLocation());
		response.setDepartureDateTime(existingSchedule.getDepartureDateTime());
		response.setArrivalDateTime(existingSchedule.getArrivalDateTime());
		response.setTicketPrice(existingSchedule.getTicketPrice());
		response.setAvailableSeats(existingSchedule.getAvailableSeats());
		response.setStatus(existingSchedule.getStatus());
		response.setFlightNumber(existingSchedule.getFlight().getFlightNumber());
		response.setFlightCapacity(existingSchedule.getFlight().getFlightCapacity());
		response.setAirlineName(existingSchedule.getAirline().getAirlineName());

		return response;

	}

	@Override
	public void deleteScheduleById(int id) {

		scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException("Schedule", "id", id));

		scheduleRepository.deleteById(id);

	}

	public List<ScheduleResponse> searchSchedulesWithSourceLocation(String sourceLocation) {
		List<Schedule> schedules = scheduleRepository.findBySourceLocation(sourceLocation);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public List<ScheduleResponse> searchSchedulesWithDestinationLocation(String destinationLocation) {
		List<Schedule> schedules = scheduleRepository.findByDestinationLocation(destinationLocation);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public List<ScheduleResponse> searchScheduleWithDepartureDate(String departureDateTime) {
		List<Schedule> schedules = scheduleRepository.findByDepartureDateTime(departureDateTime);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public List<ScheduleResponse> searchScheduleWithArrivalDateTime(String arrivalDateTime) {
		List<Schedule> schedules = scheduleRepository.findByArrivalDateTime(arrivalDateTime);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public List<ScheduleResponse> searchScheduleWithSourceAndDestinationLocation(String sourceLocation,
			String destinationLocation) {
		List<Schedule> schedules = scheduleRepository.findBySourceLocationAndDestinationLocation(sourceLocation,
				destinationLocation);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public List<ScheduleResponse> searchScheduleWithDepartureAndArrivalDateTime(String departureDateTime,
			String arrivalDateTime) {
		List<Schedule> schedules = scheduleRepository.findByDepartureDateTimeAndArrivalDateTime(departureDateTime,
				arrivalDateTime);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public List<ScheduleResponse> searchScheduleWithSourceAndDestinationAndDepartureAndArrivalDateTime(
			String sourceLocation, String destinationLocation, String departureDateTime,
			String arrivalDateTime) {
		List<Schedule> schedules = scheduleRepository
				.findBySourceLocationAndDestinationLocationAndDepartureDateTimeAndArrivalDateTime(sourceLocation,
						destinationLocation, departureDateTime, arrivalDateTime);
		List<ScheduleResponse> responseList = new ArrayList<ScheduleResponse>();
		schedules.forEach(schedule -> {
			ScheduleResponse response = new ScheduleResponse();
			response.setScheduleId(schedule.getScheduleId());
			response.setSourceLocation(schedule.getSourceLocation());
			response.setDestinationLocation(schedule.getDestinationLocation());
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
	public ScheduleResponse searchScheduleWithFlightNumber(int flightNumber) {
		Schedule schedule = scheduleRepository.findByFlightFlightNumber(flightNumber);
		ScheduleResponse response = new ScheduleResponse();
		response.setScheduleId(schedule.getScheduleId());
		response.setSourceLocation(schedule.getSourceLocation());
		response.setDestinationLocation(schedule.getDestinationLocation());
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

}
