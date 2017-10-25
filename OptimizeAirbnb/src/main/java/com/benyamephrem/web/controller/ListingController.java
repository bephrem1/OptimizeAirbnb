package com.benyamephrem.web.controller;

import com.benyamephrem.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ListingController {

    @Autowired
    ListingService listingService;

    @RequestMapping("/")
    public String getHomepage(Model model){
        return "/index";
    }

    @RequestMapping("/visualize")
    public String getVisualizationChartsPage(Model model){
        Map<String, Integer> neighborhoodToCountMap = listingService.getNeighborhoodToListingCountMap();
        List<Map.Entry<String,Integer>> top10NamesThatOccur = listingService.findTop10NamesThatOccur();

        //TODO:be This is a good way to condense the passage of data to view? Odd how the view must be rigid with neighborhood names...revise
        //Populate model with Neighborhood Listing Count attributes to pass to the view layer
        for(Map.Entry<String, Integer> entry : neighborhoodToCountMap.entrySet()){
            model.addAttribute(entry.getKey().toLowerCase().replaceAll("[ /]", ""), entry.getValue());
        }

        //TODO:be I could break top 10 list down here or in thymeleaf. I'd rather keep it simply here for now and change later
        for(int i = 1; i <= 10; i++){
            //Pass the top 10 names data to thymeleaf for processing into charts
            //Ex Attribute Passed: name1 and nameCount1 (the #1 most occurring name)
            model.addAttribute("name" + i, top10NamesThatOccur.get(i-1).getKey());
            model.addAttribute("nameCount" + i, top10NamesThatOccur.get(i-1).getValue());
        }

        return "/visualize";
    }
}
