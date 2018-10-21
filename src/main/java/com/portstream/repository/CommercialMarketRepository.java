package com.portstream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portstream.model.CommercialMarket;

public interface CommercialMarketRepository extends JpaRepository<CommercialMarket, Integer>{

	 CommercialMarket findByMarketTypeAndSizeClass(String marketType, String sizeClass);
	
}
