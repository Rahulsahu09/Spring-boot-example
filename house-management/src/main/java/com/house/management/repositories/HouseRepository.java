package com.house.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.house.management.model.House;

/**
 * This class is a house repository which performs data base operation.
 * @author Rahul
 *
 */
@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

}
