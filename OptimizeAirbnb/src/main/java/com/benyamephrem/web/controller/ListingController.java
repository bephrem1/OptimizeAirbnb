package com.benyamephrem.web.controller;

import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;
import com.benyamephrem.service.ListingService;
import com.benyamephrem.utils.Locator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class ListingController {

    @Autowired
    private ListingService listingService;

    @RequestMapping("/")
    public String getHomepage(Model model){
        return "/index";
    }

    @RequestMapping("/visualize")
    public String getVisualizationChartsPage(Model model){
        List<Listing> listings = listingService.findAll();

        Map<String, Integer> neighborhoodToCountMap = listingService.getNeighborhoodToListingCountMap();
        List<Map.Entry<String,Integer>> top10NamesThatOccur = listingService.findTop10NamesThatOccur(listings);
        List<Map.Entry<String,Integer>> top3PropertyTypesThatOccur = listingService.findTop3PropertyTypesThatOccur(listings);

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

        //Populate the model with the top 3 property types that occur
        for(int i = 1; i <=3; i++){
            model.addAttribute("property" + i, top3PropertyTypesThatOccur.get(i-1).getKey());
            model.addAttribute("propertyCount" + i, top3PropertyTypesThatOccur.get(i-1).getValue());
        }

        return "/visualize";
    }

    @RequestMapping("/income-estimation")
    public String getIncomeEstimationPage(Model model){

        return "/income-estimation";
    }

    @RequestMapping(value = "/income-estimation", method = RequestMethod.POST)
    public String processIncomeEstimationValue(@RequestParam double latitude, @RequestParam double longitude, RedirectAttributes redirectAttributes){
        Neighborhood neighborhood = Locator.findClosestNeighborhood(latitude, longitude);
        Map<String, Double> map = listingService
                .getWeeklyIncomeBasedOnNeighborhoodAndPropertyType(neighborhood.getName());

        //Add results from calculation to redirect so that person can see data for only one redirect then resets
        redirectAttributes.addFlashAttribute("houseEstimation", map.get("House"));
        redirectAttributes.addFlashAttribute("apartmentEstimation", map.get("Apartment"));
        redirectAttributes.addFlashAttribute("condominiumEstimation", map.get("Condominium"));
        redirectAttributes.addFlashAttribute("listingsProcessed", map.get("listingsProcessed"));
        redirectAttributes.addFlashAttribute("neighborhood", neighborhood.getName());

        return "redirect:/income-estimation";
    }


    @RequestMapping("/price-optimization")
    public String getPriceOptimizationPage(Model model){

        return "/price-optimization";
    }

    @RequestMapping(value = "/price-optimization", method = RequestMethod.POST)
    public String processPriceOptimizationValue(@RequestParam double latitude, @RequestParam double longitude){


        return "/price-optimization";
    }
}
