package com.benyamephrem.service;

import com.benyamephrem.model.Listing;

import java.util.List;
import java.util.Map;

public interface ListingService {

    List<Listing> findAll();
    List<Map.Entry<String,Integer>> findTop10NamesThatOccur();
    Listing findById(String id);
    List<Listing> findByNeighborhood(String neighborhood);
    int getListingCountForNeighborhood(String neighborhood);
    Map<String, Integer> getNeighborhoodToListingCountMap();
}
