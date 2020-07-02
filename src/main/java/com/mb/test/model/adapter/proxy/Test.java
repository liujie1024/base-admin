package com.mb.test.model.adapter.proxy;

/**
 * 代理模式 <br />
 * 
 * 替原对象进行一些操作
 */
public class Test {

	public static void main(String[] args) {
		Sourceable source = new Proxy();
		source.method();
	}

}
