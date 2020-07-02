package com.mb.test.model.action.subject;

/**
 * 抽象观察者
 */
public interface Watcher {

	/**
	 * 观察者更新
	 * 
	 * @param str
	 */
	public void update(String str);

}
