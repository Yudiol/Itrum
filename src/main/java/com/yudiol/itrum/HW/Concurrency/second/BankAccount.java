package com.yudiol.itrum.HW.Concurrency.second;

public class BankAccount {

    private Integer id;
    private Integer amount;

    public BankAccount(Integer id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public void deposit(Integer amount) {
        this.amount += amount;
    }

    public void withdraw(Integer amount) {
        this.amount -= amount;
    }

    public Integer getBalance() {
        return this.amount;
    }

    public Integer getId() {
        return id;
    }
}
