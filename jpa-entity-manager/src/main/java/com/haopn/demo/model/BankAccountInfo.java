package com.haopn.demo.model;

public class BankAccountInfo {
    private Integer id;
    private String fullName;
    private double balance;

    public BankAccountInfo() {

    }

    public BankAccountInfo(Integer id, String fullName, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
