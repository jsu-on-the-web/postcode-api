package com.jsu.postcodeapi.Postcode;

import java.util.List;

import com.jsu.postcodeapi.Suburb.Suburb;

import jakarta.validation.constraints.Pattern;

public class UpdatePostcodeDTO {
    // * NOTE: This pattern will only accept numbers from 0000 to 9999 as
    // * we're operating under the assumption that this API is Australia only for now.
    @Pattern(regexp = "^[0-9]{4}$", message = "Postcode must be 4 digits")
    Integer postcode;

    /**
     * List of Suburbs that this Postcode covers
     * It should be initialized with either an empty list or a list of suburbs that do not already have a postcode
     */
    List<Suburb> suburbs;

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public void setSuburbs(List<Suburb> suburbs) {
        this.suburbs = suburbs;
    }
}
