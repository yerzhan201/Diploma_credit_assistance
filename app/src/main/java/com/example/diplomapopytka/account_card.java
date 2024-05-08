package com.example.diplomapopytka;

import android.graphics.Color;
import android.view.View;

public class account_card {
    private String bankName;
    private String balance;
    private String color;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public account_card(int id,String bankName, String balance, String color) {
        this.id = id;
        this.bankName = bankName;
        this.balance = balance;
        this.color = color;
    }
    public void setBackgroundColor(View view) {
        view.setBackgroundColor(Color.parseColor(String.valueOf(color)));
    }

    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBalance() { return balance; }
    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}