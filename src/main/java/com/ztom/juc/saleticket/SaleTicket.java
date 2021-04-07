package com.ztom.juc.saleticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 *
 * @param args
 * @throws Exception
 * @contant 1 高内聚低耦合的前提下,线程() 操作(对外暴露的调用方法) 资源类
 */
public class SaleTicket {

	public static void main(String[] args) throws Exception {
		Ticket ticket = new Ticket();
		new Thread(() -> {
			for (int i = 0; i < 40; i++)
				ticket.saleTicket();
		}, "A").start();
		new Thread(() -> {
			for (int i = 0; i < 40; i++)
				ticket.saleTicket();
		}, "B").start();
		new Thread(() -> {
			for (int i = 0; i < 40; i++)
				ticket.saleTicket();
		}, "A").start();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for(int i =0;i<40;i++) {
//					ticket.saleTicket();
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}, "AA").start();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for(int i =0;i<40;i++) {
//					ticket.saleTicket();
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}, "BB").start();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for(int i =0;i<40;i++) {
//					ticket.saleTicket();
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}, "CC").start();

	}

}

class Take implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 40; i++) {

		}
	}

}

class Ticket {
	private int number = 30;
	private Lock lock = new ReentrantLock();

	public void saleTicket() {
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "\t 卖出了：" + number);
				number--;
			}
		} finally {
			lock.unlock();
		}

	}

//	public synchronized void saleTicket() {
//		if(number > 0) {
//			System.out.println(Thread.currentThread().getName() + "\t 卖出了：" + number );
//			number--;
//		}
//	}
}
