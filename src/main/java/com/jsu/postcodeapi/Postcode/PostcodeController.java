package com.jsu.postcodeapi.Postcode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsu.postcodeapi.exceptions.BadRequestException;
import com.jsu.postcodeapi.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/postcodes")
public class PostcodeController {
    private PostcodeService service;

    @Autowired
    public PostcodeController(PostcodeService service) {
        this.service = service;
    }

    /* -------------------------------------------------------------------------- */
    /* CREATE Methods */
    /* -------------------------------------------------------------------------- */

    @PostMapping()
    public ResponseEntity<Postcode> addPostcode(
            @Valid @RequestBody CreatePostcodeDTO createPostcodeDTO)
    {
        Postcode postcode = this.service.addPostcode(createPostcodeDTO);
        return new ResponseEntity<>(postcode, HttpStatus.CREATED);
    }
    

    /* -------------------------------------------------------------------------- */
    /* READ Methods */
    /* -------------------------------------------------------------------------- */

    /**
     * Retrieves all postcodes.
     *
     * @return a ResponseEntity object containing a list of Postcode objects
     */
    @GetMapping()
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
    @GetMapping(path = "{suburb}")
    public ResponseEntity<Postcode> getPostcodeBySuburb(
            @RequestParam("suburb") String suburb) {
        Optional<Postcode> postcodeBySuburb = this.service.getPostcodeBySuburb(suburb);
        if (postcodeBySuburb.isEmpty()) {
            throw new NotFoundException("Postcode that's associated with " + suburb + " does not exist");
        }
        return new ResponseEntity<>(postcodeBySuburb.get(), HttpStatus.OK);
    }

    /* -------------------------------------------------------------------------- */
    /* UPDATE Methods */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* DELETE Methods */
    /* -------------------------------------------------------------------------- */
}