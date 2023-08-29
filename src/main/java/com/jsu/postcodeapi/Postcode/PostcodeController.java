package com.jsu.postcodeapi.Postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PostcodeController {
    private PostcodeService service;

    @Autowired
    public PostcodeController(PostcodeService service) {
        this.service = service;
    }

    /* -------------------------------------------------------------------------- */
    /* CREATE Methods */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* READ Methods */
    /* -------------------------------------------------------------------------- */

    /**
     * Retrieves all postcodes.
     *
     * @return a ResponseEntity object containing a list of Postcode objects
     */
    @GetMapping("/postcodes")
    public ResponseEntity<List<Postcode>> getAllPostcodes() {
        List<Postcode> allPostcodes = this.service.getAllPostcodes();
        return new ResponseEntity<>(allPostcodes, HttpStatus.OK);
    }

    /**
     * Retrieves the postcode information for a given suburb.
     *
     * @param suburb the name of the suburb for which to retrieve the postcode
     * @return a ResponseEntity object containing the postcode information for the
     *         given suburb, if it exists
     */
    @GetMapping(value = "/postcodes/{suburb}")
    public ResponseEntity<Postcode> getPostcodeBySuburb(
            @RequestParam("suburb") String suburb) {
        Postcode postcodesBySuburb = this.service.getPostcodeBySuburb(suburb);
        return new ResponseEntity<>(postcodesBySuburb, HttpStatus.OK);
    }

    /* -------------------------------------------------------------------------- */
    /* UPDATE Methods */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* DELETE Methods */
    /* -------------------------------------------------------------------------- */
}