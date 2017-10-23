package com.benyamephrem.model;

import com.benyamephrem.service.Location;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

public class Listing {

    @Id
    private String id; //The id MongoDB will assign each index

    @Indexed(direction = IndexDirection.ASCENDING)
    private String listingId; //The listing ID indexed in ascending order
    private String hostId; //The host ID indexed in ascending order


    private String listingUrl; //The listing's url
    private String hostUrl; //The host's profile url
    private String title; //The listing's title
    private String summary; //A short summary about the listing
    private String space; //A short summary about the space the listed property has
    private String description; //A longer description about the listing in more detail
    private String neighborhoodOverview; //A quick overview on the neighborhood
    private String hostName; //Name of the host
    private Date hostSince; //The host has been a member of Airbnb since this date
    private String hostAbout; //Short description about the host
    private String superhost; //Is this host a superhost t == true f == false

    private Location location; //Location data structure created to hold data about location of the listing

    private String propertyType; //Ex: Apartment, House, Condominium
    private String roomType; //Ex: Private Room, Entire Home/Apt
    private int maxAccomodates; //Number of people the property accommodates
    private int maxGuests; //Max guests a person staying can have
    private int bathrooms;
    private int bedrooms;
    private int beds;
    private String bedType; //Ex: Real Bed, Airbed
    private List<String> amenities;

    private double dailyPrice; //Price to stay per day
    private double weeklyPrice; //Price to stay per week
    private double monthlyPrice; //Price to stay per month
    private double cleaningFee; //Cleaning Fee charged to customer
    private double extraPeopleFee; //Fee for having more than (maxAccommodates + maxGuests)
    private double securityDeposit; //Security Deposit required

    private int minNights; //Minimum nights a guest must stay to book
    private int maxNights; //Maximum nights a guest can stay before having to leave
    private int listingAvaliabilityInPast30Days; //How many days the listing has been available to book in the past 30 days
    private int listingAvaliabilityInPast60Days; //How many days the listing has been available to book in the past 60 days
    private int listingAvaliabilityInPast90Days; //How many days the listing has been available to book in the past 90 days
    private int listingAvaliabilityInPastYear; //How many days the listing has been available to book in the past 365 days

    private int numReviews; //Number of reviews the listing has received




    public Listing(){}

}
