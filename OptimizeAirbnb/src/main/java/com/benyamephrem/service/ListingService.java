package com.benyamephrem.service;

import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;

import java.util.List;
import java.util.Map;

public interface ListingService {

    List<Listing> findAll();
    List<Map.Entry<String,Integer>> findTop10NamesThatOccur(List<Listing> listings);
    Listing findById(String id);
    List<Listing> findByNeighborhood(String neighborhood);
    int getListingCountForNeighborhood(String neighborhood);
    Map<String, Integer> getNeighborhoodToListingCountMap();
    List<Listing> findByPropertyType(String propertyType);
    List<Map.Entry<String,Integer>> findTop3PropertyTypesThatOccur(List<Listing> listings);
    List<Listing> findByNeighborhoodAndPropertyType(String neighborhood, String propertyType);
    Map<String, Double> getWeeklyIncomeBasedOnNeighborhoodAndPropertyType(String neighborhood);
    double getOptimizedDailyBookingPrice(String neighborhood);
    Integer getNeighborhoodAverageOverallReviewScore(String neighborhood);
    List<Map.Entry<String, Integer>> getTop10WellReviewedNeighborhoods();
}
