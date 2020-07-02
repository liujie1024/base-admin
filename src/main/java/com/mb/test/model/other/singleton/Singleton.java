package com.mb.test.model.other.singleton;

/**
 * 单类
 */
public class Singleton {

	private volatile static Singleton instance = null;

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

}