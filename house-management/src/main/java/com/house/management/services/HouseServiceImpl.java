package com.house.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.house.management.model.House;
import com.house.management.repositories.HouseRepository;



@Service
public class HouseServiceImpl implements HouseService{
	
	@Autowired
	private HouseRepository houseRepository;
	 
	
	
	/**
	 *This method returns the list of all houses.
	 */
	@Override
	@Transactional(propagation =Propagation.REQUIRES_NEW)
	public List<House> getAllHouses(){
		
		return houseRepository.findAll();
	}

	/**
	 *This method creates or update house record.
	 */
	@Override
	@Transactional(propagation =Propagation.REQUIRES_NEW)
	public House createHouse(House house) {
		return houseRepository.save(house);
		
	}
	
	/**
	 *This method finds house based on Id.
	 */
	
     @Override
     @Transactional(propagation =Propagation.REQUIRES_NEW)
	 public Optional<House> findById(Long id) {
	        return houseRepository.findById(id);
	    }

 	/**
 	 *This method deletes house record based on Id.
 	 */
 	@Override
	@Transactional(propagation =Propagation.REQUIRES_NEW)
	public void deleteById(Long id) {
		houseRepository.deleteById(id);
	    }

	
}
