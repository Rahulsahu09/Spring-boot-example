package com.house.management.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.management.model.House;
import com.house.management.services.HouseService;

@RunWith(SpringRunner.class)
@WebMvcTest(HouseController.class)
public class HouseControllerTest {

	 @Autowired
	 private MockMvc mvc;
	
	 @MockBean
	 HouseService service;
	 
	 @Test
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
		  verify(service, times(0)).createHouse(house);      
		  
		 
	 }
	
	 
	 
	 
	 @Test
	 public void testGetAPI() throws Exception {
		 House house = new House("houseName",1L,"houseStrret","666666","houseCity1","houseState");
	 Mockito.when(service.findById(anyLong())).thenReturn(Optional.of(house));
		 
		 mvc.perform(get("/api/v1/houses/{id}",1)
			      .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
			      .andExpect(jsonPath("$.name").value("houseName")) ;
		  
	 }

	 @Test
	 public void testGetAPIForHouseNotFound() throws Exception {
		 Optional <House> house = Optional.empty() ;
		 Mockito.when(service.findById(anyLong())).thenReturn( house);
		 
		 mvc.perform(get("/api/v1/houses/{id}",1)
			      .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isNoContent());
		 
	 }

	 
	 @Test
	 public void testPostAPI() throws Exception {
		 House house = new House("houseName",1L,"houseStrret","666666","houseCity1","houseState");
		 Mockito.when(service.createHouse(house)).thenReturn(house);
		 
		  mvc.perform(post("/api/v1/houses")
			      .content(asJsonString(house))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.name").exists());
		 
	 }
	 
	 @Test
	 public void testUpdaeAPI() throws Exception {
		 House house = new House("houseName2",2L,"houseStrret","666666","houseCity2","houseState2");
		 house.setId(2L);
		 Mockito.when(service.findById(anyLong())).thenReturn(Optional.of(house));
		 Mockito.when(service.createHouse(house)).thenReturn(house);		 
		  mvc.perform(put("/api/v1/houses/{id}",2)
			      .content(asJsonString(house))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.name").value("houseName2"))
			      .andExpect(jsonPath("$.city").value("houseCity2"));
		 
	 }
	
	 @Test
	 public void deleteAPI() throws Exception
	 { 
	House house = new House("houseName",1L,"houseStrret","666666","houseCity1","houseState");
	 Mockito.when(service.findById(anyLong())).thenReturn(Optional.of(house));
		
	   mvc.perform(delete("/api/v1/houses/1") )
	         .andExpect(status().isAccepted());
	 }
	 
	 
	 
	 
	 public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
 }
}