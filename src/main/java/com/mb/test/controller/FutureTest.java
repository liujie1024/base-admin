package com.mb.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureTest {

	private final static int total = 1000000;

	private final static int size = 100000;

	private void testThread() {
		long start = System.currentTimeMillis();
		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			dataList.add(i + "--");
		}
		ExecutorService threadPool = Executors.newCachedThreadPool();
		CompletionService<List<String>> cs = new ExecutorCompletionService<List<String>>(threadPool);

		for (int i = 0; i < 10; i++) {
			List<String> tempDataList = dataList.subList(i * size, (i + 1) * size);
			cs.submit(new MyCallable(i, tempDataList));
		}
		// 可能做一些事情
		List<String> rrrrdataList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			try {
				List<String> result = cs.take().get(); // CompletionService.take()返回Future
				rrrrdataList.addAll(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		// for (String string : rrrrdataList) {
		// System.out.println(string);
		// }
		long end = System.currentTimeMillis();
		System.out.println("多线程执行时间：" + (end - start) + ",条数：" + rrrrdataList.size());
	}

	private void testThread1() {
		long start = System.currentTimeMillis();
		List<String> dataList = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			dataList.add(i + "--");
		}
		List<String> rtnList = new ArrayList<>();
		for (int i = 0; i < dataList.size(); i++) {
			rtnList.add(dataList.get(i) + "------");
		}
		long end = System.currentTimeMillis();
		System.out.println("顺序执行时间：" + (end - start) + ",条数：" + rtnList.size());
	}

	private void testThread2() {
		long start = System.currentTimeMillis();
		Thread tt = new Thread(new MyRunable());
		tt.start();
		long end = System.currentTimeMillis();
		System.out.println("顺序执行时间：" + (end - start));
	}

	public static void main(String[] args) {
		FutureTest ft = new FutureTest();
		ft.testThread1();
		ft.testThread();
		ft.testThread2();
	}
}

class MyCallable implements Callable<List<String>> {

	private Integer taskId;

	private List<String> dataList;

	public MyCallable(Integer taskId, List<String> dataList) {
		super();
		this.taskId = taskId;
		this.dataList = dataList;
	}

	@Override
	public List<String> call() throws Exception {
		List<String> rtnList = new ArrayList<>();
		for (int i = 0; i < dataList.size(); i++) {
			rtnList.add(taskId + "----" + dataList.get(i));
		}
		return rtnList;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public List<String> getDataList() {
		return dataList;
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}
}

class MyRunable implements Runnable {

	@Override
	public void run() {
		System.out.println("--------");
	}

}
