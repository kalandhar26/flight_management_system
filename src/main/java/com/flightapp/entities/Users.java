package com.flightapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tbl_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String role;
	
	
	public Users (Integer id, String userName, String userEmail, String userPassword) {
		this.id=id;
		this.userName=userName;
		this.userEmail=userEmail;
		this.userPassword=userPassword;
	}
	
	

}
