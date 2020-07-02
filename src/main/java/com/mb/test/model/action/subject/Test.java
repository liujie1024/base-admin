package com.mb.test.model.action.subject;

/**
 * 观察者模式，测试Main方法
 */
public class Test {

	public static void main(String[] args) {
		Subject sbj = new ConcreteSubject();
		Watcher watcher1 = new ConcreteWatcher();
		Watcher watcher2 = new ConcreteWatcher();
		Watcher watcher3 = new ConcreteWatcher();
		Watcher watcher4 = new ConcreteWatcher();
		sbj.addWatcher(watcher1);
		sbj.addWatcher(watcher2);
		sbj.addWatcher(watcher3);
		sbj.addWatcher(watcher4);
		sbj.notifyWatcher("hello world!");
	}

}
