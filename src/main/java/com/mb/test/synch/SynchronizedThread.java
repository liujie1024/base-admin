package com.mb.test.synch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedThread {

	public void useThread() throws InterruptedException {
		final Bank bank = new Bank();
		NewThread new_thread = new NewThread(bank);

		System.out.println("线程1");
		Thread thread1 = new Thread(new_thread);
		thread1.start();

		System.out.println("线程2");
		Thread thread2 = new Thread(new_thread);
		thread2.start();

		thread1.join();
		thread2.join();
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizedThread st = new SynchronizedThread();
		st.useThread();
	}

}

class Bank {

	private int account = 100;

	public int getAccount() {
		return account;
	}

	public void save(int money) {
		account += money;
	}
}

class NewThread implements Runnable {

	private Bank bank;

	private static final Lock lock1 = new ReentrantLock();

	public NewThread(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lock1.lock();
			try {
				bank.save(10);
				System.out.println(i + "账户余额为：" + bank.getAccount() + " 线程名称：" + Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock1.unlock();
			}
		}
	}
}
