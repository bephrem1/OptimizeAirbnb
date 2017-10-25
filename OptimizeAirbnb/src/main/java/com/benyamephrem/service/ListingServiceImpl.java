package com.benyamephrem.service;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;
import com.benyamephrem.utils.EntryComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//TODO:be DATA SHOULD BE CACHED so we don't have to keep running expensive requests that have already been run. It's slowing application down badly.
@Service
public class ListingServiceImpl implements ListingService{
    @Autowired
    ListingDao listingDao;

    @Override
    public List<Listing> findAll() {
        return listingDao.findAll();
    }

    //TODO:be This is the naive way to achieve this I feel. Shouldn't the database be doing the heavy lifting on query?
    @Override
    public List<Map.Entry<String,Integer>> findTop10NamesThatOccur() {
        List<Listing> listings = findAll();
        Map<String, Integer> namesAndCountMap = new HashMap<>();

        //Populate the namesAndCountMap with all the host names and their respective occurrences in listings
        for(Listing listing : listings){
            String key = listing.getListingInfo().getHostName();
            //If the map doesn't have the hostname add it to the HashMap and initialize amount to 1
            if(!namesAndCountMap.containsKey(key)){
                namesAndCountMap.put(key, 1);
            } else{
                //If the map already has this name update the key value with an incremented value
                namesAndCountMap.put(key, namesAndCountMap.get(key) + 1);
            }
        }

        //Find top 10 names that occur
        List<Map.Entry<String,Integer>> results = new ArrayList<>(namesAndCountMap.entrySet());
        results.sort(new EntryComparator());


        return results.subList(0, 10); //Return top 10 entries sorted by value
    }

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
