package com.portstream.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class LoadCondition {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "load_condition_id")
	private int conditionId;
	
	@Getter
	@Setter
	@Column(name = "load_condition", unique = true)
	private String condition;
	
	

	public LoadCondition() {
		super();
		// TODO Auto-generated constructor stub
	}



	public LoadCondition(String condition) {
		super();
		this.condition = condition;
	}
	
	
}
