package com.mb.test;

import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class Test {

	public static void main(String[] args) {
		// 双key map
		Table<String, String, Integer> tables = HashBasedTable.create();
		tables.put("a", "javase", 80);
		tables.put("b", "javaee", 90);
		tables.put("c", "javame", 100);
		tables.put("d", "guava", 70);
		Set<Cell<String, String, Integer>> tableSet = tables.cellSet();
		for (Cell<String, String, Integer> cell : tableSet) {
			System.out.println(cell.getRowKey() + "---" + cell.getColumnKey() + "---" + cell.getValue());
		}
	}

}
