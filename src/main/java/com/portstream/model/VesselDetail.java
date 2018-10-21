package com.portstream.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class VesselDetail {

	@Getter
	@Setter
	@Id
	@Column(name = "imo", nullable = false, unique = true)
	private Long imo;
	
	@Getter
	@Setter
	@Column(name = "vesselName", nullable = false)
	private String name;
	
	
	@Getter
	@Setter
	@Column(name = "capacity_dwt")
	private Long capacityDWT;
	
	@Getter
	@Setter
	@Column(name = "draught")
	private double draught;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "comm_market_id")
	private CommercialMarket commercialMarket;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "load_condition_id")
	private LoadCondition loadCondition;
	
	@Getter
	@Setter
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "port_call_id")
	PortCall portCall;
	
	
}
