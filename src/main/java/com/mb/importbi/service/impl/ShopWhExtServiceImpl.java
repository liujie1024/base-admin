package com.mb.importbi.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mb.importbi.dao.ShopWhExtDao;
import com.mb.importbi.entity.ShopWhExt;
import com.mb.importbi.service.IShopWhExtService;

@Service
public class ShopWhExtServiceImpl implements IShopWhExtService {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShopWhExtServiceImpl.class);

	@Resource
	private ShopWhExtDao shopWhExtDao;

	@Override
	public boolean save(ShopWhExt entity) {
		LOGGER.info("ShopWhExtServiceImpl save ShopWhExt");
		return false;
	}

	@Override
	public boolean update(ShopWhExt entity) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public ShopWhExt findById(String shopId) {
		return shopWhExtDao.findById(shopId);
	}

	@Override
	public List<ShopWhExt> getListByParam(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public ShopWhExt getShopWhExtByMrktcd(String mrkt_cd) {
		ShopWhExt shopWhExt = null;
		List<ShopWhExt> shopWhExtList = shopWhExtDao.getShopWhExtByMrktcd(mrkt_cd);
		if (null != shopWhExtList && shopWhExtList.size() > 0) {
			shopWhExt = shopWhExtList.get(0);
		}
		return shopWhExt;
	}

}