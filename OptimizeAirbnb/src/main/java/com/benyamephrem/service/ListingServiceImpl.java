package com.benyamephrem.service;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;
import com.benyamephrem.utils.EntryComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//TODO:be QUERY RESULTS SHOULD BE CACHED! We don't want to keep running expensive requests that have already been run. It's slowing application down badly.
@Service
public class ListingServiceImpl implements ListingService{
    @Autowired
    private ListingDao listingDao;

    @Override
    public List<Listing> findAll() {
        return listingDao.findAll();
    }

    //TODO:be This is the naive way to achieve this I feel. Shouldn't the database be doing the heavy lifting on query?
    @Override
    public List<Map.Entry<String,Integer>> findTop10NamesThatOccur(List<Listing> listings) {
        Map<String, Integer> namesAndCountMap = new HashMap<>();

        //TODO:be This map populating loop is verbose. Refactor this to a Java 8 stream implementation if you have time.
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

        //Find top 10 names that occur by sorting the list then returning top 10 entries
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

    @Override
    public List<Listing> findByPropertyType(String propertyType) {
        return listingDao.findByPropertyType(propertyType);
    }

    @Override
    public List<Map.Entry<String,Integer>> findTop3PropertyTypesThatOccur(List<Listing> listings) {
        Map<String, Integer> propertyTypeCountMap = new HashMap<>();

        //TODO:be This map populating loop is verbose. Refactor this to a Java 8 stream implementation if you have time.
        for(Listing listing : listings) {
            String key = listing.getPropertyLogistics().getPropertyType();
            if (!propertyTypeCountMap.containsKey(key)) {
                propertyTypeCountMap.put(listing.getPropertyLogistics().getPropertyType(), 1);
            } else{
                propertyTypeCountMap.put(key, propertyTypeCountMap.get(key) + 1);
            }
        }

        List<Map.Entry<String,Integer>> results = new ArrayList<>(propertyTypeCountMap.entrySet());
        results.sort(new EntryComparator());

        return results.subList(0,3);
    }

    @Override
    public List<Listing> findByNeighborhoodAndPropertyType(String neighborhood, String propertyType) {
        return listingDao.findByNeighborhoodAndPropertyType(neighborhood, propertyType);
    }

    //TODO:be Cache each new result here as well to save database calls!
    //TODO: This is very verbose, inefficient, slow, and messy. Revise with Java 8 implementation.
    @Override
    public Map<String, Double> getWeeklyIncomeBasedOnNeighborhoodAndPropertyType(String neighborhood) {
        Map<String, Double> incomeByPropertyTypeMap = new HashMap<>();

        List<Listing> houseListings = listingDao.findByNeighborhoodAndPropertyType(neighborhood, "House");
        List<Listing> apartmentLitings = listingDao.findByNeighborhoodAndPropertyType(neighborhood, "Apartment");
        List<Listing> condoLitings = listingDao.findByNeighborhoodAndPropertyType(neighborhood, "Condominium");

        incomeByPropertyTypeMap.put("House", calculateAverageIncomePerListing(houseListings));
        incomeByPropertyTypeMap.put("Apartment", calculateAverageIncomePerListing(apartmentLitings));
        incomeByPropertyTypeMap.put("Condominium", calculateAverageIncomePerListing(condoLitings));


        return incomeByPropertyTypeMap;
    }

    //TODO:be Revise the accuracy of this calculation, it seems off...
    private double calculateAverageIncomePerListing(List<Listing> listings) {
        //TODO:be Make sure this accounts for minimum and maximum nights the listings allows as well...that matters in calculations
        //Find the days per week that the listing's property is filled based on available data provided to us that exists...we want the most recent data
        double incomeRunningSum = 0.0;

        for(Listing listing : listings){

            double daysFilledPerWeek = 0.0; //Default value
            double listingPossibleAverageWeeklyRevenue = 0.0;

            if(listing.getListingAvaliabilityInPast30Days() != 0){
                //30 - daysListingAvaliable = daysListingIsUnavaliable <---- This means someone is staying there at the time meaning $$$
                daysFilledPerWeek = ((30 - listing.getListingAvaliabilityInPast30Days()) / 7);
            } else if(listing.getListingAvaliabilityInPast60Days() != 0){
                daysFilledPerWeek = ((60 - listing.getListingAvaliabilityInPast60Days()) / 7);
            } else if(listing.getListingAvaliabilityInPast90Days() != 0){
                daysFilledPerWeek = ((90 - listing.getListingAvaliabilityInPast90Days()) / 7);
            }

            //Get weekly prices and if that doesn't exist then get the daily price * 7 as if someone stayed 7 days
            if(listing.getListingFinancials().getWeeklyPrice() != 0){
                listingPossibleAverageWeeklyRevenue = listing.getListingFinancials().getWeeklyPrice() +
                        listing.getListingFinancials().getCleaningFee();
            } else if (listing.getListingFinancials().getDailyPrice() != 0){
                listingPossibleAverageWeeklyRevenue = (listing.getListingFinancials().getDailyPrice()*7) +
                        listing.getListingFinancials().getCleaningFee();
            }

            incomeRunningSum += daysFilledPerWeek * (listingPossibleAverageWeeklyRevenue/7); //Add this listing's project weekly income to running sum
        }

        return incomeRunningSum / listings.size();
    }

}
