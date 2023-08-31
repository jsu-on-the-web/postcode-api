package com.jsu.postcodeapi.Postcode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {

    
    /**
     * Sends a query to the database to check if the postcode is present in the
     * database
     * 
     * @param postcode - the postcode to check
     * @return Whether the postcode exists in the database or not
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Postcode p WHERE p.postcode = ?1")
    Boolean exists(int postcode);

    /**
     * Finds a Postcode by its suburb
     * 
     * @param suburb - the suburb to search for
     * @return The Postcode object if found, otherwise empty
     */
    @Query("SELECT p FROM Postcode p WHERE p.suburbs = ?1")
    Optional<Postcode> findBySuburb(String suburb);

}