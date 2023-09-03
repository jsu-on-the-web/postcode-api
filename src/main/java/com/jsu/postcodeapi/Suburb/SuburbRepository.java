package com.jsu.postcodeapi.Suburb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SuburbRepository extends JpaRepository<Suburb, Long> {

	@Query("SELECT COUNT(s) > 0 FROM Suburb s WHERE s.suburb_name = ?1 AND s.postcode.postcode = ?2")
	Boolean existsByNameAndPostcode(String suburbName, Integer postcode);
    
}