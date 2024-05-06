package com.example.diplomapopytka;

// Loan.java
public class Loan {
    private String loanName;
    private String bankName;
    private String loanDate;
    private String monthlyPayment;
    private String loanAmount;
    private String loanTerm;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Constructor
    public Loan(int id,String loanName, String bankName, String loanDate, String monthlyPayment, String loanAmount, String loanTerm) {
        this.id = id;
        this.loanName = loanName;
        this.bankName = bankName;
        this.loanDate = loanDate;
        this.monthlyPayment = monthlyPayment;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
    }

    // Getters
    public String getLoanName() {
        return loanName;
    }
    public String getBankName() {
        return bankName;
    }
    public String getloanDate() {
        return loanDate;
    }
    public String getmonthlyPayment() {
        return monthlyPayment;
    }
    public String getloanTerm() {
        return loanTerm;
    }
    public String getloanAmount() {
        return loanAmount;
    }





    // Add similar getters for the other fields
}

