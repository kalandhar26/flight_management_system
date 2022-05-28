package com.flightapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.entities.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

}
