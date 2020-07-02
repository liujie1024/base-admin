package com.mb.importbi.service;

import com.mb.base.BaseService;
import com.mb.importbi.entity.ShopWhExt;

public interface IShopWhExtService extends BaseService<ShopWhExt, java.lang.String> {

	/**
	 * 根据市场ID查询数据
	 * 
	 * @param mrkt_cd
	 *            市场ID
	 * @return
	 */
	public ShopWhExt getShopWhExtByMrktcd(String mrkt_cd);

}