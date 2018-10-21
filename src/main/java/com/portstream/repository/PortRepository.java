package com.portstream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portstream.model.Port;

public interface PortRepository extends JpaRepository<Port, Integer>{

	Port findByPortName(String name);
	
}
