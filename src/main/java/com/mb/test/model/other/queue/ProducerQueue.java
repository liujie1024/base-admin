package com.mb.test.model.other.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者
 */
public class ProducerQueue implements Runnable {

	private final BlockingQueue<Object> proQueue;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("生产者生产的苹果编号为 : " + i); // 放入十个苹果编号 为1到10
				proQueue.put(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ProducerQueue(BlockingQueue<Object> proQueue) {
		this.proQueue = proQueue;
	}

}