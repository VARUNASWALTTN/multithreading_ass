package com.ass2.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ass_q2 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // ExecutorService executorService = Executors.newFixedThreadPool(5);

        //ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5 ; i++) {
            executorService.submit(() -> {
                System.out.println("Doing task");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Task completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}