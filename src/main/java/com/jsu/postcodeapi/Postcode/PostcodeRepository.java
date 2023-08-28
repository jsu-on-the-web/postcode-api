package com.jsu.postcodeapi.Postcode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostcodeRepository extends JpaRepository<Postcode, Long> {

}