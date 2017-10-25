package com.benyamephrem.model;

import com.benyamephrem.model.leafs.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class Listing {

    @Id
    private String id; //The id MongoDB will assign each index

    //TODO:be Assess what needs to be indexed here (and in what order) based on queries you write later for efficiency
    private String listingId; //The listing ID indexed in ascending order
    private String hostId; //The host ID indexed in ascending order

    private ListingInfo listingInfo; //Class containing general info about the listing like host name and listing description

    private ListingLocation listingLocation; //Location data type created to hold data about listingLocation of the listing

    private PropertyLogistics propertyLogistics; //Class containing property logistical data like amenities and max guests accommodated

    private ListingFinancials listingFinancials; //Price and cost data about the property

    //TODO:be Can we consolidate these variables below to clean things up?
    private int minNights; //Minimum nights a guest must stay to book
    private int maxNights; //Maximum nights a guest can stay before having to leave
    private int listingAvaliabilityInPast30Days; //How many days the listing has been available to book in the past 30 days
    private int listingAvaliabilityInPast60Days; //How many days the listing has been available to book in the past 60 days
    private int listingAvaliabilityInPast90Days; //How many days the listing has been available to book in the past 90 days
    private int listingAvaliabilityInPastYear; //How many days the listing has been available to book in the past 365 days

    private ListingReviewStats listingReviewStats; //Data structure made to better organize review statistics

    public Listing(){}

    //TODO:be This is a very verbose constructor...implement the builder design pattern if you have time...
    public Listing(String listingId, String hostId, ListingInfo listingInfo, ListingLocation listingLocation,
                   PropertyLogistics propertyLogistics, ListingFinancials listingFinancials, int minNights, int maxNights,
                   int listingAvaliabilityInPast30Days, int listingAvaliabilityInPast60Days, int listingAvaliabilityInPast90Days,
                   int listingAvaliabilityInPastYear, ListingReviewStats listingReviewStats) {
        this.listingId = listingId;
        this.hostId = hostId;
        this.listingInfo = listingInfo;
        this.listingLocation = listingLocation;
        this.propertyLogistics = propertyLogistics;
        this.listingFinancials = listingFinancials;
        this.minNights = minNights;
        this.maxNights = maxNights;
        this.listingAvaliabilityInPast30Days = listingAvaliabilityInPast30Days;
        this.listingAvaliabilityInPast60Days = listingAvaliabilityInPast60Days;
        this.listingAvaliabilityInPast90Days = listingAvaliabilityInPast90Days;
        this.listingAvaliabilityInPastYear = listingAvaliabilityInPastYear;
        this.listingReviewStats = listingReviewStats;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public ListingInfo getListingInfo() {
        return listingInfo;
    }

    public void setListingInfo(ListingInfo listingInfo) {
        this.listingInfo = listingInfo;
    }

    public ListingLocation getListingLocation() {
        return listingLocation;
    }

    public void setListingLocation(ListingLocation listingLocation) {
        this.listingLocation = listingLocation;
    }

    public PropertyLogistics getPropertyLogistics() {
        return propertyLogistics;
    }

    public void setPropertyLogistics(PropertyLogistics propertyLogistics) {
        this.propertyLogistics = propertyLogistics;
    }

    public ListingFinancials getListingFinancials() {
        return listingFinancials;
    }

    public void setListingFinancials(ListingFinancials listingFinancials) {
        this.listingFinancials = listingFinancials;
    }

    public int getMinNights() {
        return minNights;
    }

    public void setMinNights(int minNights) {
        this.minNights = minNights;
    }

    public int getMaxNights() {
        return maxNights;
    }

    public void setMaxNights(int maxNights) {
        this.maxNights = maxNights;
    }

    public int getListingAvaliabilityInPast30Days() {
        return listingAvaliabilityInPast30Days;
    }

    public void setListingAvaliabilityInPast30Days(int listingAvaliabilityInPast30Days) {
        this.listingAvaliabilityInPast30Days = listingAvaliabilityInPast30Days;
    }

    public int getListingAvaliabilityInPast60Days() {
        return listingAvaliabilityInPast60Days;
    }

    public void setListingAvaliabilityInPast60Days(int listingAvaliabilityInPast60Days) {
        this.listingAvaliabilityInPast60Days = listingAvaliabilityInPast60Days;
    }

    public int getListingAvaliabilityInPast90Days() {
        return listingAvaliabilityInPast90Days;
    }

    public void setListingAvaliabilityInPast90Days(int listingAvaliabilityInPast90Days) {
        this.listingAvaliabilityInPast90Days = listingAvaliabilityInPast90Days;
    }

    public int getListingAvaliabilityInPastYear() {
        return listingAvaliabilityInPastYear;
    }

    public void setListingAvaliabilityInPastYear(int listingAvaliabilityInPastYear) {
        this.listingAvaliabilityInPastYear = listingAvaliabilityInPastYear;
    }

    public ListingReviewStats getListingReviewStats() {
        return listingReviewStats;
    }

    public void setListingReviewStats(ListingReviewStats listingReviewStats) {
        this.listingReviewStats = listingReviewStats;
    }
}