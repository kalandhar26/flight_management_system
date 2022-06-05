package com.flightapp.dto;

public class AirlineRequest {
	
	private int airlineId;
	private String airlineName;
	private long airlineContactNumber;
	private String airlineAddress;
	public int getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public long getAirlineContactNumber() {
		return airlineContactNumber;
	}
	public void setAirlineContactNumber(long airlineContactNumber) {
		this.airlineContactNumber = airlineContactNumber;
	}
	public String getAirlineAddress() {
		return airlineAddress;
	}
	public void setAirlineAddress(String airlineAddress) {
		this.airlineAddress = airlineAddress;
	}
	public AirlineRequest(int airlineId, String airlineName, long airlineContactNumber, String airlineAddress) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
		this.airlineContactNumber = airlineContactNumber;
		this.airlineAddress = airlineAddress;
	}
	public AirlineRequest() {
		
	}
	
	
	
	

}
