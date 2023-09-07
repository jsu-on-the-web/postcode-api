package com.jsu.postcodeapi.Postcode;

import java.util.List;

import com.jsu.postcodeapi.Suburb.Suburb;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class CreatePostcodeDTO {
    @NotNull
    @Getter
    private Integer postcode;

    @Getter
    @Setter
    private List<Suburb> suburbs;


    public void setPostcode(@NotNull int postcode) {
        this.postcode = postcode;
    }
}
