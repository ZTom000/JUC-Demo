package com.ztom.juc.callabledemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * java 多线程中第三种获得多线程的方式
 * 1 一般get方法请放在最后一行
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {

        FutureTask futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();

        System.out.println(Thread.currentThread().getName() + " *****Finish");

        System.out.println(futureTask.get()); // 阻塞
    }

}

//class MyThread implements Runnable {
//
//	@Override
//	public void run() {
//
//		
//	}
//
//}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("******* Come in here");
        // 暂停线程
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }

}