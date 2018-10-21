package com.portstream.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class PortCall {

	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "port_call_id")
	private int portCallId;
	
	@Getter
	@Setter
	@Column(name = "ETA")
	private Date eta;
	
	@Getter
	@Setter
	@Column(name = "current_port_country")
	private String currentPortCountry;
	
	
	@Getter
	@Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "destination_port_id")
	private Port destinationPort;
	
	
	@Getter
	@Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "origin_port_id")
	private Port originPort;
	
	
	@Getter
	@Setter
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "previous_origin_port_id")
	private Port previousOriginPort;
	
}
