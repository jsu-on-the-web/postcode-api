package com.jsu.postcodeapi.Suburb;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsu.postcodeapi.Suburb.Suburb;
import com.jsu.postcodeapi.Suburb.SuburbRepository;
import com.jsu.postcodeapi.exceptions.BadRequestException;
import com.jsu.postcodeapi.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class SuburbService {

        private SuburbRepository suburbRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public SuburbService(SuburbRepository postcodeRepository) {
        this.suburbRepository = postcodeRepository;
    }

    /**
     * Retrieves all suburbs from the repository.
     *
     * @return         	a list of suburb objects
     */
    public List<Suburb> getAllSuburbs() {
        return suburbRepository.findAll();
    }

    /**
     * Adds a new suburb to the database.
     *
     * @param  createSuburbDTO  the DTO object containing the details of the suburb to be created
     * @return                  the newly created suburb
     */
    public Suburb addSuburb(@Valid CreateSuburbDTO createSuburbDTO) {
        // Check if the suburb already exists on the database
        // ! Right now, we're calling getPostcode() (suburb version) on 
        Boolean existsSuburb = suburbRepository.existsByNameAndPostcode(createSuburbDTO.getSuburbName(),
                createSuburbDTO.getPostcode().getPostcode());
        if (existsSuburb) {
            throw new BadRequestException("Suburb " + createSuburbDTO.getSuburbName() + " already exists");
        }

        Suburb newSuburb = modelMapper.map(createSuburbDTO, Suburb.class);

        Suburb createdSuburb = this.suburbRepository.save(newSuburb);

        return createdSuburb;
    }

    public Suburb getSuburbById(Long id) {
        Optional<Suburb> foundSuburb = suburbRepository.findById(id);

        if (foundSuburb.isEmpty()) {
            throw new NotFoundException("Suburb not found");
        }

        return foundSuburb.get();
	}
    
}
