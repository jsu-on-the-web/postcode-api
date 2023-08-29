package com.jsu.postcodeapi.Postcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsu.postcodeapi.exceptions.BadRequestException;

@Service
public class PostcodeService {
    private PostcodeRepository postcodeRepository;

    @Autowired
    public PostcodeService(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }

    public List<Postcode> getAllPostcodes() {
        return postcodeRepository.findAll();
    }

    public void addPostcode(Postcode postcode) {
        Boolean existsPostcode = postcodeRepository.exists(postcode.getPostcode());
        if (existsPostcode) {
            throw new BadRequestException("Postcode " + postcode.getPostcode() + " already exists");
        }

        postcodeRepository.save(postcode);
    }

    public Postcode getPostcodeBySuburb(String suburb) {
        return null;
    }


}