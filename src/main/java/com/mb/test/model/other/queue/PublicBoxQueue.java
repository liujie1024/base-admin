package com.mb.test.model.other.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者和消费者模式
 */
public class PublicBoxQueue {

	public static void main(String[] args) {
		BlockingQueue<Object> publicBoxQueue = new LinkedBlockingQueue<Object>(5); // 定义了一个大小为5的盒子
		Thread pro = new Thread(new ProducerQueue(publicBoxQueue));
		Thread con = new Thread(new ConsumerQueue(publicBoxQueue));
		pro.start();
		con.start();
	}

}