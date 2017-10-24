package com.benyamephrem.service;

import com.benyamephrem.model.Listing;

import java.util.List;
import java.util.Map;

public interface ListingService {

    Listing findById(String id);
    List<Listing> findByNeighborhood(String neighborhood);
    int getListingCountForNeighborhood(String neighborhood);
    Map<String, Integer> getNeighborhoodToListingCountMap();
}
