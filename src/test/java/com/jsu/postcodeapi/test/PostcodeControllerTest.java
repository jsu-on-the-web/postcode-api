package com.jsu.postcodeapi.test;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpRequest;

import com.jsu.postcodeapi.Postcode.PostcodeController;
import com.jsu.postcodeapi.Postcode.PostcodeRepository;
import com.jsu.postcodeapi.Postcode.PostcodeService;

@ExtendWith(MockitoExtension.class)
public class PostcodeControllerTest {
    @Mock
    private PostcodeService postcodeService;

    private PostcodeController testingController;

    @BeforeEach
    void setup() {
        this.testingController = new PostcodeController(postcodeService);
    }

    /* ------------------------------ Create Tests ------------------------------ */

    /* ------------------------------- Read Tests ------------------------------- */

    /* ------------------------------ Update Tests ------------------------------ */

    /* ------------------------------ Delete Tests ------------------------------ */
}
