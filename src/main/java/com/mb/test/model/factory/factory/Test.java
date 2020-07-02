package com.mb.test.model.factory.factory;

/**
 * 普通的工厂类，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建
 */
public class Test {

	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
	}

}
