package com.flightapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightapp.dto.BookingRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	private String email;

	@JsonIgnore
	private long pnrNumber;

	@JsonIgnore
	@JoinColumn(name = "flight_number")
	@ManyToOne(fetch = FetchType.EAGER)
	private Flight flight;

	@JsonIgnore
	@JoinColumn(name = "airline_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Airline airline;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Passenger> passengers;
	
	public Booking(BookingRequest request) {
		this.bookingId =request.getBookingId();
		this.email=request.getEmail();		
	}

}
