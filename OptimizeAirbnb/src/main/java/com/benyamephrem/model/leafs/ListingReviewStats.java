package com.benyamephrem.model.leafs;

/*
This class is made to consolidate and organize review data per listing
 */

public class ListingReviewStats {

    private int numTotalReviews; //Number of reviews the listing has received
    private int reviewsPerMonth; //Number of reviews the listing receives per month
    private int listingAccuracyScore; //Accuracy of the listing as assessed by the guest (from 0-10)
    private int cleanlinessScore; //Cleanliness of the property as assessed by the guest (from 0-10)
    private int checkinScore; //Score of host's check-in process ease, etc. (from 0-10)
    private int communicationScore; //Score based on host's communication (from 0-10)
    private int locationScore; //Score of the overall location as assessed by the guest (from 0-10)
    private int valueScore; //Guest's retrospective assessment of the overall value of the offer after staying (from 0-10)
    private int overallRating; //Conglomerate rating of the stay, property, and experience the guest had (from 0-100)

    public ListingReviewStats(){

    }

    public int getNumTotalReviews() {
        return numTotalReviews;
    }

    public void setNumTotalReviews(int numTotalReviews) {
        this.numTotalReviews = numTotalReviews;
    }

    public int getReviewsPerMonth() {
        return reviewsPerMonth;
    }

    public void setReviewsPerMonth(int reviewsPerMonth) {
        this.reviewsPerMonth = reviewsPerMonth;
    }

    public int getListingAccuracyScore() {
        return listingAccuracyScore;
    }

    public void setListingAccuracyScore(int listingAccuracyScore) {
        this.listingAccuracyScore = listingAccuracyScore;
    }

    public int getCleanlinessScore() {
        return cleanlinessScore;
    }

    public void setCleanlinessScore(int cleanlinessScore) {
        this.cleanlinessScore = cleanlinessScore;
    }

    public int getCheckinScore() {
        return checkinScore;
    }

    public void setCheckinScore(int checkinScore) {
        this.checkinScore = checkinScore;
    }

    public int getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(int communicationScore) {
        this.communicationScore = communicationScore;
    }

    public int getLocationScore() {
        return locationScore;
    }

    public void setLocationScore(int locationScore) {
        this.locationScore = locationScore;
    }

    public int getValueScore() {
        return valueScore;
    }

    public void setValueScore(int valueScore) {
        this.valueScore = valueScore;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }
}
