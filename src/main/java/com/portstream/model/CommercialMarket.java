package com.portstream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class CommercialMarket {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comm_market_id")
	private int commercialMarketId;
	
	@Getter
	@Setter
	@Column(name = "market_type")
	private String marketType;
	
	@Getter
	@Setter
	@Column(name = "size_class")
	private String sizeClass;
	
	

	public CommercialMarket() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CommercialMarket(String marketType, String sizeClass) {
		super();
		this.marketType = marketType;
		this.sizeClass = sizeClass;
	}
	
}
