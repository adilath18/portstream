package com.portstream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Port {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "port_id")
	private int portId;
	
	@Getter
	@Setter
	@Column(name = "portName", unique = true)
	private String portName;
	
	
	@Getter
	@Setter
	@Column(name = "portCountry")
	private String portCountry;


	
	public Port() {
	}




	public Port(String portName, String portCountry) {
		super();
		this.portName = portName;
		this.portCountry = portCountry;
	}
	
	
	
}
