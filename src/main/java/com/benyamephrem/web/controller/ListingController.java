package com.benyamephrem.web.controller;

import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;
import com.benyamephrem.service.ListingService;
import com.benyamephrem.utils.Locator;
import com.benyamephrem.utils.NumberUtil;
import com.google.common.collect.Table;
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
    public String getHomepage(){
        return "index";
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
    public String getIncomeEstimationPage(){

        return "/income-estimation";
    }

    @RequestMapping(value = "/income-estimation", method = RequestMethod.POST)
    public String processIncomeEstimationValue(@RequestParam double latitude, @RequestParam double longitude, RedirectAttributes redirectAttributes){
        Neighborhood neighborhood = Locator.findClosestNeighborhood(latitude, longitude);
        Map<String, Double> map = listingService
                .getWeeklyIncomeBasedOnNeighborhoodAndPropertyType(neighborhood.getName());

        //Add results from calculation to redirect so that person can see data for only one redirect then resets
        redirectAttributes.addFlashAttribute("houseEstimation", NumberUtil.roundHundredths(map.get("House")));
        redirectAttributes.addFlashAttribute("apartmentEstimation", NumberUtil.roundHundredths(map.get("Apartment")));
        redirectAttributes.addFlashAttribute("condominiumEstimation", NumberUtil.roundHundredths(map.get("Condominium")));
        redirectAttributes.addFlashAttribute("listingsProcessed", Math.round(map.get("listingsProcessed")));
        redirectAttributes.addFlashAttribute("neighborhood", neighborhood.getName());

        return "redirect:/income-estimation";
    }


    @RequestMapping("/price-optimization")
    public String getPriceOptimizationPage(){

        return "/price-optimization";
    }

    @RequestMapping(value = "/price-optimization", method = RequestMethod.POST)
    public String processPriceOptimizationValue(@RequestParam double latitude, @RequestParam double longitude, RedirectAttributes redirectAttributes){
        Neighborhood neighborhood = Locator.findClosestNeighborhood(latitude, longitude);

        double result = listingService.getOptimizedDailyBookingPrice(neighborhood.getName());

        //Add optimized price to the model map
        redirectAttributes.addFlashAttribute("optimizedPrice", NumberUtil.roundHundredths(result));
        redirectAttributes.addFlashAttribute("neighborhood", neighborhood.getName());

        return "redirect:/price-optimization";
    }

    @RequestMapping("/best-neighborhoods")
    public String getBestNeighborhoods(){
        return "/best-neighborhoods";
    }

    @RequestMapping(value = "/best-neighborhoods", method = RequestMethod.POST)
    public String processBestNeighborhoods(@RequestParam double latitude, @RequestParam double longitude, RedirectAttributes redirectAttributes){
        List<Map.Entry<String, Integer>> reviewMap = listingService.getTop10WellReviewedNeighborhoods();
        Neighborhood neighborhood = Locator.findClosestNeighborhood(latitude, longitude);

        for(int i = 1; i <= 10; i++){
            //Pass the top 10 names data to thymeleaf for processing into charts
            //Ex Attribute Passed: name1 and nameCount1 (the #1 most occurring name)
            redirectAttributes.addFlashAttribute("neighborhood" + i, reviewMap.get(i-1).getKey());
            redirectAttributes.addFlashAttribute("reviewScore" + i, reviewMap.get(i-1).getValue());
        }

        redirectAttributes.addFlashAttribute("yourNeighborhood", neighborhood.getName());
        redirectAttributes.addFlashAttribute("yourNeighborhoodScore", listingService.getNeighborhoodAverageOverallReviewScore(neighborhood.getName()));

        return "redirect:/best-neighborhoods";
    }

    @RequestMapping("/invest")
    public String getInvestmentCalculator(){
        return "/invest";
    }

    @RequestMapping(value = "/invest", method = RequestMethod.POST)
    public String processInvestmentCalculator(@RequestParam double investment, @RequestParam double aggression, RedirectAttributes redirectAttributes){
        List<Table.Cell<String, String, Double>> sectors = listingService.getSectorsToInvestIn(aggression/100);
        double weeksUntilBreakeven = listingService.findWeeksToBreakeven(sectors, investment);

        redirectAttributes.addFlashAttribute("sectors", sectors);
        redirectAttributes.addFlashAttribute("investmentAmountPer", NumberUtil.roundHundredths(investment/sectors.size()));
        redirectAttributes.addFlashAttribute("breakeven", weeksUntilBreakeven);

        return "redirect:/invest";
    }
}