package com.example.diplomapopytka;

public class Transaction {
    private String sum;
    private int id;
    private String transactionName;
    private String date;
    private String category;
    private String type;

    public Transaction(int id,String transactionName, String sum,String category, String type) {
        this.sum = sum;
        this.id = id;
        //this.date = date;
        this.transactionName = transactionName;
        //this.date = date;
        this.category = category;
        this.type = type;
    }

    // Getters
    public String getSum() {
        return sum;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() { // New getter for transaction name
        return transactionName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) { // New setter for transaction name
        this.transactionName = name;
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
