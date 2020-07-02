package com.mb.test.model.other.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 */
public class ConsumerQueue implements Runnable {

	private final BlockingQueue<Object> conQueue;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("消费者消费的苹果编号为 ：" + conQueue.take());
				Thread.sleep(3000); // 在这里sleep是为了看的更加清楚些
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ConsumerQueue(BlockingQueue<Object> conQueue) {
		this.conQueue = conQueue;
	}

}