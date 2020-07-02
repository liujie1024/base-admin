package com.mb.alibaba;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

public class ExcelWriteTest {

	/**
	 * 每行数据是List<String>无表头
	 * 
	 * @throws IOException
	 */
	public void writeWithoutHead() {
		try {
			OutputStream out = new FileOutputStream("d:\\withoutHead.xlsx");
			ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
			Sheet sheet1 = new Sheet(1, 0);
			sheet1.setSheetName("sheet1");
			List<List<String>> data = new ArrayList<List<String>>();
			for (int i = 0; i < 100; i++) {
				List<String> item = new ArrayList<String>();
				item.add("item0--" + i);
				item.add("item1--" + i);
				item.add("item2--" + i);
				data.add(item);
			}
			writer.write0(data, sheet1);
			writer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExcelWriteTest test = new ExcelWriteTest();
		test.writeWithoutHead();
	}

}
