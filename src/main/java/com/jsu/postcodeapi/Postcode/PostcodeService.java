package com.jsu.postcodeapi.Postcode;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsu.postcodeapi.exceptions.BadRequestException;
import com.jsu.postcodeapi.exceptions.NotFoundException;

import jakarta.validation.Valid;

@Service
public class PostcodeService {
    private PostcodeRepository postcodeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PostcodeService(PostcodeRepository postcodeRepository, ModelMapper modelMapper) {
        this.postcodeRepository = postcodeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves all postcodes from the repository.
     *
     * @return a list of Postcode objects representing all the postcodes
     *         in the repository
     */
    public List<Postcode> getAllPostcodes() {
        return this.postcodeRepository.findAll();
    }

    /**
     * Adds a new postcode to the system.
     *
     * @param  createPostcodeDTO  the DTO containing the details of the postcode to be added
     * @return                   the newly created Postcode object
     */
    public Postcode addPostcode(@Valid CreatePostcodeDTO createPostcodeDTO) {
        Boolean existsPostcode = postcodeRepository.exists(createPostcodeDTO.getPostcode());
        if (existsPostcode) {
            throw new BadRequestException("Postcode " + createPostcodeDTO.getPostcode() + " already exists");
        }

        // Checking that the postcode is also in-bounds (must be 4 digits)
        if (!createPostcodeDTO.getPostcode().toString().matches("\\d{4}")) {
            throw new BadRequestException("Postcode must be 4 digits");
        }

        Postcode newPostcode = modelMapper.map(createPostcodeDTO, Postcode.class);

        Postcode createdPostcode = this.postcodeRepository.save(newPostcode);

        return createdPostcode;
    }

    /**
     * Retrieves a Postcode object by suburb.
     *
     * @param  suburb  the name of the suburb
     * @return         an Optional containing the Postcode object if found, or an empty Optional if not found
     */
    public Optional<Postcode> getPostcodeBySuburb(String suburb) {
        Optional<Postcode> findBySuburb = this.postcodeRepository.findBySuburb(suburb);

        if (findBySuburb.isEmpty()) {
            throw new NotFoundException("Postcode not found");
        }

        return findBySuburb;
    }


    /**
     * Retrieves a `Postcode` object by its ID.
     *
     * @param  id  the ID of the `Postcode` object to retrieve
     * @return     an optional containing the found `Postcode` object, or an empty optional if not found
     */
    private Optional<Postcode> findById(Long id) {
        Optional<Postcode> foundPostcode = this.postcodeRepository.findById(id);
        return foundPostcode;
    }

    /**
     * Updates a postcode with the given ID using the provided data.
     *
     * @param  id    the ID of the postcode to update
     * @param  data  the data to update the postcode with
     * @return       an optional containing the updated postcode if it exists, otherwise an empty optional
     */
    public Optional<Postcode> updatePostcode(Long id, @Valid UpdatePostcodeDTO data) {
        //1. Find the postcode to update 
        Optional<Postcode> postcodeToUpdate = this.findById(id);

        // 2. If it exists, update it and save it back to the repository
        if (postcodeToUpdate.isPresent()) {
            Postcode existingPostcode = postcodeToUpdate.get();
            modelMapper.map(data, existingPostcode);
            return Optional.of(this.postcodeRepository.save(existingPostcode));
        }
        return postcodeToUpdate;
    }

    /**
     * Deletes a postcode by ID.
     *
     * @param  id  the ID of the postcode to be deleted
     * @return     true if the postcode was successfully deleted, false otherwise
     */
    public boolean deleteById(Long id) {
        Optional<Postcode> deletedPostcode = this.findById(id);
        if (deletedPostcode.isEmpty()) {
            throw new NotFoundException("Postcode that's associated with " + id + " does not exist, unable to delete");
        }

        this.postcodeRepository.delete(deletedPostcode.get());
        return true;
    }
}