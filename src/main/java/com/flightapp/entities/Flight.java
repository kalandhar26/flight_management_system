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
import com.flightapp.dto.FlightRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightNumber;
	private int flightCapacity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="airline_id")
	@JsonIgnore
	private Airline airline;
	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<Schedule> schedules;
	
	public Flight (int flightNumber, int flightCapacity) {
		this.flightNumber=flightNumber;
		this.flightCapacity=flightCapacity;
	}
	
	public Flight(FlightRequest request) {
		this.flightNumber = request.getFlightNumber();
		this.flightCapacity=request.getFlightCapacity();	
	}

}
