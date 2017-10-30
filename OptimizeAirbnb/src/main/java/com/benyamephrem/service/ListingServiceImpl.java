package com.benyamephrem.service;

import com.benyamephrem.dao.ListingDao;
import com.benyamephrem.model.Listing;
import com.benyamephrem.model.constants.Neighborhood;
import com.benyamephrem.utils.NumberUtil;
import com.benyamephrem.utils.comparators.DoubleComparator;
import com.benyamephrem.utils.comparators.EntryComparator;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO:be QUERY RESULTS SHOULD BE CACHED! We don't want to keep running expensive requests that have already been run. It's slowing application down badly.
//TODO:be Ensure that averages are adjusted to only take into account listings that were deemed valid to be included in average

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
        incomeByPropertyTypeMap.put("listingsProcessed", (double) (houseListings.size() + apartmentLitings.size() + condoLitings.size()));


        return incomeByPropertyTypeMap;
    }

    //TODO:be Is Pareto's Principle the best idea to use here. I mean...20:80 is pretty pervasive statistically
    //TODO:be This method is really sloppy and text heavy, revise variable names and clean it up. It also only looks at one factor which is short sighted
    @Override
    public double getOptimizedDailyBookingPrice(String neighborhood) {
        double optimizedBookingPriceSum = 0.0;
        Map<Double, Double> priceToAvaliability = new HashMap<>();

        List<Listing> neighborhoodListings = listingDao.findByNeighborhood(neighborhood);

        //Populate the priceToAvaliability map
        for (Listing listing : neighborhoodListings){

            double dailyPrice = listing.getListingFinancials().getDailyPrice();

            //Populate the map with the following parameters (pricePerNight, daysListingIsFilledWeekly)
            if(listing.getListingAvaliabilityInPast30Days() != 0){
                priceToAvaliability.put(dailyPrice, (double) ((30 - listing.getListingAvaliabilityInPast30Days()) / 7));
            } else if(listing.getListingAvaliabilityInPast60Days() != 0){
                priceToAvaliability.put(dailyPrice, (double) ((60 - listing.getListingAvaliabilityInPast60Days()) / 7));
            } else if(listing.getListingAvaliabilityInPast90Days() != 0){
                priceToAvaliability.put(dailyPrice, (double) ((90 - listing.getListingAvaliabilityInPast90Days()) / 7));
            }

        }

        //Sort the map based on the days the listing is filled weekly. The closer the # is to 7 (a full week) the better
        //We then take the top 20% of listings from the sorted list because these are the ones that pull 80% of the income overall
        List<Map.Entry<Double, Double>> results = new ArrayList<>(priceToAvaliability.entrySet());
        results.sort(new DoubleComparator());

        //Find average of the top 20% listings with the best fill rates
        List<Map.Entry<Double, Double>> subList = results.subList(0, (int) Math.round(neighborhoodListings.size() * .2));
        for(Map.Entry<Double, Double> entry : subList){
            optimizedBookingPriceSum += entry.getKey();
        }

        return optimizedBookingPriceSum / subList.size();
    }

    @Override
    public Integer getNeighborhoodAverageOverallReviewScore(String neighborhood) {
        List<Listing> neighborhoodListings = listingDao.findByNeighborhood(neighborhood);
        int overallRatingSum = 0;
        int validCounter = 0;

        for(Listing listing : neighborhoodListings){
            int overallRating = listing.getListingReviewStats().getOverallRating();

            //Only process valid overall ratings
            if(overallRating != 0){
                overallRatingSum += overallRating;
                validCounter++;
            }
        }

        return overallRatingSum / validCounter;
    }

    @Override
    public List<Map.Entry<String, Integer>> getTop10WellReviewedNeighborhoods() {
        Map<String, Integer> top10ReviewedMap = new HashMap<>();

        //Get all neighborhoods to get count of from our enum
        Neighborhood[] neighborhoods = Neighborhood.class.getEnumConstants();

        for(Neighborhood neighborhood : neighborhoods){
            //Populate the map with the rating averages for each neighborhood
            top10ReviewedMap.put(neighborhood.getName(), getNeighborhoodAverageOverallReviewScore(neighborhood.getName()));
        }

        //Sort map
        List<Map.Entry<String, Integer>> results = new ArrayList<>(top10ReviewedMap.entrySet());
        results.sort(new EntryComparator());

        return results.subList(0, 10); //Return top 10 best reviewed neighborhoods
    }

    //TODO: This return type looks nasty...there must be a better way to structure the data we have here...
    @Override
    public List<Table.Cell<String, String, Double>> getSectorsToInvestIn(double aggression) {
        Table<String, String, Double> table = HashBasedTable.create();
        Neighborhood[] neighborhoods = Neighborhood.class.getEnumConstants();

        //Populate map with (Key: Neighborhood Name, Value: (Key: Property Type, Value: Weekly Revenue))
        for(Neighborhood neighborhood : neighborhoods){
            List<Listing> houseListings = listingDao.findByNeighborhoodAndPropertyType(neighborhood.getName(), "House");
            List<Listing> apartmentLitings = listingDao.findByNeighborhoodAndPropertyType(neighborhood.getName(), "Apartment");
            List<Listing> condoListings = listingDao.findByNeighborhoodAndPropertyType(neighborhood.getName(), "Condominium");


            table.put(neighborhood.getName(), "House", NumberUtil.roundHundredths(calculateAverageIncomePerListing(houseListings)));
            table.put(neighborhood.getName(), "Apartment", NumberUtil.roundHundredths(calculateAverageIncomePerListing(apartmentLitings)));
            table.put(neighborhood.getName(), "Condominium", NumberUtil.roundHundredths(calculateAverageIncomePerListing(condoListings)));
        }

        /* Sort the map to float the highest income per week sectors to the top (Property Type & Neighborhood = Sector) */
        //Comparator to order cells based on value. Credits to Louis Wasserman on stack overflow for this approach.
        Ordering<Table.Cell<String, String, Double>> comparator =
                new Ordering<Table.Cell<String, String, Double>>() {
                    public int compare(
                            Table.Cell<String, String, Double> cell1,
                            Table.Cell<String, String, Double> cell2) {
                            if(!Double.isNaN(cell1.getValue()) && Double.isNaN(cell2.getValue())){
                                return 1;
                            } else if(Double.isNaN(cell1.getValue()) && !Double.isNaN(cell2.getValue())){
                                return -1;
                            } else{
                                return cell1.getValue().compareTo(cell2.getValue());
                            }
                    }
                };

        //Create ImmutableTable Builder
        ImmutableTable.Builder<String, String, Double> sortedBuilder = ImmutableTable.builder();

        //Sort cells using comparator
        for (Table.Cell<String, String, Double> cell :
                comparator.reverse().sortedCopy(table.cellSet())) {
            sortedBuilder.put(cell);
        }

        //Final sorted table
        ImmutableTable<String, String, Double> sortedTable = sortedBuilder.build();

        //Return a sublist of cells based on aggression provided by user
        //So if we had 100 listings with a 25% aggression, this would return a list of map entries 0 through 25 (pre-sorted of course)
        return sortedTable.cellSet().stream()
                                    .limit((int) Math.round(sortedTable.size() * aggression))
                                    .collect(Collectors.toList());
    }

    //TODO: Fix faulty calculation here...reimplement as this is not correct
    @Override
    public double findWeeksToBreakeven(List<Table.Cell<String, String, Double>> sectorsInvestedIn, double investment) {
        double totalWeeklyIncome = 0.0;

        //Calculate the weekly income expected beased on generated investment sectors
        for (Table.Cell<String, String, Double> cell : sectorsInvestedIn){
            totalWeeklyIncome += cell.getValue();
        }

        //This will return weeks until initial investment is returned (break-even point)
        return NumberUtil.roundHundredths(investment / totalWeeklyIncome);
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
