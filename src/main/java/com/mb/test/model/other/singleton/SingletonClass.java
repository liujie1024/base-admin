package com.mb.test.model.other.singleton;

/**
 * 单例的使用
 */
public class SingletonClass {

	private static class SingletonClassInstance {
		private static SingletonClass instance = new SingletonClass();
	}

	public static SingletonClass getInstance() {
		return SingletonClassInstance.instance;
	}

	private SingletonClass() {

	}

}