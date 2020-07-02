package com.mb.test.model.action.subject;

/**
 * 观察者模式 它包含四种角色： （1）抽象主题角色（Subject）：抽象主题提供一个接口，包含增加、移除和通知观察者的方法。
 * （2）具体主题角色（ConcreteSubject）：具体主题是被观察者所观察的对象，它包含主题所有的观察者列表，是抽象主题的具体实现。
 * 当主题状态发生改变时，主动通知观察者。 （3）抽象观察者（Watcher）：是具体观察者的抽象接口，它包含更新观察者的方法。
 * （4）具体观察者（ConcreteWatcher）：实现抽象观察者接口，实现提供更新其自身的方法。
 */
public interface Subject {
	
	/**
	 * 添加观察者
	 * 
	 * @param watcher
	 */
	public void addWatcher(Watcher watcher);

	/**
	 * 移除观察者
	 * 
	 * @param watcher
	 */
	public void removeWatcher(Watcher watcher);

	/**
	 * 通知观察者
	 * 
	 * @param str
	 */
	public void notifyWatcher(String str);
	
}
