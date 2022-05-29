package com.flightapp.repos;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

	public Schedule findByScheduleId(int id);

	public Schedule findBySourceLocation(String source);

	public Schedule findByDestinationLocation(String destination);

	public Schedule findBySourceLocationAndDestinationLocation(String source, String destination);

	public Schedule findByDepartureDateTime(LocalDateTime departureDateTime);

	public Schedule findByArrivalDateTime(LocalDateTime arrivalDateTime);

	public Schedule findByDepartureDateTimeAndArrivalDateTime(LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime);

	public Schedule findBySourceLocationAndDestinationLocationAndDepartureDateTimeAndArrivalDateTime(String source,
			String destination, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);

}
