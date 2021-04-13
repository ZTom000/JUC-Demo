package com.ztom.juc.completablefuturedemo;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "没有返回值, update mysql ok");
//        });
//
//        completableFuture.get();

        // 异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
//            int num = 10/0;
            return 1024;
        });

        completableFuture2.whenComplete((t, u) -> {
            System.out.println("****t: " + t);
            System.out.println("****u: " + u);
        }).exceptionally(f -> {
            System.out.println("***excption: " + f.getMessage());
            return 4444;
        }).get();
    }
}
