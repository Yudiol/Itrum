package com.yudiol.itrum.HW.Concurrency.second;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(500);
        BankAccount account2 = bank.createAccount(1000);

//        Thread transferThread111 = new Thread(() -> bank.transfer(account1, account2, 1));
//        ExecutorService executorService1 = Executors.newFixedThreadPool(100);
//        ExecutorService executorService2 = Executors.newFixedThreadPool(100);
//        for (int i = 0; i < 1000; i++) {
//            executorService1.execute(() -> bank.transfer(account1, account2, 100));
//        }
//
//        Thread transferThread222 = new Thread(() -> bank.transfer(account2, account1, 1));
//        for (int i = 0; i < 1000; i++) {
//            executorService2.execute(() -> bank.transfer(account2, account1, 100));
//        }
//
//        executorService1.shutdown();
//        executorService2.shutdown();

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 100));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));
        Thread transferThread3 = new Thread(() -> bank.transfer(account1, account2, 100));
        Thread transferThread4 = new Thread(() -> bank.transfer(account2, account1, 100));
        Thread transferThread13 = new Thread(() -> bank.transfer(account1, account2, 100));
        Thread transferThread14 = new Thread(() -> bank.transfer(account2, account1, 100));
        Thread transferThread5 = new Thread(() -> bank.transfer(account1, account2, 100));
        Thread transferThread6 = new Thread(() -> bank.transfer(account2, account1, 100));
        Thread transferThread7 = new Thread(() -> bank.transfer(account1, account2, 100));
        Thread transferThread8 = new Thread(() -> bank.transfer(account2, account1, 100));
        Thread transferThread9 = new Thread(() -> bank.transfer(account1, account2, 100));
        Thread transferThread10 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();
        transferThread3.start();
        transferThread4.start();
        transferThread13.start();
        transferThread14.start();
        transferThread5.start();
        transferThread6.start();
        transferThread7.start();
        transferThread8.start();
        transferThread9.start();
        transferThread10.start();

        try {
            transferThread1.join();
            transferThread2.join();
            transferThread3.join();
            transferThread4.join();
            transferThread5.join();
            transferThread6.join();
            transferThread7.join();
            transferThread8.join();
            transferThread9.join();
            transferThread10.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод общего баланса
        System.out.println("account1 " + account1.getBalance() + " account2 " + account2.getBalance());
        System.out.println("Total balance: " + bank.getTotalBalance());
    }


}
