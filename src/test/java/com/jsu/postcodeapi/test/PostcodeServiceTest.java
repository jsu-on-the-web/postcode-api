package com.jsu.postcodeapi.test;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.jsu.postcodeapi.Postcode.CreatePostcodeDTO;
import com.jsu.postcodeapi.Postcode.Postcode;
import com.jsu.postcodeapi.Postcode.PostcodeRepository;
import com.jsu.postcodeapi.Postcode.PostcodeService;
import com.jsu.postcodeapi.Suburb.Suburb;
import com.jsu.postcodeapi.exceptions.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class PostcodeServiceTest {
    @Mock
    private PostcodeRepository postcodeRepository;
    @Mock
    private ModelMapper modelMapper;

    private PostcodeService testingService;

    @BeforeEach
    void setup() {
        this.testingService = new PostcodeService(postcodeRepository, modelMapper);
    }


    /* ----------------------------- Creation Tests ----------------------------- */
    // @Test
    // void shouldNotAddInvalidPostcode() {
    //     ArgumentCaptor<CreatePostcodeDTO> createPostcodeDTO = ArgumentCaptor.forClass(CreatePostcodeDTO.class);
    //     CreatePostcodeDTO dto = new CreatePostcodeDTO();
    //     dto.setPostcode(12345);
    //     Assertions.assertThatThrownBy(() -> testingService.addPostcode(dto))
    //             .isInstanceOf(BadRequestException.class);
    // }

    @Test
    void shouldAddValidPostcode() {
        Postcode postcode = new Postcode(
                1234);
        // Make sure this postcode doesn't already exist
        BDDMockito.given(postcodeRepository.exists(postcode.getPostcode())).willReturn(false);

        CreatePostcodeDTO createPostcodeDTO = new CreatePostcodeDTO();
        createPostcodeDTO.setPostcode(postcode.getPostcode());

        Mockito.when(modelMapper.map(createPostcodeDTO, Postcode.class)).thenReturn(postcode);

        testingService.addPostcode(createPostcodeDTO);

        ArgumentCaptor<Postcode> postcodeCaptor = ArgumentCaptor.forClass(Postcode.class);

        Mockito.verify(postcodeRepository).save(postcodeCaptor.capture());

        Assertions.assertThat(postcodeCaptor.getValue()).isEqualTo(postcode);
        Assertions.assertThat(postcodeCaptor.getValue().getPostcode()).isEqualTo(postcode.getPostcode());
    }

    /* -------------------------------- Get Tests ------------------------------- */
    @Test 
    void getAllPostcodesShouldCallFindAll() {
        testingService.getAllPostcodes();
        Mockito.verify(postcodeRepository).findAll();
    }
}