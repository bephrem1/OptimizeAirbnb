package com.benyamephrem.web;

import com.benyamephrem.ListingController;
import com.benyamephrem.service.ListingService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(MockitoJUnitRunner.class) //Let's us use annotations in the main class for MockitoJUnitRunner vs JUnit JunitUnitRunner
public class ListingControllerTest {

    private MockMvc mockMvc;

    //We mock the listing service so we can isolate behaviour to only the listing controller
    @Mock
    private ListingService service;

    //We inject the mock service into the controller we are about to test so we can use it
    @InjectMocks
    private ListingController controller;

    @Before
    public void setUp() {
        mockMvc = new MockMvcBuilders().standaloneSetup(controller).build();
    }

}
