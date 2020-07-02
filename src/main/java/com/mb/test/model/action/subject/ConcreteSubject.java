package com.mb.test.model.action.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色
 */
public class ConcreteSubject implements Subject {

	// 主题的观察者列表
	private List<Watcher> list = new ArrayList<Watcher>();

	@Override
	public void addWatcher(Watcher watcher) {
		list.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		list.remove(watcher);
	}

	@Override
	public void notifyWatcher(String str) {
		for (Watcher watcher : list) {
			watcher.update(str);
		}
	}
}
