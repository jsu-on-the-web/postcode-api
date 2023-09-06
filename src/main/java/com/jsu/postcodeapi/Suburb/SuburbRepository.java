package com.jsu.postcodeapi.Suburb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SuburbRepository extends JpaRepository<Suburb, Long> {

	/**
	 * Checks if a suburb with the given name and postcode exists.
	 *
	 * @param  suburbName  the name of the suburb
	 * @param  postcode    the postcode of the suburb
	 * @return             true if a suburb with the given name and postcode exists, false otherwise
	 */
	@Query("SELECT COUNT(s) > 0 FROM Suburb s WHERE s.suburbName LIKE %?1% AND s.postcode.postcode = ?2")
	Boolean existsByNameAndPostcode(String suburbName, Integer postcode);
    
}