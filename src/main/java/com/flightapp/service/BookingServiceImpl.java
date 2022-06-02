package com.flightapp.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.dto.BookingRequest;
import com.flightapp.dto.BookingResponse;
import com.flightapp.entities.Airline;
import com.flightapp.entities.Booking;
import com.flightapp.entities.Flight;
import com.flightapp.entities.Passenger;
import com.flightapp.entities.Schedule;
import com.flightapp.exceptions.CancelBookingException;
import com.flightapp.exceptions.FlightNotFoundException;
import com.flightapp.exceptions.ScheduleNotFoundException;
import com.flightapp.repos.AirlineRepository;
import com.flightapp.repos.BookingRepository;
import com.flightapp.repos.FlightRepository;
import com.flightapp.repos.ScheduleRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Booking bookFlightTicket(BookingRequest request) {
		Random random = new Random();
		Flight flight = flightRepository.findById(request.getFlightNumber())
				.orElseThrow(() -> new FlightNotFoundException("Flight", "Id", request.getFlightNumber()));
		Airline airline = airlineRepository.findByAirlineName(request.getAirlineName());

		Booking booking = new Booking();
		booking.setBookingId(request.getBookingId());
		booking.setEmail(request.getEmail());
		booking.setPnrNumber(random.nextInt(10000, 100000));
		booking.setAirline(airline);
		booking.setFlight(flight);
		for (Passenger p : request.getPassengers()) {
			p.setBooking(booking);
		}
		booking.setPassengers(request.getPassengers());
		return bookingRepository.save(booking);
	}

	@Override
	public void cancelBooking(int id) {
		
		
		 Schedule schedule = scheduleRepository.findById(id) .orElseThrow(() -> new
		 ScheduleNotFoundException("Schedule", "Id", id));
		 
		//== Logic to cancel ticket prior to 24 hrs will not work==
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime departureDateTime = schedule.getDepartureDateTime();
		Duration diff = Duration.between(departureDateTime, currentDateTime);
		// long days = diff.toDays(); // Now we can use only hours
		long hours = diff.toHours();		
		if(hours>24)
		bookingRepository.deleteById(id);
		else
			throw new CancelBookingException("You Cannot cancel Ticket in Last 24 hours");
	}

	@Override
	public BookingResponse getBookingDetailswithEmail(String email) {
		Booking booking = bookingRepository.findByEmail(email);
		BookingResponse response = new BookingResponse();
		response.setBookingId(booking.getBookingId());
		response.setEmail(booking.getEmail());
		response.setPnrNumber(booking.getPnrNumber());
		response.setFlightNumber(booking.getFlight().getFlightNumber());
		response.setAirlineName(booking.getAirline().getAirlineName());
		booking.getPassengers().forEach(passenger -> {
			response.setPassengerId(passenger.getPassengerId());
			response.setPassengerName(passenger.getPassengerName());
			response.setPassengerGender(passenger.getPassengerGender());
			response.setPassengerAge(passenger.getPassengerAge());
			response.setSeatNumber(passenger.getSeatNumber());
			response.setOptforMeals(passenger.getOptforMeals());
			response.setMealType(passenger.getMealType());
		});

		return response;
	}

	@Override
	public BookingResponse getBookingDetailswithpnrNumber(long pnrNumber) {
		Booking booking = bookingRepository.findByPnrNumber(pnrNumber);
		BookingResponse response = new BookingResponse();
		response.setBookingId(booking.getBookingId());
		response.setEmail(booking.getEmail());
		response.setPnrNumber(booking.getPnrNumber());
		response.setFlightNumber(booking.getFlight().getFlightNumber());
		response.setAirlineName(booking.getAirline().getAirlineName());
		booking.getPassengers().forEach(passenger -> {
			response.setPassengerId(passenger.getPassengerId());
			response.setPassengerName(passenger.getPassengerName());
			response.setPassengerGender(passenger.getPassengerGender());
			response.setPassengerAge(passenger.getPassengerAge());
			response.setSeatNumber(passenger.getSeatNumber());
			response.setOptforMeals(passenger.getOptforMeals());
			response.setMealType(passenger.getMealType());
		});

		return response;

	}

}
