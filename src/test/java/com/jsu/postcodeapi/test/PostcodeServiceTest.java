package com.jsu.postcodeapi.test;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
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
import com.jsu.postcodeapi.exceptions.NotFoundException;

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
    @Test
    void shouldNotAddInvalidPostcode() {
        CreatePostcodeDTO dto = new CreatePostcodeDTO();

        dto.setPostcode(12345);

        Assertions.assertThatThrownBy(() -> testingService.addPostcode(dto))
                .isInstanceOf(BadRequestException.class);
    }

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

    /*
     * -------------------------------- Read Tests -------------------------------
     */
    @Test
    void getAllPostcodesShouldCallFindAll() {
        testingService.getAllPostcodes();
        Mockito.verify(postcodeRepository).findAll();
    }

    /* ------------------------------ Update Tests ------------------------------ */
    @Test
    void shouldUpdateValidPostcode() {
        ArgumentCaptor<Postcode> postcodeCaptor = ArgumentCaptor.forClass(Postcode.class);
        CreatePostcodeDTO createPostcodeDTO = new CreatePostcodeDTO();
        // TODO: Figure out how to test update methods
    }

    /* ------------------------------ Delete Tests ------------------------------ */

    // ! BUG: The following test does not work. 
    
    // @Test
    // void shouldDeletePostcodeWithExistingId() {
    //     Long id = 1L;
    //     // Create a Postcode object and add it to the mock repository
    //     Postcode existingPostcode = new Postcode(
    //         1234
    //     );
    //     CreatePostcodeDTO createPostcodeDTO = new CreatePostcodeDTO();
    //     createPostcodeDTO.setPostcode(existingPostcode.getPostcode());

    //     Mockito.when(modelMapper.map(createPostcodeDTO, Postcode.class)).thenReturn(existingPostcode);

    //     testingService.addPostcode(createPostcodeDTO);


    //     BDDMockito.given(postcodeRepository.findById(id))
    //             .willReturn(Optional.of(existingPostcode));

    //     testingService.deleteById(id);

    //     ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

    //     Mockito.verify(postcodeRepository).deleteById(idCaptor.capture());

    //     Assertions.assertThat(idCaptor.getValue()).isEqualTo(id);
    // }

    @Test
    void shouldNotDeletePostcodeWithInvalidId() {
        Long id = 100L;
        Assertions.assertThatThrownBy(() -> testingService.deleteById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Postcode that's associated with " + id + " does not exist, unable to delete");
    }
}