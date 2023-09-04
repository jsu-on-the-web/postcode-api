package com.jsu.postcodeapi.Suburb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public ResponseEntity<List<Suburb>> getAllSuburbs() {
        List<Suburb> allSuburbs = this.service.getAllSuburbs();
        return new ResponseEntity<>(allSuburbs, HttpStatus.OK);
    }

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

    /* -------------------------------------------------------------------------- */
    /* DELETE Methods */
    /* -------------------------------------------------------------------------- */
}
