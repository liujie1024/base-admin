package com.mb.test.model.action.subject;

/**
 * 具体的观察者角色
 */
public class ConcreteWatcher implements Watcher {

	@Override
	public void update(String str) {
		System.out.println(str);
	}
	
}
