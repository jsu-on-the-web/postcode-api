package com.jsu.postcodeapi.Suburb;

import com.jsu.postcodeapi.Postcode.Postcode;
import com.jsu.postcodeapi.enums.EState;

import jakarta.validation.constraints.Pattern;

public class UpdateSuburbDTO {
    @Pattern(regexp = "^(?=\\S).*$", message = "Suburb name cannot be an empty string")
    String suburbName;

    @Pattern(regexp = "^(?=\\S).*$", message = "Suburb state cannot be an empty string")
    EState state;

    Long population;

    Postcode postcode;
}
