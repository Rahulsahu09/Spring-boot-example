package com.house.management.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.house.management.model.House;


@RunWith(SpringRunner.class)
@DataJpaTest
public class HouseRepositoryTest {

	@Autowired
	private HouseRepository houseRepository;
	
	 @Test
	 @DirtiesContext
	  public void testHouseEmpty() {
	    List<House> houses = houseRepository.findAll();
	      assertTrue(houses.isEmpty());
	  }
	
	 
	 @Test
	 @DirtiesContext
	  public void testCreateHouse() {
		 House house = new House();
		 house.setId(1L);
		 house.setCity("houseCity1");
		 house.setName("houseName");
		 house.setNumber(1L);
		 house.setPostalCode("666666");
		 house.setState("houseState");
		 house.setStreet("houseStrret");
		
	    House house1=houseRepository.save(house);
	    assertTrue(house.equals(house1));
	  }
	
	 @Test
	 @DirtiesContext
	  public void testFindById_when_House_Present() {
		 createHouseEntry() ;
		
		 assertTrue( houseRepository.findById(1L).isPresent());
	  }
	
	 @Test
	 @DirtiesContext
	  public void testHouseNotPresent() {
	    Optional<House> house = houseRepository.findById(1L);
	      assertFalse(house.isPresent());
	  }
	
	 
	 @Test
	 @DirtiesContext
	  public void testFindById_when_house_Not_Present() {
		 createHouseEntry() ;
		
		 assertFalse( houseRepository.findById(2L).isPresent());
	  }
	
	 @Test
	 @DirtiesContext
	  public void testDeleteById() {
		 createHouseEntry();
		 
		 houseRepository.deleteById(1L);
		
		 assertFalse( houseRepository.findById(2L).isPresent() );
	  }
	
	 
	
	 private void createHouseEntry() {
	 House house = new House();
		 house.setId(1L);
		 house.setCity("houseCity1");
		 house.setName("houseName");
		 house.setNumber(1L);
		 house.setPostalCode("666666");
		 house.setState("houseState");
		 house.setStreet("houseStrret");
		 houseRepository.save(house);
		 
	 }
	 		
}
