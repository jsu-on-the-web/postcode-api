package com.jsu.postcodeapi.Postcode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {

    
    /**
     * Sends a query to the database to check if the postcode is present in the database
     * @param postcode - the postcode to check
     * @return Whether the postcode exists on the database or not
     */
    @Query("SELECT CASE WHEN p.postcode = ? THEN 1 ELSE 0 END FROM Postcode p")
    Boolean exists(Integer postcode);

}