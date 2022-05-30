package com.flightapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	public Booking findByBookingId(int id);
	
	public Booking findByEmail(String email);
	
	public Booking findByPnrNumber(long pnrNumber);

}
