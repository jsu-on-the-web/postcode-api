package com.jsu.postcodeapi.Postcode;

import java.util.List;

import com.jsu.postcodeapi.Suburb.Suburb;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class CreatePostcodeDTO {
    @NotBlank
    @Getter
    @Setter
    private Integer postcode;

    @NotBlank
    @Getter
    @Setter
    private List<Suburb> suburbs;
}
