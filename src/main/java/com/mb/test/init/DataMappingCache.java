package com.mb.test.init;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class DataMappingCache {

	private static ConcurrentMap<String, Object> CACHE = new ConcurrentHashMap<String, Object>();

	@PostConstruct
	private void init() {
		load();
	}

	public void load() {
		if (!CACHE.isEmpty()) {
			return;
		}
		for (int i = 0; i < 10; i++) {
			CACHE.put("key--" + i, i + "==value");
		}
	}

	public void reload() {
		CACHE.clear();
		load();
	}

	public Map<String, Object> getCache() {
		return CACHE;
	}

	public Object getObjByKey(String key) {
		return CACHE.get(key);
	}

	@PreDestroy
	public void destory() {
		CACHE.clear();
	}

}