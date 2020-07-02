package com.mb.importbi.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mb.base.BaseController;
import com.mb.importbi.entity.ErrorMsg;
import com.mb.importbi.entity.ImportFranchiseBi;
import com.mb.importbi.service.IImportFranchiseBiService;
import com.mb.util.CommonUtil;
import com.mb.util.DateUtil;
import com.mb.util.MbConstant;
import com.mb.util.NumberValidationUtil;

/**
 * 加盟导入
 */
@Controller
@RequestMapping("/importfrancheisebi")
public class ImportFranchiseBiController extends BaseController<ImportFranchiseBi> {

	private final static Logger LOGGER = LoggerFactory.getLogger(ImportFranchiseBiController.class);

	public final static List<String> UNSALABLELIST = Arrays.asList("畅", "滞", "普", "未定义"); // 店铺定位不允许为空，字符必须为（畅，滞，普，未定义），要去空格

	@Autowired
	private IImportFranchiseBiService importFranchiseBiService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("ImportFranchiseBiController goto importfranchisebi page");
		return "importbi/importfranchisebi";
	}

	@Override
	public List<ImportFranchiseBi> setPageList(HttpServletRequest request, HttpServletResponse response, ImportFranchiseBi entity) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/upload")
	public Map<String, Object> upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<ImportFranchiseBi> successRowList = null;// 成功的数据list
		List<ErrorMsg> failRowList = null;// 有错误的数据list
		List<String> replaceRowList = null;// 重复的数据list
		long startTime = System.currentTimeMillis();
		try {
			InputStream is = file.getInputStream();
			String fileSizeStr = CommonUtil.getPrintSize(file.getSize());
			LOGGER.info("上传的文件名称：" + file.getOriginalFilename() + "，文件大小：" + fileSizeStr);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			// 取第一个工作簿
			if (null != xssfWorkbook) {
				XSSFSheet xssfFSheet = xssfWorkbook.getSheetAt(0);// 第一个工作簿
				if (null != xssfFSheet) {
					Map<String, Object> returnMap = this.dealExeclData(xssfFSheet);// EXECL数据处理
					successRowList = (List<ImportFranchiseBi>) returnMap.get(MbConstant.SUCCESS);
					failRowList = (List<ErrorMsg>) returnMap.get(MbConstant.FAIL);
					replaceRowList = (List<String>) returnMap.get(MbConstant.REPLACE);
				}
			}
			Integer successLen = successRowList.size();
			Integer failLen = failRowList.size();
			if (successLen > 0 && failLen == 0) {// 只有没有错误的数据，并且成功的数据不为空的情况下，再新增
				this.deleteBatchByParam(replaceRowList);// 先删除重复数据
				this.insertBatch(successRowList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long runTime = (endTime - startTime) / 1000;
		LOGGER.info("数据处理，共花费时间：" + runTime + "秒");
		rtnMap.put("successRowListSize", successRowList.size());// 成功数据的个数
		rtnMap.put("failRowList", failRowList);// 失败数据
		return rtnMap;
	}

	/**
	 * 处理EXECL数据
	 * 
	 * @param startLen
	 *            第几行数据开始读取
	 * @param xssfFSheet
	 *            EXECL工作簿对象
	 * @return
	 */
	private Map<String, Object> dealExeclData(XSSFSheet xssfFSheet) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<ImportFranchiseBi> successRowList = new ArrayList<ImportFranchiseBi>();// 成功的数据list
		List<ErrorMsg> failRowList = new ArrayList<ErrorMsg>();// 有错误的数据list
		List<String> replaceRowList = new ArrayList<String>();// 重复的数据list
		List<String> keyList = new ArrayList<String>();// 唯一key的集合，判断key是否有重复的
		ImportFranchiseBi importFranchiseBi = null;
		StringBuffer failSb = null;// 失败的描述
		String lastSunday = DateUtil.getLastSunday();// 上周日
		List<String> allList = this.getAllData();// 数据库中的所有数据
		// 处理当前页，循环读取每一页
		for (int rowNum = 0; rowNum <= xssfFSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfFSheet.getRow(rowNum);
			if (xssfRow == null) {
				continue;
			}
			if (isContent(xssfRow)) {// 排除说明和表头
				continue;
			}
			// --数据处理-------------------------------------------------------------------------
			boolean isSuccess = true;// 判断这条记录是否合法
			failSb = new StringBuffer();
			importFranchiseBi = new ImportFranchiseBi();
			// 默认上周周日（系统生成）
			importFranchiseBi.setClndr_wk_cd(Double.valueOf(lastSunday));
			// 市场ID
			String mrktCd = xssfRow.getCell(3) == null ? "" : xssfRow.getCell(3).toString().trim();
			if (StringUtils.isNotBlank(mrktCd)) {
				importFranchiseBi.setMrkt_cd(mrktCd);
			} else {
				isSuccess = false;
				failSb.append("市场ID为空；");
			}
			// 8位码
			String enghtCode = CommonUtil.setExeclDate4Type(xssfRow.getCell(5));
			if (StringUtils.isNotBlank(enghtCode) && enghtCode.length() == 8) {
				importFranchiseBi.setItem_cd_8bit(enghtCode);
			} else {
				isSuccess = false;
				failSb.append("8位码为空或长度不对；");
			}
			// 店铺定位
			String shopLocation = xssfRow.getCell(6) == null ? "" : xssfRow.getCell(6).toString().trim();
			if (StringUtils.isNotBlank(shopLocation)) {
				if (UNSALABLELIST.contains(shopLocation)) {
					importFranchiseBi.setIs_sale_unsalable(shopLocation);
				} else {
					isSuccess = false;
					failSb.append("店铺定位有误；");
				}
			} else {
				isSuccess = false;
				failSb.append("店铺定位为空；");
			}
			// 下周销量
			String targetUnitCut = CommonUtil.setExeclDate4Type(xssfRow.getCell(7));
			if (StringUtils.isNotBlank(targetUnitCut)) {
				if (NumberValidationUtil.isPositiveInteger2(targetUnitCut)) {
					importFranchiseBi.setTarget_unit_cnt(Integer.valueOf(targetUnitCut));
				} else {
					isSuccess = false;
					failSb.append("下周销量有非法字符；");
				}
			}
			// 下周折扣
			String suggestDisc = xssfRow.getCell(8) == null ? "" : xssfRow.getCell(8).toString();
			if (StringUtils.isNotBlank(suggestDisc)) {
				if (NumberValidationUtil.isRealNumber(suggestDisc)) {// 判断是否为数字或者小数,如果不为数字
					importFranchiseBi.setSuggest_disc(Double.valueOf(suggestDisc));
				} else {
					isSuccess = false;
					failSb.append("下周折扣含有非法字符；");
				}
			}
			// ---1、判断本次导入的是否含有重复的，门店id+8位码+门店定位；------------------------------------------------------
			String onlyKey = mrktCd + "," + enghtCode + "," + shopLocation;// 唯一key
			if (!keyList.contains(onlyKey)) {
				keyList.add(onlyKey);
			} else {
				isSuccess = false;
				failSb.append("唯一标识有重复；");
			}
			// ---2、判断数据库中是否含有重复的--------------------------------------------------------------------------
			if (allList.contains(onlyKey)) {
				LOGGER.info("该条数据在数据库中重复，EXECL行号：" + String.valueOf(rowNum + 1));
				replaceRowList.add(onlyKey);// 数据库中的重复数据
			}
			// ---判断是否含有错误的----------------------------------------------------------------------------------
			if (isSuccess) {
				LOGGER.info("该条数据正确：EXECL行号：" + String.valueOf(rowNum + 1));
				successRowList.add(importFranchiseBi);
			} else {
				// EXECL的该条行记录
				String tempStr = mrktCd + "," + enghtCode + "," + shopLocation + "," + targetUnitCut + "," + suggestDisc;// 整行数据，逗号分割
				String rowNumStr = String.valueOf(rowNum + 1);// 行号
				failRowList.add(new ErrorMsg(rowNumStr, tempStr, failSb.toString()));
				LOGGER.info("该条数据有误：EXECL行号：" + rowNumStr + "；内容：" + tempStr + ";错误说明：" + failSb.toString());
			}
		}
		rtnMap.put(MbConstant.SUCCESS, successRowList);// 成功数据list
		rtnMap.put(MbConstant.FAIL, failRowList);// 失败数据list
		rtnMap.put(MbConstant.REPLACE, replaceRowList);// 重复数据list
		return rtnMap;
	}

	/**
	 * 查询所有数据库中的所有的数据
	 * 
	 * @return
	 */
	private List<String> getAllData() {
		long start = System.currentTimeMillis();
		List<String> rtnList = new ArrayList<String>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<ImportFranchiseBi> importFranchiseBiList = importFranchiseBiService.getListByParam(paramMap);
		if (null != importFranchiseBiList && importFranchiseBiList.size() > 0) {
			for (ImportFranchiseBi importFranchiseBi : importFranchiseBiList) {
				String shopId = importFranchiseBi.getMrkt_cd();// 市场ID
				String enghtCode = importFranchiseBi.getItem_cd_8bit();// 八位码
				String shopLocation = importFranchiseBi.getIs_sale_unsalable();// 门店定位
				String onlyKey = shopId + "," + enghtCode + "," + shopLocation;// 唯一key
				rtnList.add(onlyKey);
			}
		}
		long end = System.currentTimeMillis();
		LOGGER.info("获得加盟全部数据，执行时间为：" + (end - start) / 1000 + "秒");
		return rtnList;
	}

	/**
	 * 排除该行记录是说明和表头
	 * 
	 * @param xssfRow
	 *            该行数据
	 * @return
	 */
	private boolean isContent(XSSFRow xssfRow) {
		boolean rtn = false;
		String mrktCd = xssfRow.getCell(3) == null ? "" : xssfRow.getCell(3).toString();
		String cd8bit = xssfRow.getCell(5) == null ? "" : xssfRow.getCell(5).toString();
		String location = xssfRow.getCell(6) == null ? "" : xssfRow.getCell(6).toString();
		// 说明
		if (StringUtils.isBlank(mrktCd) && StringUtils.isBlank(cd8bit) && StringUtils.isBlank(location)) {
			rtn = true;
		}
		// 表头
		if ("市场ID".equals(mrktCd) && "8位码".equals(cd8bit) && "下周目标定位".equals(location)) {
			rtn = true;
		}
		return rtn;
	}

	/**
	 * 批量处理数据
	 * 
	 * @param importFranchiseBiList
	 *            处理好的数据
	 */
	private synchronized void insertBatch(List<ImportFranchiseBi> importFranchiseBiList) {
		int maxLen = importFranchiseBiList.size();// 一共多少条数据
		int batchLen = 1000;// 每次提交个数
		int remainder = maxLen % batchLen;// 余数
		int len = remainder > 0 ? (maxLen / batchLen + 1) : (maxLen / batchLen);// 分多少批次执行
		LOGGER.info("*******加盟市场目标导入开始***************************");
		LOGGER.info("导入数据，一共" + maxLen + "条数据，每次执行" + batchLen + "条，分" + len + "个批次导入；");
		for (int i = 0; i < len; i++) {
			Map<String, Object> batchMap = new HashMap<String, Object>();
			Integer statrLen = i * batchLen;
			Integer endLen = (i + 1) * batchLen;
			if (i == (len - 1)) {// 如果最后一个批次的数据不是整数处理
				endLen = maxLen;
			}
			batchMap.put("importFranchiseBiList", importFranchiseBiList.subList(statrLen, endLen));
			importFranchiseBiService.insertBatchByMap(batchMap);
			LOGGER.info("批次" + (i + 1) + "导入成功，开始数据：" + statrLen + "；结束数据：" + endLen);
		}
		LOGGER.info("*******加盟市场目标导入结束***************************");
	}

	/**
	 * 批量删除数据
	 * 
	 * @param replaceRowList
	 */
	private synchronized void deleteBatchByParam(List<String> replaceRowList) {
		int maxLen = replaceRowList.size();// 一共多少条数据
		int batchLen = 1000;// 每次提交个数
		int remainder = maxLen % batchLen;// 余数
		int len = remainder > 0 ? (maxLen / batchLen + 1) : (maxLen / batchLen);// 分多少批次执行
		LOGGER.info("*******加盟市场目标批量重复数据删除开始***************************");
		LOGGER.info("重复数据删除，一共" + maxLen + "条数据，每次执行" + batchLen + "条，分" + len + "个批次删除；");
		for (int i = 0; i < len; i++) {
			Integer statrLen = i * batchLen;
			Integer endLen = (i + 1) * batchLen;
			if (i == (len - 1)) {// 如果最后一个批次的数据不是整数处理
				endLen = maxLen;
			}
			List<String> tempReplaceRowList = replaceRowList.subList(statrLen, endLen);
			importFranchiseBiService.deleteBatchByMap(tempReplaceRowList);
			LOGGER.info("批次" + (i + 1) + "删除成功，开始数据：" + statrLen + "；结束数据：" + endLen);
		}
		LOGGER.info("*******加盟市场目标批量重复数据删除结束***************************");
	}

	/**
	 * 获得全部数据，方法2
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<String> getAllData2() {
		long start1 = System.currentTimeMillis();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<ImportFranchiseBi> importFranchiseBiList = importFranchiseBiService.getListByParam(paramMap);
		int importSize = importFranchiseBiList.size();
		int maxLen = 10000;// 分成小任务，10000一个list
		int threadSize = importSize % maxLen > 0 ? importSize / maxLen + 1 : importSize / maxLen;

		ExecutorService exec = Executors.newCachedThreadPool();
		List<Future<List<String>>> results = new ArrayList<Future<List<String>>>();
		for (int i = 0; i < threadSize; i++) {
			int start = i * maxLen;
			int end = (i + 1) * maxLen;
			if (end > importSize) {
				end = importSize;
			}
			List<ImportFranchiseBi> tempList = importFranchiseBiList.subList(start, end);
			results.add(exec.submit(new TaskWithResult(tempList)));
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
		long end1 = System.currentTimeMillis();
		LOGGER.info("获得加盟全部数据，执行时间为：" + (end1 - start1) / 1000 + "秒");
		return strList;
	}

}

class TaskWithResult implements Callable<List<String>> {

	private List<ImportFranchiseBi> importFranchiseBiList;

	public TaskWithResult(List<ImportFranchiseBi> importFranchiseBiList) {
		this.importFranchiseBiList = importFranchiseBiList;
	}

	@Override
	public List<String> call() throws Exception {
		List<String> rtnList = new ArrayList<String>();
		for (ImportFranchiseBi importFranchiseBi : importFranchiseBiList) {
			String shopId = importFranchiseBi.getMrkt_cd();// 市场ID
			String enghtCode = importFranchiseBi.getItem_cd_8bit();// 八位码
			String shopLocation = importFranchiseBi.getIs_sale_unsalable();// 门店定位
			String onlyKey = shopId + "," + enghtCode + "," + shopLocation;// 唯一key
			rtnList.add(onlyKey);
		}
		return rtnList;
	}
}
