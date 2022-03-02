package com.example.payucart.model.addmoney;

public class AddMoneyModel {
    private int bankImage;
    private String bankUserName;
    private String bankUserAccountNumber;
    private String bankDebAcc;

    public AddMoneyModel(int bankImage, String bankUserName, String bankUserAccountNumber, String bankDebAcc) {
        this.bankImage = bankImage;
        this.bankUserName = bankUserName;
        this.bankUserAccountNumber = bankUserAccountNumber;
        this.bankDebAcc = bankDebAcc;
    }

    public int getBankImage() {
        return bankImage;
    }

    public void setBankImage(int bankImage) {
        this.bankImage = bankImage;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankUserAccountNumber() {
        return bankUserAccountNumber;
    }

    public void setBankUserAccountNumber(String bankUserAccountNumber) {
        this.bankUserAccountNumber = bankUserAccountNumber;
    }

    public String getBankDebAcc() {
        return bankDebAcc;
    }

    public void setBankDebAcc(String bankDebAcc) {
        this.bankDebAcc = bankDebAcc;
    }

    @Override
    public String toString() {
        return "AddMoneyModel{" +
                "bankImage=" + bankImage +
                ", bankUserName='" + bankUserName + '\'' +
                ", bankUserAccountNumber='" + bankUserAccountNumber + '\'' +
                ", bankDebAcc='" + bankDebAcc + '\'' +
                '}';
    }
}
