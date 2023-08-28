package com.jsu.postcodeapi.Suburb;

import com.jsu.postcodeapi.Postcode.Postcode;
import com.jsu.postcodeapi.enums.EState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class CreateSuburbDTO {
    @NotBlank
    @Getter
    @Setter
    private String suburbName;

    @NotBlank
    @Getter
    @Setter
    private EState state;

    @NotNull
    @Getter
    @Setter
    private Long population;

    @NotNull
    @Getter
    @Setter
    private Postcode postcode;
}
