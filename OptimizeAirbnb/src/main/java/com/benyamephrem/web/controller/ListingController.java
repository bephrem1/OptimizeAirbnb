package com.benyamephrem.web.controller;

import com.benyamephrem.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListingController {

    @Autowired
    ListingService listingService;

    @RequestMapping("/")
    public String getHomepage(){
        return "/index";
    }
}
