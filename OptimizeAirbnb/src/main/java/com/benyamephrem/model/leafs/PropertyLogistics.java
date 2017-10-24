package com.benyamephrem.model.leafs;

import java.util.List;

/*
Class containing property logistical data like amenities, room types, bathroom count, etc.
 */
public class PropertyLogistics {

    private String propertyType; //Ex: Apartment, House, Condominium
    private String roomType; //Ex: Private Room, Entire Home/Apt
    private int maxAccomodates; //Number of people the property accommodates
    private int maxGuests; //Max guests a person staying can have
    private int bathrooms;
    private int bedrooms;
    private int beds;
    private String bedType; //Ex: Real Bed, Airbed
    private List<String> amenities;

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getMaxAccomodates() {
        return maxAccomodates;
    }

    public void setMaxAccomodates(int maxAccomodates) {
        this.maxAccomodates = maxAccomodates;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}
