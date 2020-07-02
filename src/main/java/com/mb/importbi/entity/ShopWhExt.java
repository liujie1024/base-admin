package com.mb.importbi.entity;

import java.io.Serializable;

/**
 * 商店维度表
 */
public class ShopWhExt implements Serializable {

	private static final long serialVersionUID = 1794216739983084100L;

	/**
	 * 门店ID
	 */
	private String shop_id;

	/**
	 * 市场ID
	 */
	private String mrkt_cd;

	/**
	 * 集团片区
	 */
	private String mb_dist_name;

	/**
	 * 分公司
	 */
	private String manage_unit_name;

	/**
	 * 品牌名称
	 */
	private String shop_brand_name;

	/**
	 * 店铺类型，直营，合伙人
	 */
	private String shop_class_name;

	/**
	 * 门店名称
	 */
	private String shop_name;

	public String getShop_brand_name() {
		return shop_brand_name;
	}

	public void setShop_brand_name(String shop_brand_name) {
		this.shop_brand_name = shop_brand_name;
	}

	public String getShop_class_name() {
		return shop_class_name;
	}

	public void setShop_class_name(String shop_class_name) {
		this.shop_class_name = shop_class_name;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getMb_dist_name() {
		return mb_dist_name;
	}

	public void setMb_dist_name(String mb_dist_name) {
		this.mb_dist_name = mb_dist_name;
	}

	public String getManage_unit_name() {
		return manage_unit_name;
	}

	public void setManage_unit_name(String manage_unit_name) {
		this.manage_unit_name = manage_unit_name;
	}

	public String getMrkt_cd() {
		return mrkt_cd;
	}

	public void setMrkt_cd(String mrkt_cd) {
		this.mrkt_cd = mrkt_cd;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

}