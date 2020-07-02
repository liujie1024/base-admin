package com.mb.test.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ThreadController {

	public void gteImportData() throws Exception {
		String filePath = "E://加盟test.xlsx";
		FileInputStream is = new FileInputStream(filePath);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		if (xssfWorkbook != null) {
			XSSFSheet xssfFSheet = xssfWorkbook.getSheetAt(0);
			this.dealData4Thread1(xssfFSheet);
		}
	}

	public void dealData4Thread1(XSSFSheet xssfFSheet) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		FutureTask<List<String>> future = new FutureTask<List<String>>(new Callable<List<String>>() {// 使用Callable接口作为构造参数
			public List<String> call() {
				// 真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
				List<String> rtnList = new ArrayList<>();
				for (int i = 0; i < 100; i++) {
					rtnList.add(i + "**");
				}
				return rtnList;
			}
		});
		for (int i = 0; i <2; i++) {
			executor.execute(future);
		}
		
		// 在这里可以做别的任何事情
		try {
			List<String> result = future.get(); // 取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
			for (String string : result) {
				System.out.println(string);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}

	}

	public void dealData4Thread(XSSFSheet xssfFSheet) {
		int importSize = xssfFSheet.getLastRowNum();
		int maxLen = 10000;// 分成小任务，10000一个list
		int threadSize = importSize % maxLen > 0 ? importSize / maxLen + 1 : importSize / maxLen;

		ExecutorService exec = Executors.newFixedThreadPool(2);
		List<Future<List<String>>> results = new ArrayList<Future<List<String>>>();
		for (int i = 0; i < threadSize; i++) {
			int start = i * maxLen;
			int end = (i + 1) * maxLen;
			if (end > importSize) {
				end = importSize;
			}
			List<XSSFRow> sheetList = new ArrayList<XSSFRow>();
			for (int j = start; j < end; j++) {
				sheetList.add(xssfFSheet.getRow(j));
			}
			results.add(exec.submit(new MyRunnable(sheetList)));
		}
		List<String> strList = new ArrayList<>();
		for (Future<List<String>> fs : results) {
			try {
				strList.addAll(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		long statr = System.currentTimeMillis();
		ThreadController tc = new ThreadController();
		tc.gteImportData();
		long end = System.currentTimeMillis();
		System.out.println("执行时间为：" + (end - statr));
	}

}

class MyRunnable implements Callable<List<String>> {

	private List<XSSFRow> importList;

	public MyRunnable(List<XSSFRow> importList) {
		this.importList = importList;
	}

	List<String> rtnList = new ArrayList<String>();

	@Override
	public List<String> call() throws Exception {
		for (int i = 0; i < importList.size(); i++) {
			System.out.println(i + "---" + importList.get(i).getCell(3).toString());
			rtnList.add(importList.get(i).getCell(4).toString());
		}
		return rtnList;
	}
}
