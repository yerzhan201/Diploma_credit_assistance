package com.example.diplomapopytka;

public class Transaction {
    private String sum;
    private String date;
    private String category;
    private String type;

    public Transaction(String sum,String category, String type) {
        this.sum = sum;
        //this.date = date;
        this.category = category;
        this.type = type;
    }

    // Getters
    public String getSum() {
        return sum;
    }



    public String getCategory() {
        return category;
    }
    public String getType() {
        return type;
    }

    // Setters
    public void setSum(String sum) {
        this.sum = sum;
    }



    public void setCategory(String category) {
        this.category = category;
    }
    public void setType(String type) {
        this.type = type;
    }
}
