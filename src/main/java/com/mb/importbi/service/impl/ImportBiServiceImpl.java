package com.mb.importbi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mb.importbi.dao.ImportBiDao;
import com.mb.importbi.entity.ImportBi;
import com.mb.importbi.service.IImportBiService;

@Service
public class ImportBiServiceImpl implements IImportBiService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ImportBiServiceImpl.class);

	@Resource
	private ImportBiDao importBiDao;

	@Override
	public void insertBatchByMap(Map<String, Object> batchMap) {
		importBiDao.insertBatch(batchMap);
	}

	@Override
	public void deleteByMap(Map<String, Object> deleteMap) {
		importBiDao.deleteByMap(deleteMap);
	}

	@Override
	public void deleteBatchByMap(List<String> replaceRowList) {
		Map<String, Object> batchMap = new HashMap<String, Object>();
		List<ImportBi> importBiList = new ArrayList<ImportBi>();
		if (null != replaceRowList && replaceRowList.size() > 0) {
			ImportBi tempImportBi = null;
			for (String importBiStr : replaceRowList) {
				tempImportBi = new ImportBi();
				String[] importBis = importBiStr.split(",");
				tempImportBi.setShop_id(importBis[0]);
				tempImportBi.setItem_cd_8bit(importBis[1]);
				tempImportBi.setIs_sale_unsalable(importBis[2]);
				importBiList.add(tempImportBi);
			}
		}
		batchMap.put("deleteBatchList", importBiList);
		importBiDao.deleteBatchByMap(batchMap);
	}

	@Override
	public void insertBatch(List<ImportBi> importBiList) throws Exception {
		int maxLen = importBiList.size();// 一共多少条数据
		int batchLen = 1000;// 每次提交个数
		int remainder = maxLen % batchLen;// 余数
		int len = remainder > 0 ? (maxLen / batchLen + 1) : (maxLen / batchLen);// 分多少批次执行
		LOGGER.info("*******直营&合伙分公司目标数据导入开始********************************************************");
		LOGGER.info("导入数据，一共" + maxLen + "条数据，每次执行" + batchLen + "条，分" + len + "个批次导入；");
		for (int i = 0; i < len; i++) {
			Map<String, Object> batchMap = new HashMap<String, Object>();
			Integer statrLen = i * batchLen;
			Integer endLen = (i + 1) * batchLen;
			if (i == (len - 1)) {// 如果最后一个批次的数据不是整数处理
				endLen = maxLen;
			}
			batchMap.put("importBiList", importBiList.subList(statrLen, endLen));
			this.insertBatchByMap(batchMap);
			LOGGER.info("批次" + (i + 1) + "导入成功，开始数据：" + statrLen + "；结束数据：" + endLen);
		}
		LOGGER.info("*******直营&合伙分公司目标数据导入结束********************************************************");
	}

	@Override
	public boolean save(ImportBi entity) {
		return false;
	}

	@Override
	public boolean update(ImportBi entity) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public ImportBi findById(String id) {
		return null;
	}

	@Override
	public List<ImportBi> getListByParam(Map<String, Object> paramMap) {
		return importBiDao.getListByParam(paramMap);
	}

	@Override
	public boolean isHasRow(ImportBi importBi) {
		boolean isHas = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("shop_id", importBi.getShop_id());
		paramMap.put("item_cd_8bit", importBi.getItem_cd_8bit());
		paramMap.put("is_sale_unsalable", importBi.getIs_sale_unsalable());
		List<ImportBi> importBiList = this.getListByParam(paramMap);
		if (null != importBiList && importBiList.size() > 0) {
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
