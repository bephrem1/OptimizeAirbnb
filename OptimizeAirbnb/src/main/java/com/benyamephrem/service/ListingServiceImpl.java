package com.benyamephrem.service;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO:be DATA SHOULD BE CACHED so we don't have to keep running expensive requests that have already been run. It's slowing application down badly.
@Service
public class ListingServiceImpl implements ListingService{
    @Autowired
    ListingDao listingDao;

    @Override
    public Listing findById(String id) {
        return listingDao.findById(id);
    }

    @Override
    public List<Listing> findByNeighborhood(String neighborhood) {
        return listingDao.findByNeighborhood(neighborhood);
    }

    @Override
    public int getListingCountForNeighborhood(String neighborhood) {
        List<Listing> matchedListings = findByNeighborhood(neighborhood);
        return matchedListings.size();
    }

    //TODO:be Is passing a map up to the controller the best idea? I think it consolidates things and keeps processing in service layer...revise
    @Override
    public Map<String, Integer> getNeighborhoodToListingCountMap() {
        //Initialize map to return
        Map<String, Integer> neighborhoodToCountMap = new HashMap<>();

        //Get all neighborhoods to get count of from our enum
        Neighborhood[] neighborhoods = Neighborhood.class.getEnumConstants();

        //Populate the map to be returned
        for (Neighborhood n : neighborhoods){
            //Add keys to the map and get the count of listings that neighborhood has
            neighborhoodToCountMap.put(n.getName(), getListingCountForNeighborhood(n.getName()));
        }

        return neighborhoodToCountMap;
    }

}
