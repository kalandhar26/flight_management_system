package com.flightapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="tbl_airline")
public class Airline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int airlineId;
	private String airlineName;
	private long airlineContactNumber;
	private String airlineAddress;
	
	@OneToMany(mappedBy = "airline", cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<Flight> flights;
	
	public Airline (int airlineId,String airlineName, long airlineContactNumber, String airlineAddress) {
		this.airlineId=airlineId;
		this.airlineName=airlineName;
		this.airlineContactNumber= airlineContactNumber;
		this.airlineAddress=airlineAddress;
	}
	

}
