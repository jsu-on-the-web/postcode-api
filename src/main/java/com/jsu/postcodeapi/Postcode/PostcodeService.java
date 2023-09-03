package com.jsu.postcodeapi.Postcode;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsu.postcodeapi.exceptions.BadRequestException;

import jakarta.validation.Valid;

@Service
public class PostcodeService {
    private PostcodeRepository postcodeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PostcodeService(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }

    public List<Postcode> getAllPostcodes() {
        return this.postcodeRepository.findAll();
    }

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

    public Optional<Postcode> getPostcodeBySuburb(String suburb) {
        Optional<Postcode> findBySuburb = this.postcodeRepository.findBySuburb(suburb);

        return findBySuburb;
    }


    private Optional<Postcode> findById(Long id) {
        Optional<Postcode> foundPostcode = this.postcodeRepository.findById(id);
        return foundPostcode;
    }

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


}