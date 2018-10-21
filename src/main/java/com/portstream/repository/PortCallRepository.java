package com.portstream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portstream.model.PortCall;

public interface PortCallRepository extends JpaRepository<PortCall, Integer>{

}
