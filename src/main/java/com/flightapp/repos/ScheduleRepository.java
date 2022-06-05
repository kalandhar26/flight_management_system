package com.flightapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	public Schedule findByScheduleId(int id);

	public List<Schedule> findBySourceLocation(String source);

	public List<Schedule> findByDestinationLocation(String destination);

	public List<Schedule> findBySourceLocationAndDestinationLocation(String source, String destination);

	public List<Schedule> findByDepartureDateTime(String departureDateTime);

	public List<Schedule> findByArrivalDateTime(String arrivalDateTime);

	public List<Schedule> findByDepartureDateTimeAndArrivalDateTime(String departureDateTime,
			String arrivalDateTime);

	public List<Schedule> findBySourceLocationAndDestinationLocationAndDepartureDateTimeAndArrivalDateTime(String source,
			String destination, String departureDateTime, String arrivalDateTime);
	
	public Schedule findByFlightFlightNumber(int flightNumber);

}
