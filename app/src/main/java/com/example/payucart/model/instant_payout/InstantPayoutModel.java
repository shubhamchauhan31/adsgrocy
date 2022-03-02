package com.example.payucart.model.instant_payout;

public class InstantPayoutModel {
    private String instantPayoutWeekly;
    private String instantPayoutDate;
    private String instantPayoutPrice;

    public InstantPayoutModel(String instantPayoutWeekly, String instantPayoutDate, String instantPayoutPrice) {
        this.instantPayoutWeekly = instantPayoutWeekly;
        this.instantPayoutDate = instantPayoutDate;
        this.instantPayoutPrice = instantPayoutPrice;
    }

    public String getInstantPayoutWeekly() {
        return instantPayoutWeekly;
    }

    public void setInstantPayoutWeekly(String instantPayoutWeekly) {
        this.instantPayoutWeekly = instantPayoutWeekly;
    }

    public String getInstantPayoutDate() {
        return instantPayoutDate;
    }

    public void setInstantPayoutDate(String instantPayoutDate) {
        this.instantPayoutDate = instantPayoutDate;
    }

    public String getInstantPayoutPrice() {
        return instantPayoutPrice;
    }

    public void setInstantPayoutPrice(String instantPayoutPrice) {
        this.instantPayoutPrice = instantPayoutPrice;
    }

    @Override
    public String toString() {
        return "InstantPayoutModel{" +
                "instantPayoutWeekly='" + instantPayoutWeekly + '\'' +
                ", instantPayoutDate='" + instantPayoutDate + '\'' +
                ", instantPayoutPrice='" + instantPayoutPrice + '\'' +
                '}';
    }
}
