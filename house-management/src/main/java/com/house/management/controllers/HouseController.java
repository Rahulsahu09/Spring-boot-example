package com.house.management.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.house.management.model.House;
import com.house.management.services.HouseService;

/**
 * This class is Rest controller for house service. 
 * @author Rahul
 *
 */
@RestController
@RequestMapping("/api/v1/houses")
@Validated
public class HouseController {
	
	@Autowired
	private HouseService houseService;  
	
	private static final Logger log = LoggerFactory.getLogger(HouseController.class);
   
	/** 
	 * End point to handle get request of houses.
	 */
	@GetMapping()
	public List<House> getHouses() {
		return houseService.getAllHouses();
	
	}
	
	/**
	 *  End point to handle post request.
	 */
	@PostMapping ()
	public ResponseEntity<House>  addHouse( @Valid @RequestBody House house) {
		
		return ResponseEntity.ok(houseService.createHouse(house));
	}
	
	
	/** 
	 * End point to handle get request based on Id.
	 * 
	 */
	
	@GetMapping("/{id}")
    public ResponseEntity<House> getHouse(@PathVariable @NonNull Long id) {
        Optional<House> house = houseService.findById(id);
        if (!house.isPresent()) {
            log.error("Id " + id + " is not existed");
            return   ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(house.get());
    } 

	/** 
	 * End point to handle update request.
	 * 
	 */
	
    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable @NonNull Long id, @Valid @RequestBody House house) {
    	Optional<House> existingHouse = houseService.findById(id);
        if (existingHouse.isPresent()) {
        	house.setId(id); 
          }

        return ResponseEntity.ok(houseService.createHouse(house));
    }
    
    /** 
	 * End point to handle delete request.
	 * 
	 */
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteHouse(@PathVariable @NonNull Long id) {
        if (!houseService.findById(id).isPresent()) {
            log.error("Id " + id + " is not existed");
           return  ResponseEntity.noContent().build();
        }

        houseService.deleteById(id);

        return ResponseEntity.accepted().build();
    }
	
   
}
