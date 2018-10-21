package com.portstream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portstream.model.LoadCondition;

public interface LoadConditionRepository extends JpaRepository<LoadCondition, Integer> {

	LoadCondition findByCondition(String condition);
}

