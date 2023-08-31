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
        return postcodeRepository.findAll();
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
        Optional<Postcode> findBySuburb = postcodeRepository.findBySuburb(suburb);

        return findBySuburb;
    }

}