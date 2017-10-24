package com.benyamephrem.model.leafs;

/*
Class made to encapsulate price and cost data about the property
 */
public class ListingFinancials {

    private double dailyPrice; //Price to stay per day
    private double weeklyPrice; //Price to stay per week
    private double monthlyPrice; //Price to stay per month
    private double cleaningFee; //Cleaning Fee charged to customer
    private double extraPeopleFee; //Fee for having more than (maxAccommodates + maxGuests)
    private double securityDeposit; //Security Deposit required

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public double getWeeklyPrice() {
        return weeklyPrice;
    }

    public void setWeeklyPrice(double weeklyPrice) {
        this.weeklyPrice = weeklyPrice;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public double getCleaningFee() {
        return cleaningFee;
    }

    public void setCleaningFee(double cleaningFee) {
        this.cleaningFee = cleaningFee;
    }

    public double getExtraPeopleFee() {
        return extraPeopleFee;
    }

    public void setExtraPeopleFee(double extraPeopleFee) {
        this.extraPeopleFee = extraPeopleFee;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }
}
