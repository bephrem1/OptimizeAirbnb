package com.benyamephrem.utils;

import com.benyamephrem.model.constants.Neighborhood;

public class Locator {

    public Locator() {
    }

    //TODO: Rewrite using a k-d tree implementation, this is the naive way to solve this...
    public static Neighborhood findClosestNeighborhood(double latitude, double longitude){
        Neighborhood closestNeighborhood = null; //TODO:be I don't like this null here, find a way to fix it with a good default
        double closestDistance = Integer.MAX_VALUE; //initial placeholder value (in meters)

        Neighborhood[] neighborhoods = Neighborhood.class.getEnumConstants();

        for(Neighborhood neighborhood : neighborhoods){
            double distance = distance(latitude, longitude, neighborhood.getLatitude(),
                    neighborhood.getLongitude(), 0,0);
            if(distance < closestDistance){
                closestNeighborhood = neighborhood;
            }
        }

        return closestNeighborhood;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     *
     * Credits to user "Dommer" on Stack Overflow for this method
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
