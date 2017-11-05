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
    private double bathrooms;
    private int bedrooms;
    private int beds;
    private String bedType; //Ex: Real Bed, Airbed

    public PropertyLogistics(){

    }

    public PropertyLogistics(String propertyType, String roomType, int maxAccomodates, int maxGuests,
                             double bathrooms, int bedrooms, int beds, String bedType) {
        this.propertyType = propertyType;
        this.roomType = roomType;
        this.maxAccomodates = maxAccomodates;
        this.maxGuests = maxGuests;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.bedType = bedType;
    }

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

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
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
}
