package com.jsu.postcodeapi.Suburb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsu.postcodeapi.Suburb.CreateSuburbDTO;
import com.jsu.postcodeapi.Suburb.Suburb;
import com.jsu.postcodeapi.Suburb.SuburbService;
import com.jsu.postcodeapi.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/suburbs")
public class SuburbController {
        private SuburbService service;

    @Autowired
    public SuburbController(SuburbService service) {
        this.service = service;
    }

    /* -------------------------------------------------------------------------- */
    /* CREATE Methods */
    /* -------------------------------------------------------------------------- */

    /**
     * Adds a new suburb to the system.
     *
     * @param  createSuburbDTO  the DTO object containing the details of the new suburb
     * @return                  the ResponseEntity object containing the newly created suburb and the HTTP status code
     */
    @PostMapping()
    public ResponseEntity<Suburb> addSuburb(
            @Valid @RequestBody CreateSuburbDTO createSuburbDTO
    ) {
        Suburb suburb = this.service.addSuburb(createSuburbDTO);
        return new ResponseEntity<>(suburb, HttpStatus.CREATED);
    }
    /* -------------------------------------------------------------------------- */
    /* READ Methods */
    /* -------------------------------------------------------------------------- */

    /**
     * Retrieves all suburbs.
     *
     * @return  a ResponseEntity containing a list of Suburb objects
     */
    @GetMapping()
    public ResponseEntity<List<Suburb>> getAllSuburbs() {
        List<Suburb> allSuburbs = this.service.getAllSuburbs();
        return new ResponseEntity<>(allSuburbs, HttpStatus.OK);
    }

    /**
     * Retrieves a suburb by its ID.
     *
     * @param  id  the ID of the suburb to retrieve
     * @return     a ResponseEntity containing the suburb and HTTP status code
     */
    @GetMapping("/{id}")
    public ResponseEntity<Suburb> getSuburbById(
            @PathVariable Long id
    ) {
        Suburb suburb = this.service.getSuburbById(id);
        return new ResponseEntity<>(suburb, HttpStatus.OK);
    }

    /* -------------------------------------------------------------------------- */
    /* UPDATE Methods */
    /* -------------------------------------------------------------------------- */

    @PatchMapping("/{id}")
    public ResponseEntity<Suburb> updateSuburb(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSuburbDTO updates
    ) {
        Optional<Suburb> suburb = this.service.updateSuburb(id, updates);
        if (suburb.isEmpty()) {
            throw new NotFoundException("Suburb with id " + id + "not found, unable to update.");
        }
        return new ResponseEntity<>(suburb.get(), HttpStatus.OK);
    }

    /* -------------------------------------------------------------------------- */
    /* DELETE Methods */
    /* -------------------------------------------------------------------------- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Suburb> deleteSuburb(
            @PathVariable Long id
    ) {
        boolean deletedSuburb = this.service.deleteById(id);
        if (!deletedSuburb) {
            throw new NotFoundException("Suburb with id " + id + "not found, unable to delete.");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
