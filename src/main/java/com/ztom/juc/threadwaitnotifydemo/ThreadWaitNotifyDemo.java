package com.ztom.juc.threadwaitnotifydemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner {
	private int number = 0;

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException {

		lock.lock();
		try {
			// 判断
			// if (number != 0) 
			while (number != 0){
				//this.wait();
				condition.await();
			}

			// 干活
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);

			// 通知
			// this.notifyAll();
			condition.signalAll();
		}finally {
			lock.unlock();
		}

	}

	public void decrement() throws InterruptedException {
		
		lock.lock();
		try {
			// 判断
			//if (number == 0) 
			while (number == 0){
				//this.wait();
				condition.await();
			}

			// 干活
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);

			// 通知
			// this.notifyAll();
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

//	public synchronized void increment() throws InterruptedException {
//
//		// 判断
//		// if (number != 0) 
//		while (number != 0){
//			this.wait();
//
//		}
//
//		// 干活
//		number++;
//		System.out.println(Thread.currentThread().getName() + "\t" + number);
//
//		// 通知
//		this.notifyAll();
//	}
//
//	public synchronized void decrement() throws InterruptedException {
//
//		// 判断
//		//if (number == 0) 
//		while (number == 0){
//			this.wait();
//
//		}
//
//		// 干活
//		number--;
//		System.out.println(Thread.currentThread().getName() + "\t" + number);
//
//		// 通知
//		this.notifyAll();
//	}
}

/**
 * 题目 两个线程操作一个初始值为0的变量,实现一个线程对该变量+1,一个对该变量-1，实现交替10轮，变量初始值为0 
 * 1 高聚低合前提下,线程操作资源类 
 * 2 判断/干活/通知
 * 3 多线程交互中,必须要防止多线程的虚假唤醒(判断只用while,不能用if)
 * 4 标志位
 */
public class ThreadWaitNotifyDemo {

	public static void main(String[] args) {
		AirConditioner airConditioner = new AirConditioner();

		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					Thread.sleep(500);
					airConditioner.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "A").start();
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					Thread.sleep(500);
					airConditioner.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "B").start();
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					Thread.sleep(500);
					airConditioner.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "C").start();
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					Thread.sleep(500);
					airConditioner.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "D").start();

	}

}
