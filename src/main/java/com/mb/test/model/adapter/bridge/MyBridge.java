package com.mb.test.model.adapter.bridge;

public class MyBridge extends Bridge {
	
	public void method() {
		getSource().method();
	}
	
}