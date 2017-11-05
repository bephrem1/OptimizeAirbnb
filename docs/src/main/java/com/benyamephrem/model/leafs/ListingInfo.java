package com.benyamephrem.model.leafs;

import com.benyamephrem.model.Listing;

import java.util.Date;

/*
This class is made to represent general information about the listing like description and listing summary
 */

public class ListingInfo {

    private String listingUrl; //The listing's url
    private String hostUrl; //The host's profile url
    private String title; //The listing's title
    private String summary; //A short summary about the listing
    private String space; //A short summary about the space the listed property has
    private String description; //A longer description about the listing in more detail
    private String neighborhoodOverview; //A quick overview on the neighborhood
    private String hostName; //Name of the host
    private String hostSince; //The host has been a member of Airbnb since this date
    private String hostAbout; //Short description about the host
    private String superhost; //Is this host a superhost t == true f == false
    private int hostListingCount; //Amount of listings the host has

    public ListingInfo(){
    }

    public ListingInfo(String listingUrl, String hostUrl, String title, String summary,
                       String space, String description, String neighborhoodOverview,
                       String hostName, String hostSince, String hostAbout, String superhost,
                       int hostListingCount) {
        this.listingUrl = listingUrl;
        this.hostUrl = hostUrl;
        this.title = title;
        this.summary = summary;
        this.space = space;
        this.description = description;
        this.neighborhoodOverview = neighborhoodOverview;
        this.hostName = hostName;
        this.hostSince = hostSince;
        this.hostAbout = hostAbout;
        this.superhost = superhost;
        this.hostListingCount = hostListingCount;
    }

    public String getListingUrl() {
        return listingUrl;
    }

    public void setListingUrl(String listingUrl) {
        this.listingUrl = listingUrl;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNeighborhoodOverview() {
        return neighborhoodOverview;
    }

    public void setNeighborhoodOverview(String neighborhoodOverview) {
        this.neighborhoodOverview = neighborhoodOverview;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostSince() {
        return hostSince;
    }

    public void setHostSince(String hostSince) {
        this.hostSince = hostSince;
    }

    public String getHostAbout() {
        return hostAbout;
    }

    public void setHostAbout(String hostAbout) {
        this.hostAbout = hostAbout;
    }

    public String getSuperhost() {
        return superhost;
    }

    public void setSuperhost(String superhost) {
        this.superhost = superhost;
    }

    public int getHostListingCount() {
        return hostListingCount;
    }

    public void setHostListingCount(int hostListingCount) {
        this.hostListingCount = hostListingCount;
    }
}
