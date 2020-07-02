package com.mb.test.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FileTest {

	public Map<String, Object> fileMap = new HashMap<String, Object>();

	private void getFileNameAndPathList(File file) {
		if (null != file) {
			if (file.isDirectory()) {
				File[] fileArray = file.listFiles();
				if (fileArray != null && fileArray.length > 0) {
					for (int i = 0; i < fileArray.length; i++) {
						getFileNameAndPathList(fileArray[i]);
					}
				}
			} else {
				fileMap.put(file.getName() + "!~!" + file.getPath(), file.getName());
			}
		}
	}

	public void test(){
		
	}
	
	public static void main(String[] args) {
		String fileName = "E:" + File.separator + "imagestest" + File.separator;
		File f = new File(fileName);
		FileTest ft = new FileTest();
		ft.getFileNameAndPathList(f);
		System.out.println(ft.fileMap.size());
		Set<Entry<String, Object>> fileSet = ft.fileMap.entrySet();
		for (Entry<String, Object> tempFile : fileSet) {
			System.out.println(tempFile.getKey() + "*********************" + tempFile.getValue());
		}
	}

}