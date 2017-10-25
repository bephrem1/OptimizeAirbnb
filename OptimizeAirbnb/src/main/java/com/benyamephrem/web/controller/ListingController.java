package com.benyamephrem.web.controller;

import com.benyamephrem.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ListingController {

    @Autowired
    ListingService listingService;

    @RequestMapping("/")
    public String getHomepage(Model model){
        Map<String, Integer> neighborhoodToCountMap = listingService.getNeighborhoodToListingCountMap();

        //TODO:be This is a good way to condense the passage of data to view? Odd how the view must be rigid with neighborhood names...revise
        //Populate model with Neighborhood Listing Count attributes to pass to the view layer
        for(Map.Entry<String, Integer> entry : neighborhoodToCountMap.entrySet()){
            model.addAttribute(entry.getKey().toLowerCase().replaceAll("[ /]", ""), entry.getValue());
        }

        return "/index";
    }
}
