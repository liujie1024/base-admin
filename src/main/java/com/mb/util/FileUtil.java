package com.mb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;

public class FileUtil {

	private static final String SPECIAL_CHAR_A = "[^\"\t\\n 　]";

	private static final String SPECIAL_CHAR_B = "[^\"\t\\n]";

	public static List<String[]> readCsvFile(File csvFile) throws FileNotFoundException, IOException {
		List<String[]> list = new ArrayList<String[]>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(csvFile);
			bufferedReader = new BufferedReader(fileReader);
			String regExp = getRegExp();
			String strLine = "";
			String str = "";
			while ((strLine = bufferedReader.readLine()) != null) {
				Pattern pattern = Pattern.compile(regExp);
				Matcher matcher = pattern.matcher(strLine);
				List<String> listTemp = new ArrayList<String>();
				boolean iscan = false;
				while (iscan = matcher.find()) {
					str = matcher.group();
					str = str.trim();
					if (str.endsWith("\t")) {
						str = str.substring(0, str.length() - 1);
						str = str.trim();
					}
					if (str.startsWith("\"") && str.endsWith("\"")) {
						str = str.substring(1, str.length() - 1);
						if (isExisted("\"\"", str)) {
							str = str.replaceAll("\"\"", "\"");
						}
					}
					if (!"".equals(str)) {
						listTemp.add(str);
					}
				}
				if (listTemp.size() > 0)
					list.add(listTemp.toArray(new String[listTemp.size()]));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return list;
	}

	/**
	 * 方法描述：正则表达式。
	 * 
	 * @return 匹配csv文件里最小单位的正则表达式。
	 */
	private static String getRegExp() {
		StringBuffer strRegExps = new StringBuffer();
		strRegExps.append("\"((");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*[,\\n 　])*(");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"{2})*)*");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"[ 　]*,[ 　]*");
		strRegExps.append("|");
		strRegExps.append(SPECIAL_CHAR_B);
		strRegExps.append("*[ 　]*,[ 　]*");
		strRegExps.append("|\"((");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*[,\\n 　])*(");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"{2})*)*");
		strRegExps.append(SPECIAL_CHAR_A);
		strRegExps.append("*\"[ 　]*");
		strRegExps.append("|");
		strRegExps.append(SPECIAL_CHAR_B);
		strRegExps.append("*[ 　]*");
		return strRegExps.toString();
	}

	/**
	 * 是否存在
	 * 
	 * @param argChar
	 * @param argStr
	 * @return
	 */
	private static boolean isExisted(String argChar, String argStr) {
		boolean blnReturnValue = false;
		if ((argStr.indexOf(argChar) >= 0) && (argStr.indexOf(argChar) <= argStr.length())) {
			blnReturnValue = true;
		}
		return blnReturnValue;
	}

	/**
	 * 读取Excel
	 * 
	 * @param filePath
	 */
	public static void readExcel(String filePath) {
		String data2 = "";
		// DateTime tem=new DateTime("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			InputStream is = new FileInputStream(filePath);
			Workbook rwb = Workbook.getWorkbook(is);
			// Sheet st = rwb.getSheet("0")这里有两种方法获取sheet表,1为名字，而为下标，从0开始
			Sheet st = rwb.getSheet("Sheet1");
			int rs = st.getColumns();
			int rows = st.getRows();
			System.out.println("列数===>" + rs + "行数：" + rows);
			List list = new ArrayList<>();
			for (int k = 0; k < rows; k++) {// 行
				for (int i = 0; i < rs; i++) {// 列

					Cell c00 = st.getCell(i, k);
					// 通用的获取cell值的方式,返回字符串
					String strc00 = c00.getContents();
					// 获得cell具体类型值的方式
					if (c00.getType() == CellType.LABEL) {
						LabelCell labelc00 = (LabelCell) c00;
						strc00 = labelc00.getString();
					}
					// excel 类型为时间类型处理;
					if (c00.getType() == CellType.DATE) {
						DateCell dc = (DateCell) c00;
						strc00 = sdf.format(dc.getDate());

					}
					// excel 类型为数值类型处理;
					/*
					 * if(c00.getType()==CellType.NUMBER||
					 * c00.getType()==CellType.NUMBER_FORMULA){ NumberCell
					 * nc=(NumberCell)c00; strc00=""+nc.getValue(); }
					 */

					// 输出
					System.out.println(">" + strc00);

					list.add(strc00);

					// 列，行
					// data2=String.valueOf(st.getCell(1,k).getContents());
					// data2=data2.replace("/", "-");
					// java.util.Date dt=sdf.parse(data2);
					// System.out.println(sdf.format(dt));
					//
				}
				System.out.println(data2 + "======" + list.get(k) + "=========");
			}

			// 关闭
			rwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		readCsvFile(new File("E://test.xlsx"));
	}

}
