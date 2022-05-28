package com.flightapp.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.flightapp.dto.FlightRequest;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name="tbl_flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightNumber;
	private int flightCapacity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="airline_id")
	private Airline airline;
	
	public Flight (int flightNumber, int flightCapacity) {
		this.flightNumber=flightNumber;
		this.flightCapacity=flightCapacity;
	}
	
	public Flight(FlightRequest request) {
		this.flightNumber = request.getFlightNumber();
		this.flightCapacity=request.getFlightCapacity();	
	}

}
