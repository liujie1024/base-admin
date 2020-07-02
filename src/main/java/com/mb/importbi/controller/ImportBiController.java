package com.mb.importbi.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mb.importbi.entity.ImportBi;
import com.mb.importbi.service.IImportBiService;
import com.mb.util.CommonUtil;
import com.mb.util.DateUtil;
import com.mb.util.MbConstant;
import com.mb.util.NumberValidationUtil;

/**
 * 直营合伙人导入
 */
@Controller
@RequestMapping("/importbi")
public class ImportBiController extends BaseController<ImportBi> {

	private final static Logger LOGGER = LoggerFactory.getLogger(ImportBiController.class);

	public final static List<String> UNSALABLELIST = Arrays.asList("畅", "滞", "普", "未定义"); // 店铺定位不允许为空，字符必须为（畅，滞，普，未定义），要去空格

	@Autowired
	private IImportBiService importBiService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("ImportBiController goto importbi page");
		return "importbi/importbi";
	}

	@Override
	public List<ImportBi> setPageList(HttpServletRequest request, HttpServletResponse response, ImportBi entity) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/upload")
	public Map<String, Object> upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<ImportBi> successRowList = null;// 成功的数据list
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
					successRowList = (List<ImportBi>) returnMap.get(MbConstant.SUCCESS);
					failRowList = (List<ErrorMsg>) returnMap.get(MbConstant.FAIL);
					replaceRowList = (List<String>) returnMap.get(MbConstant.REPLACE);
				}
			}
			Integer successLen = successRowList.size();
			Integer failLen = failRowList.size();
			if (successLen > 0 && failLen == 0) {// 只有没有错误的数据，并且成功的数据不为空的情况下，再新增
				this.deleteBatchByParam(replaceRowList);// 先删除重复数据
				this.insertBatch(successRowList);// 先新增数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long runTime = (endTime - startTime) / 1000;
		LOGGER.info("数据处理，共花费时间：" + runTime + "秒");
		rtnMap.put("successRowListSize", successRowList.size());// 成功数据
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
		List<ImportBi> successRowList = new ArrayList<ImportBi>();// 成功的数据list
		List<ErrorMsg> failRowList = new ArrayList<ErrorMsg>();// 有错误的数据list
		List<String> replaceRowList = new ArrayList<String>();// 重复的数据list
		List<String> keyList = new ArrayList<String>();// 唯一key的集合，判断key是否有重复的
		ImportBi importBi = null;
		StringBuffer failSb = null;// 失败的描述
		String lastSunday = DateUtil.getLastSunday();// 上周日
		List<String> allList = this.getAllData();// 数据库中的所有数据
		// 处理当前页，循环读取每一页
		for (int rowNum = 0; rowNum <= xssfFSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfFSheet.getRow(rowNum);// 一行数据
			if (xssfRow == null) {
				continue;
			}
			if (isContent(xssfRow)) {// 排除说明和表头
				continue;
			}
			// --数据处理-------------------------------------------------------------------------
			boolean isSuccess = true;// 判断这条记录是否合法
			failSb = new StringBuffer();
			importBi = new ImportBi();
			// 默认上周周日（系统生成）
			importBi.setClndr_wk_cd(Double.valueOf(lastSunday));
			// 门店ID
			String shopId = xssfRow.getCell(2) == null ? "" : xssfRow.getCell(2).toString().trim();
			if (StringUtils.isNotBlank(shopId)) {
				importBi.setShop_id(shopId);
			} else {
				isSuccess = false;
				failSb.append("门店ID为空；");
			}
			// 6位码
			String sixCode = CommonUtil.setExeclDate4Type(xssfRow.getCell(6));
			if (StringUtils.isNotBlank(sixCode) && sixCode.length() == 6) {
				importBi.setItem_cd_6bit(sixCode);
			} else {
				isSuccess = false;
				failSb.append("6位码为空或长度不对；");
			}
			// 8位码
			String enghtCode = CommonUtil.setExeclDate4Type(xssfRow.getCell(7));
			if (StringUtils.isNotBlank(enghtCode) && enghtCode.length() == 8) {
				importBi.setItem_cd_8bit(enghtCode);
			} else {
				isSuccess = false;
				failSb.append("8位码为空或长度不对；");
			}
			// 剩余生命周期
			String restLife = CommonUtil.setExeclDate4Type(xssfRow.getCell(8));
			if (StringUtils.isNotBlank(restLife)) {
				if (NumberValidationUtil.isPositiveInteger2(restLife)) {
					importBi.setRest_life(Integer.valueOf(restLife));
				} else {
					isSuccess = false;
					failSb.append("剩余生命周期有非法字符；");
				}
			}
			// 店铺定位
			String shopLocation = xssfRow.getCell(9) == null ? "" : xssfRow.getCell(9).toString().trim();
			if (StringUtils.isNotBlank(shopLocation)) {
				if (UNSALABLELIST.contains(shopLocation)) {
					importBi.setIs_sale_unsalable(shopLocation);
				} else {
					isSuccess = false;
					failSb.append("店铺定位有误；");
				}
			} else {
				isSuccess = false;
				failSb.append("店铺定位为空；");
			}
			// 下周销量
			String targetUnitCut = CommonUtil.setExeclDate4Type(xssfRow.getCell(10));
			if (StringUtils.isNotBlank(targetUnitCut)) {
				if (NumberValidationUtil.isPositiveInteger2(targetUnitCut)) {
					importBi.setTarget_unit_cnt(Integer.valueOf(targetUnitCut));
				} else {
					isSuccess = false;
					failSb.append("下周销量有非法字符；");
				}
			}
			// 下周折扣
			String suggestDisc = xssfRow.getCell(11) == null ? "" : xssfRow.getCell(11).toString();
			if (StringUtils.isNotBlank(suggestDisc)) {
				if (NumberValidationUtil.isRealNumber(suggestDisc)) {// 判断是否为数字或者小数,如果不为数字
					importBi.setSuggest_disc(Double.valueOf(suggestDisc));
				} else {
					isSuccess = false;
					failSb.append("下周折扣含有非法字符；");
				}
			}
			// ---1、判断本次导入的是否含有重复的，门店id+8位码+门店定位；------------------------------------------------------
			String onlyKey = shopId + "," + enghtCode + "," + shopLocation;// 唯一key
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
				LOGGER.info("该条数据正确，EXECL行号：" + String.valueOf(rowNum + 1));
				successRowList.add(importBi);
			} else {
				// EXECL的该条行记录
				String tempStr = shopId + "," + sixCode + "," + enghtCode + "," + restLife + "," + shopLocation + "," + targetUnitCut + "," + suggestDisc;// 整行数据，逗号分割
				String rowNumStr = String.valueOf(rowNum + 1);// 行号
				failRowList.add(new ErrorMsg(rowNumStr, tempStr, failSb.toString()));
				LOGGER.info("该条数据有误，EXECL行号：" + rowNumStr + "；内容：" + tempStr + ";错误说明：" + failSb.toString());
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
		List<String> rtnList = new ArrayList<String>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<ImportBi> importBiList = importBiService.getListByParam(paramMap);
		if (null != importBiList && importBiList.size() > 0) {
			for (ImportBi importBi : importBiList) {
				String shopId = importBi.getShop_id();// 门店ID
				String enghtCode = importBi.getItem_cd_8bit();// 八位码
				String shopLocation = importBi.getIs_sale_unsalable();// 门店定位
				String onlyKey = shopId + "," + enghtCode + "," + shopLocation;// 唯一key
				rtnList.add(onlyKey);
			}
		}
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
		String shopId = xssfRow.getCell(2) == null ? "" : xssfRow.getCell(2).toString();// 门店ID
		String cd8bit = xssfRow.getCell(7) == null ? "" : xssfRow.getCell(7).toString();// 8位码
		String location = xssfRow.getCell(9) == null ? "" : xssfRow.getCell(9).toString();// 门店定位
		// 说明
		if (StringUtils.isBlank(shopId) && StringUtils.isBlank(cd8bit) && StringUtils.isBlank(location)) {
			rtn = true;
		}
		// 表头
		if ("门店ID".equals(shopId) && "8位码".equals(cd8bit) && "店铺定位".equals(location)) {
			rtn = true;
		}
		return rtn;
	}

	/**
	 * 批量处理数据，新增
	 * 
	 * @param importBiList
	 *            批量数据
	 */
	private synchronized void insertBatch(List<ImportBi> importBiList) {
		int maxLen = importBiList.size();// 一共多少条数据
		int batchLen = 1000;// 每次提交个数
		int remainder = maxLen % batchLen;// 余数
		int len = remainder > 0 ? (maxLen / batchLen + 1) : (maxLen / batchLen);// 分多少批次执行
		LOGGER.info("*******直营&合伙分公司目标数据导入开始***************************");
		LOGGER.info("导入数据，一共" + maxLen + "条数据，每次执行" + batchLen + "条，分" + len + "个批次导入；");
		for (int i = 0; i < len; i++) {
			Map<String, Object> batchMap = new HashMap<String, Object>();
			Integer statrLen = i * batchLen;
			Integer endLen = (i + 1) * batchLen;
			if (i == (len - 1)) {// 如果最后一个批次的数据不是整数处理
				endLen = maxLen;
			}
			batchMap.put("importBiList", importBiList.subList(statrLen, endLen));
			importBiService.insertBatchByMap(batchMap);
			LOGGER.info("批次" + (i + 1) + "导入成功，开始数据：" + statrLen + "；结束数据：" + endLen);
		}
		LOGGER.info("*******直营&合伙分公司目标数据导入结束***************************");
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
		LOGGER.info("*******直营&合伙分公司目标批量数据删除开始***************************");
		LOGGER.info("重复数据删除，一共" + maxLen + "条数据，每次执行" + batchLen + "条，分" + len + "个批次删除；");
		for (int i = 0; i < len; i++) {
			Integer statrLen = i * batchLen;
			Integer endLen = (i + 1) * batchLen;
			if (i == (len - 1)) {// 如果最后一个批次的数据不是整数处理
				endLen = maxLen;
			}
			List<String> tempReplaceRowList = replaceRowList.subList(statrLen, endLen);
			importBiService.deleteBatchByMap(tempReplaceRowList);
			LOGGER.info("批次" + (i + 1) + "删除成功，开始数据：" + statrLen + "；结束数据：" + endLen);
		}
		LOGGER.info("*******直营&合伙分公司目标批量数据删除结束***************************");
	}

}