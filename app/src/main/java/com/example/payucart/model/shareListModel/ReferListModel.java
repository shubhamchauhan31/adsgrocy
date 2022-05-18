package com.example.payucart.model.shareListModel;

import java.lang.ref.PhantomReference;

public class ReferListModel {
    private int referListImg;
    private String referListName;
    private String referListPrice;
    private boolean expandable;


    public ReferListModel(String referListName, String referListPrice) {
        this.referListName = referListName;
        this.referListPrice = referListPrice;
        this.expandable=false;

    }
    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public int getReferListImg() {
        return referListImg;
    }

    public void setReferListImg(int referListImg) {
        this.referListImg = referListImg;
    }

    public String getReferListName() {
        return referListName;
    }

    public void setReferListName(String referListName) {
        this.referListName = referListName;
    }

    public String getReferListPrice() {
        return referListPrice;
    }

    public void setReferListPrice(String referListPrice) {
        this.referListPrice = referListPrice;
    }
}
