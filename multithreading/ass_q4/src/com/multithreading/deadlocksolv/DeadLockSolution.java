package com.multithreading.deadlocksolv;

import java.sql.SQLOutput;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockSolution {
    public static void main(String[] args) throws InterruptedException {
        DeadLockSolution deadLockSolution = new DeadLockSolution();
        Account account1 = new Account("A1", 100);
        Account account2 = new Account("B2", 200);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                DeadLockSolution.lock(account1, account2);
                DeadLockSolution.transfer(account1, account2, 1);
                DeadLockSolution.unLock(account1, account2);
            }
        });
//        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 5; i++) {
//                DeadLockSolution.lock(account2, account1);
//                DeadLockSolution.transfer(account2, account1, 1);
//                DeadLockSolution.unLock(account2, account1);
//            }
//        });
        t1.start();
       // t2.start();
        t1.join();
        //t2.join();
        System.out.println("Balance in 1 account : " + (account1.getAmount()));
        System.out.println("Balance in 2 account : " + (account2.getAmount()));
    }

    private static void transfer(Account fromAccount, Account toAccount, Integer amount) {
        System.out.println("Transferring amount: " + amount + " from account: " + fromAccount.getAccountNumber() + " to account: " + toAccount.getAccountNumber());
        toAccount.setAmount(toAccount.getAmount() - amount);
        fromAccount.setAmount(fromAccount.getAmount() + amount);
    }

    private static void lock(Account fromAccount, Account toAccount) {
        while (true) {
            Boolean fromAccountLocked = fromAccount.getLock().tryLock();
            Boolean toAccountLocked = toAccount.getLock().tryLock();
            if (fromAccountLocked && toAccountLocked) {
                return;
            }
            if(fromAccountLocked) {
                fromAccount.getLock().unlock();
            }
            if(toAccountLocked) {
                toAccount.getLock().unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void unLock(Account fromAccount, Account toAccount) {
        fromAccount.getLock().unlock();
        toAccount.getLock().unlock();
    }
}

class Account {
    private final String accountNumber;
    private Integer amount;
    private ReentrantLock lock;

    public Account(String accountNumber, Integer amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.lock = new ReentrantLock();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}