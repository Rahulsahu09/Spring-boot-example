package com.house.management.Integration;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.management.model.House;
import com.house.management.repositories.HouseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class houseServiceIntegrationTest {

	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	 private MockMvc mvc;
	
	
	@Test
	@DirtiesContext
	 public void testValidationAPI() throws Exception {
		 House house = new House("",null,null,"666","",null);
		  mvc.perform(post("/api/v1/houses")
			      .content(asJsonString(house))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$.status", is(400)))
	                .andExpect(jsonPath("$.errors").isArray())
	                .andExpect(jsonPath("$.errors", hasSize(6)))
	                .andExpect(jsonPath("$.errors", hasItem("Please provide street")))
	                .andExpect(jsonPath("$.errors", hasItem("please provide city")))
	                .andExpect(jsonPath("$.errors", hasItem("Please provide house number")))
	                .andExpect(jsonPath("$.errors", hasItem("please provide state")))
	                .andExpect(jsonPath("$.errors", hasItem("Please provide name")))
	                .andExpect(jsonPath("$.errors", hasItem("Please provide minimum six digit postal code")));
		  assertTrue( houseRepository.findAll().isEmpty());
	}
		  
	
	
	@Test
	@DirtiesContext
	 public void testPostAPI() throws Exception { 
		 House house = new House("houseName",1L,"houseStrret","6666666","houseCity1","houseState");
		  mvc.perform(post("/api/v1/houses")
			      .content(asJsonString(house))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.id").exists());
		  assertTrue( houseRepository.findById(1L).isPresent());
	 }
	
	 @Test
	 @DirtiesContext
	 public void deleteAPI() throws Exception
	 { 
	House house = new House("houseName",1L,"houseStrret","666666","houseCity1","houseState");
	 houseRepository.save(house);
		
	   mvc.perform(delete("/api/v1/houses/1") )
	         .andExpect(status().isAccepted());
	   assertFalse( houseRepository.findById(1L).isPresent());
	 }

	
	
	 @Test
	 @DirtiesContext
	 public void testGetAPI() throws Exception {
		 House house = new House("houseName",1L,"houseStrret","666666","houseCity1","houseState");
		 houseRepository.save(house);
		 
		 mvc.perform(get("/api/v1/houses/{id}",1)
			      .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
			      .andExpect(jsonPath("$.name").value("houseName")) ;
		  
	 }

	 @Test
	 @DirtiesContext
	 public void testGetAPIForHouseNotFound() throws Exception {
		  mvc.perform(get("/api/v1/houses/{id}",1)
			      .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isNoContent());
		 
	 }
	 


	 public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
}
	
	
}
