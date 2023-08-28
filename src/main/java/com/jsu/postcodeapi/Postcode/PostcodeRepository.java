package com.jsu.postcodeapi.Postcode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, Long> {

    @Query("SELECT CASE WHEN p.postcode = ? THEN 1 ELSE 0 END FROM Postcode p")
    Boolean exists(Integer postcode);

}