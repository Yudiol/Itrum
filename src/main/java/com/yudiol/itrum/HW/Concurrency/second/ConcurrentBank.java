package com.yudiol.itrum.HW.Concurrency.second;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {

    Map<Integer, BankAccount> accounts = new ConcurrentHashMap<>();
    AtomicInteger id = new AtomicInteger();

    public synchronized BankAccount createAccount(Integer amount) {
        BankAccount bankAccount = new BankAccount(id.incrementAndGet(), amount);
        accounts.put(id.get(), bankAccount);
        return bankAccount;
    }

    private void transaction(BankAccount fromAccount, BankAccount toAccount, Integer amount) {
        if (fromAccount.getBalance() <= amount)
            System.out.println("На счету не достаточно средств");
        else {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }
    }


    public void transfer(BankAccount fromAccount, BankAccount toAccount, Integer amount) {
        if (fromAccount.getId() < toAccount.getId()) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    transaction(fromAccount, toAccount, amount);
                }
            }
        } else {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    transaction(fromAccount, toAccount, amount);
                }
            }
        }
    }


    public Integer getTotalBalance() {
        return accounts.values().stream().map(e -> e.getBalance()).reduce(Integer::sum).orElse(0);
    }
}
