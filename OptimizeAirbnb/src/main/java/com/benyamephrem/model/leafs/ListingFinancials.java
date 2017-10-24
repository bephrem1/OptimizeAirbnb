package com.benyamephrem.model.leafs;

/*
Class made to encapsulate price and cost data about the property
 */
public class ListingFinancials {

    private Double dailyPrice; //Price to stay per day
    private Double weeklyPrice; //Price to stay per week
    private Double monthlyPrice; //Price to stay per month
    private Double cleaningFee; //Cleaning Fee charged to customer
    private Double extraPeopleFee; //Fee for having more than (maxAccommodates + maxGuests)
    private Double securityDeposit; //Security Deposit required

    public ListingFinancials(){

    }

    public ListingFinancials(Double dailyPrice, Double weeklyPrice, Double monthlyPrice, Double cleaningFee,
                             Double extraPeopleFee, Double securityDeposit) {
        this.dailyPrice = dailyPrice;
        this.weeklyPrice = weeklyPrice;
        this.monthlyPrice = monthlyPrice;
        this.cleaningFee = cleaningFee;
        this.extraPeopleFee = extraPeopleFee;
        this.securityDeposit = securityDeposit;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Double getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(Double weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public Double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public Double getCleaningFee() {
        return cleaningFee;
    }

    public void setCleaningFee(Double cleaningFee) {
        this.cleaningFee = cleaningFee;
    }

    public Double getExtraPeopleFee() {
        return extraPeopleFee;
    }

    public void setExtraPeopleFee(Double extraPeopleFee) {
        this.extraPeopleFee = extraPeopleFee;
    }

    public Double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }
}
