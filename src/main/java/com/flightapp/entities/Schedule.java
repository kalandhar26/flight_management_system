package com.flightapp.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_schedule")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	private String sourceLocation;
	private String destinationLocation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime departureDateTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime arrivalDateTime;
	private double ticketPrice;
	private int availableSeats;
	private String status;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="flight_number")
	@JsonIgnore
	private Flight flight;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="airline_id")
	@JsonIgnore
	private Airline airline;

	public String dateFormatter(LocalDateTime dateTime) {
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myFormatObj.format(dateTime);
		return formattedDate;
	}

}
