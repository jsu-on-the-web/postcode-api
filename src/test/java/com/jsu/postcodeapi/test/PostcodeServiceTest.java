package com.jsu.postcodeapi.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jsu.postcodeapi.Postcode.CreatePostcodeDTO;
import com.jsu.postcodeapi.Postcode.PostcodeRepository;
import com.jsu.postcodeapi.Postcode.PostcodeService;
import com.jsu.postcodeapi.exceptions.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class PostcodeServiceTest {
    @Mock
    private PostcodeRepository postcodeRepository;

    private PostcodeService testingService;

    @BeforeEach
    void setup() {
        this.testingService = new PostcodeService(postcodeRepository);
    }


    /* ----------------------------- Creation Tests ----------------------------- */
    @Test
    void shouldNotAddInvalidPostcode() {
        CreatePostcodeDTO createPostcodeDTO = new CreatePostcodeDTO();
        createPostcodeDTO.setPostcode(42424242);
        Assertions.assertThatThrownBy(() -> testingService.addPostcode(createPostcodeDTO))
            .isInstanceOf(BadRequestException.class);
    }

    /* -------------------------------- Get Tests ------------------------------- */
    @Test 
    void getAllPostcodesShouldCallFindAll() {
        testingService.getAllPostcodes();
        Mockito.verify(postcodeRepository).findAll(); 
    }
}