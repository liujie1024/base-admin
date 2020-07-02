package com.mb.test.synch;

public class Main {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "主线程运行开始!");
		Thread1 mTh1 = new Thread1("A");
		Thread1 mTh2 = new Thread1("B");
		mTh1.start();
		mTh2.start();
		try {
			mTh1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			mTh2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + "主线程运行结束!");
	}

}
