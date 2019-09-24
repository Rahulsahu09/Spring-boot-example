package com.house.management.services;

import java.util.List;
import java.util.Optional;

import com.house.management.model.House;

public interface HouseService {

	List<House> getAllHouses();
	
	House createHouse(House house);

	Optional<House> findById(Long id);

	void deleteById(Long id);

	 
}
