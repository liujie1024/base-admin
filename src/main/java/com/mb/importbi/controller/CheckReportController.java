package com.mb.importbi.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.base.BaseController;
import com.mb.importbi.entity.CheckReport;
import com.mb.importbi.service.ICheckReportService;
import com.mb.util.ExportInternalUtil;
import com.mb.util.JsonResult;
import com.mb.util.MbConstant;

/**
 * 报表检测
 */
@Controller
@RequestMapping("/checkreport")
public class CheckReportController extends BaseController<CheckReport> {

	private final static Logger LOGGER = LoggerFactory.getLogger(CheckReportController.class);

	@Autowired
	private ICheckReportService checkReportService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("CheckReportController goto checkreport page");
		return "importbi/checkreport";
	}

	/**
	 * 加盟市场导入数据查询
	 * 
	 * @return
	 */
	private List<CheckReport> getJmscList() {
		List<CheckReport> rtnList = new ArrayList<CheckReport>();
		List<CheckReport> tempList = checkReportService.getJmscCount();// 加盟市场，只要有数据就上传成功
		CheckReport checkReport = new CheckReport();
		if (null != tempList && tempList.size() > 0) {
			checkReport = tempList.get(0);// 一般都是一条数据
			if (checkReport.getImport_count() > 0) {
				checkReport.setCheck_msg(MbConstant.CHECK_YSC);// 已上传
			} else {
				checkReport.setCheck_msg(MbConstant.CHECK_WSC);// 未上传
			}
		}
		checkReport.setMb_dist_name("加盟市场");
		rtnList.add(checkReport);
		return rtnList;
	}

	/**
	 * MB直营品牌，应该上传的分公司list
	 * 
	 * @return
	 */
	private List<CheckReport> getMBList4Zy() {
		List<CheckReport> rtnList = checkReportService.getMBList4Zy();// 需要上传的全部公司
		List<CheckReport> tempList = this.getAllImportDataCount(MbConstant.CHECK_MB, MbConstant.CHECK_ZY);// MB直营数据
		for (CheckReport checkReport : rtnList) {
			String tempMbDistName = checkReport.getMb_dist_name();// 品牌
			String tempManageUnitName = checkReport.getManage_unit_name();// 属性
			checkReport.setCheck_msg(MbConstant.CHECK_WSC);// 未上传
			checkReport.setImport_count(0);// 条数
			for (int i = 0; i < tempList.size(); i++) {
				CheckReport tempCheckReport = tempList.get(i);
				String mbDistName = tempCheckReport.getMb_dist_name();// 品牌
				String manageUnitName = tempCheckReport.getManage_unit_name();// 属性
				if (tempMbDistName.equals(mbDistName) && tempManageUnitName.equals(manageUnitName)) {// 片区和分公司都相同的情况下
					checkReport.setCheck_msg(MbConstant.CHECK_YSC);// 已上传
					checkReport.setImport_count(tempCheckReport.getImport_count());// 条数
				}
			}
		}
		return rtnList;
	}

	/**
	 * MB合伙人品牌，应该上传的分公司list
	 * 
	 * @return
	 */
	private List<CheckReport> getMBList4Hhr() {
		List<CheckReport> rtnList = checkReportService.getMBList4Hhr();
		List<CheckReport> tempList = this.getAllImportDataCount(MbConstant.CHECK_MB, MbConstant.CHECK_HHR);// MB合伙人数据
		for (CheckReport checkReport : rtnList) {
			String tempMbDistName = checkReport.getMb_dist_name();// 品牌
			String tempManageUnitName = checkReport.getManage_unit_name();// 属性
			checkReport.setCheck_msg(MbConstant.CHECK_WSC);// 未上传
			checkReport.setImport_count(0);// 条数
			for (int i = 0; i < tempList.size(); i++) {
				CheckReport tempCheckReport = tempList.get(i);
				String mbDistName = tempCheckReport.getMb_dist_name();// 品牌
				String manageUnitName = tempCheckReport.getManage_unit_name();// 属性
				if (tempMbDistName.equals(mbDistName) && tempManageUnitName.equals(manageUnitName)) {// 片区和分公司都相同的情况下
					checkReport.setCheck_msg(MbConstant.CHECK_YSC);// 已上传
					checkReport.setImport_count(tempCheckReport.getImport_count());// 条数
				}
			}
		}
		return rtnList;
	}

	/**
	 * MM合伙人品牌，应该上传的分公司list
	 * 
	 * @return
	 */
	private List<CheckReport> getMMList4Hhr() {
		List<CheckReport> rtnList = checkReportService.getMMList4Hhr();
		List<CheckReport> tempList = this.getAllImportDataCount(MbConstant.CHECK_MM, MbConstant.CHECK_HHR);// MM合伙人数据
		for (CheckReport checkReport : rtnList) {
			String tempMbDistName = checkReport.getMb_dist_name();// 品牌
			String tempManageUnitName = checkReport.getManage_unit_name();// 属性
			checkReport.setCheck_msg(MbConstant.CHECK_WSC);// 未上传
			checkReport.setImport_count(0);// 条数
			for (int i = 0; i < tempList.size(); i++) {
				CheckReport tempCheckReport = tempList.get(i);
				String mbDistName = tempCheckReport.getMb_dist_name();// 品牌
				String manageUnitName = tempCheckReport.getManage_unit_name();// 属性
				if (tempMbDistName.equals(mbDistName) && tempManageUnitName.equals(manageUnitName)) {// 片区和分公司都相同的情况下
					checkReport.setCheck_msg(MbConstant.CHECK_YSC);// 已上传
					checkReport.setImport_count(tempCheckReport.getImport_count());// 条数
				}
			}
		}
		return rtnList;
	}

	/**
	 * 查询导入的全部数据,分类的总数
	 * 
	 * @return
	 */
	private List<CheckReport> getAllImportDataCount(String shopBrandName, String shopClassName) {
		LOGGER.info("报表检测，需要的参数,品牌：" + shopBrandName + ",门店属性：" + shopClassName);
		return checkReportService.getCountByBrandAndClass(shopBrandName, shopClassName);
	}

	@Override
	public List<CheckReport> setPageList(HttpServletRequest request, HttpServletResponse response, CheckReport checkReport) {
		String checkType = request.getParameter("checkType");// 请求检测的类型
		return this.getCheckReportList(checkType);
	}

	/**
	 * 获得检测的数据list
	 * 
	 * @param checkType
	 *            检测的类型
	 * @return
	 */
	private List<CheckReport> getCheckReportList(String checkType) {
		List<CheckReport> checkReportList = new ArrayList<CheckReport>();
		if (StringUtils.isNotBlank(checkType)) {
			switch (checkType) {
			case MbConstant.TYPE_MB_ZY:
				checkReportList = this.getMBList4Zy();// mb直营
				break;
			case MbConstant.TYPE_MB_HHR:
				checkReportList = this.getMBList4Hhr();// mb合伙人
				break;
			case MbConstant.TYPE_MM_HHR:
				checkReportList = this.getMMList4Hhr();// mm合伙人
				break;
			case MbConstant.TYPE_JMSC:
				checkReportList = this.getJmscList();// 加盟市场
				break;
			default:
				checkReportList = new ArrayList<CheckReport>();// 默认为空
				break;
			}
		}
		return checkReportList;
	}

	/**
	 * 根据检测项的类型获得对应的文件名称
	 * 
	 * @param checkType
	 *            检测项名称
	 * @return
	 */
	private String getTempFileName(String checkType) {
		String tempName = "";
		if (StringUtils.isNotBlank(checkType)) {
			switch (checkType) {
			case MbConstant.TYPE_MB_ZY:
				tempName = "MB直营";// mb直营
				break;
			case MbConstant.TYPE_MB_HHR:
				tempName = "MB合伙人";// mb合伙人
				break;
			case MbConstant.TYPE_MM_HHR:
				tempName = "MM品牌";// mm品牌
				break;
			case MbConstant.TYPE_JMSC:
				tempName = "加盟市场";// 加盟市场
				break;
			default:
				break;
			}
		}
		return tempName;
	}

	/**
	 * 下载导出的execl
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/download")
	public JsonResult downloadExecl(HttpServletRequest request, HttpServletResponse response) {
		JsonResult result = new JsonResult(true, "导出成功");
		String checkType = request.getParameter("checkType");// 请求检测的类型
		String tempName = this.getTempFileName(checkType);
		SimpleDateFormat sdf = null;
		try {
			List<CheckReport> checkReportList = this.getCheckReportList(checkType);// mb直营
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String execlName = "报表检测导出_" + tempName + "_" + sdf.format(new Date());
			String fileName = new String(execlName.getBytes("gb2312"), "iso8859-1") + ".xlsx";
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setCharacterEncoding("utf-8");
			String[] titles = { "片区", "分公司", "上传结果", "上传条数" };
			ServletOutputStream outputStream = response.getOutputStream();
			this.exportExcel(titles, checkReportList, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("导出失败");
		}
		return result;
	}

	/**
	 * 导出execl数据
	 * 
	 * @param titles
	 *            标题
	 * @param list
	 *            list数据
	 * @param outputStream
	 */
	public void exportExcel(String[] titles, List<CheckReport> list, ServletOutputStream outputStream) {
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet Sheet名称，可以自定义中文名称
		XSSFSheet sheet = workBook.createSheet("Sheet1");
		ExportInternalUtil exportUtil = new ExportInternalUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		XSSFCell cell = null;
		// 输出标题
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}

		// 构建表体数据
		for (int j = 0; j < list.size(); j++) {
			XSSFRow bodyRow = sheet.createRow(j + 1);
			// 和标题对应值
			CheckReport checkReport = list.get(j);

			cell = bodyRow.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(checkReport.getMb_dist_name());// 片区

			cell = bodyRow.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(checkReport.getManage_unit_name());// 分公司

			cell = bodyRow.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(checkReport.getCheck_msg());// 上传结果

			cell = bodyRow.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(checkReport.getImport_count());// 上传条数

		}
		try {
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}