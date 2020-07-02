package com.mb.importbi.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.importbi.entity.ShopWhExt;

@MapperScan
public interface ShopWhExtDao extends BaseDao<ShopWhExt, java.lang.String> {

	/**
	 * 根据市场ID查询数据
	 * 
	 * @param mrkt_cd
	 *            市场ID
	 * @return
	 */
	public List<ShopWhExt> getShopWhExtByMrktcd(String mrkt_cd);

}