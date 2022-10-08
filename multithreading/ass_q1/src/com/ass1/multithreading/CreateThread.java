package com.ass1.multithreading;

public class CreateThread {
    public static void main(String[] args) {
        //Create Thread using Thread Class
        new ThreadClass().start();

        //Create Thread using Runnable
        Thread t1 = new Thread(new ThreadClass2(), "t1");
        t1.start();
        //start second thread after waiting for 2 seconds or if it's dead
        try {
            t1.join(2000);
            System.out.println("thread after join");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadClass extends Thread {
    @Override
    public void run() {
        System.out.println("Thread class");
        try {
            Thread.sleep(1000);
            System.out.println("thread after sleep");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ThreadClass2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread Runnable");

    }
}