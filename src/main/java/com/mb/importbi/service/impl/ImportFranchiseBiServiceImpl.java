package com.mb.importbi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mb.importbi.dao.ImportFranchiseBiDao;
import com.mb.importbi.entity.ImportFranchiseBi;
import com.mb.importbi.service.IImportFranchiseBiService;

@Service
public class ImportFranchiseBiServiceImpl implements IImportFranchiseBiService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ImportFranchiseBiServiceImpl.class);

	@Resource
	private ImportFranchiseBiDao importFranchiseBiDao;// 加盟导入

	@Override
	public void insertBatchByMap(Map<String, Object> batchMap) {
		importFranchiseBiDao.insertBatch(batchMap);
	}

	@Override
	public void deleteByMap(Map<String, Object> deleteMap) {
		importFranchiseBiDao.deleteByMap(deleteMap);
	}

	@Override
	public void deleteBatchByMap(List<String> replaceRowList) {
		Map<String, Object> batchMap = new HashMap<String, Object>();
		List<ImportFranchiseBi> importFranchiseBiList = new ArrayList<ImportFranchiseBi>();
		if (null != replaceRowList && replaceRowList.size() > 0) {
			ImportFranchiseBi tempImportFranchiseBi = null;
			for (String importFranchiseBiStr : replaceRowList) {
				tempImportFranchiseBi = new ImportFranchiseBi();
				String[] importFranchiseBis = importFranchiseBiStr.split(",");
				tempImportFranchiseBi.setMrkt_cd(importFranchiseBis[0]);// 市场ID
				tempImportFranchiseBi.setItem_cd_8bit(importFranchiseBis[1]);// 8位码
				tempImportFranchiseBi.setIs_sale_unsalable(importFranchiseBis[2]);// 门店定位
				importFranchiseBiList.add(tempImportFranchiseBi);
			}
		}
		batchMap.put("deleteFranchiseBiList", importFranchiseBiList);// mapper中需要使用
		importFranchiseBiDao.deleteBatchByMap(batchMap);
	}

	@Override
	public void insertBatch(List<ImportFranchiseBi> importFranchiseBiList) {
		int maxLen = importFranchiseBiList.size();// 一共多少条数据
		int batchLen = 1000;// 每次提交个数
		int remainder = maxLen % batchLen;// 余数
		int len = remainder > 0 ? (maxLen / batchLen + 1) : (maxLen / batchLen);// 分多少批次执行
		LOGGER.info("*******加盟市场目标导入开始********************************************************");
		LOGGER.info("导入数据，一共" + maxLen + "条数据，每次执行" + batchLen + "条，分" + len + "个批次导入；");
		for (int i = 0; i < len; i++) {
			Map<String, Object> batchMap = new HashMap<String, Object>();
			Integer statrLen = i * batchLen;
			Integer endLen = (i + 1) * batchLen;
			if (i == (len - 1)) {// 如果最后一个批次的数据不是整数处理
				endLen = maxLen;
			}
			batchMap.put("importFranchiseBiList", importFranchiseBiList.subList(statrLen, endLen));
			this.insertBatchByMap(batchMap);
			LOGGER.info("批次" + (i + 1) + "导入成功，开始数据：" + statrLen + "；结束数据：" + endLen);
		}
		LOGGER.info("*******加盟市场目标导入结束********************************************************");
	}

	@Override
	public boolean save(ImportFranchiseBi entity) {
		return false;
	}

	@Override
	public boolean update(ImportFranchiseBi entity) {
		return false;
	}

	@Override
	public ImportFranchiseBi findById(String id) {
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public List<ImportFranchiseBi> getListByParam(Map<String, Object> paramMap) {
		return importFranchiseBiDao.getListByParam(paramMap);
	}

	@Override
	public boolean isHasRow(ImportFranchiseBi importFranchiseBi) {
		boolean isHas = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mrkt_cd", importFranchiseBi.getMrkt_cd());
		paramMap.put("item_cd_8bit", importFranchiseBi.getItem_cd_8bit());
		paramMap.put("is_sale_unsalable", importFranchiseBi.getIs_sale_unsalable());
		List<ImportFranchiseBi> importFranchiseBiList = this.getListByParam(paramMap);
		if (null != importFranchiseBiList && importFranchiseBiList.size() > 0) {
			isHas = true;
		}
		return isHas;
	}

	public static void main(String[] args) {
		int maxLen = 2012;
		int batchLen = 1000;// 每次提交个数
		System.out.println(maxLen / batchLen + "--------" + maxLen % batchLen);
	}
}
