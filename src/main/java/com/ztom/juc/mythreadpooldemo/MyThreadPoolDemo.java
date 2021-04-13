package com.ztom.juc.mythreadpooldemo;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5); // 1池5个工作线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 1池1个工作线程
//        ExecutorService threadPool = Executors.newCachedThreadPool(); // 1池N个工作线程
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                // TimeUnit.MILLISECONDS.sleep(400);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
