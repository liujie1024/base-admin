package com.mb.importbi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 导入加盟的实体类
 */
public class ImportFranchiseBi implements Serializable {

	private static final long serialVersionUID = -671435172837690836L;

	/**
	 * 默认上周周日（系统生成）
	 */
	private double clndr_wk_cd;

	/**
	 * 市场id
	 */
	private String mrkt_cd;

	/**
	 * 六位码
	 */
	private String item_cd_6bit;

	/**
	 * 八位码
	 */
	private String item_cd_8bit;

	/**
	 * 店铺定位
	 */
	private String is_sale_unsalable;

	/**
	 * sysdate
	 */
	private Date load_dt;

	/**
	 * 下周销量
	 */
	private Integer target_unit_cnt;

	/**
	 * Y
	 */
	private double target_unit_amt;

	/**
	 * 下周折扣
	 */
	private double suggest_disc;

	/**
	 * Y
	 */
	private double sku_cnt;

	/**
	 * Y
	 */
	private double plan_unit_cnt;

	/**
	 * Y
	 */
	private double target_gm_amt;

	/**
	 * 剩余生命周期
	 */
	private Integer rest_life;

	/**
	 * 集团片区
	 */
	private String mb_dist_name;

	/**
	 * 单位（分公司）
	 */
	private String manage_unit_name;

	public double getClndr_wk_cd() {
		return clndr_wk_cd;
	}

	public void setClndr_wk_cd(double clndr_wk_cd) {
		this.clndr_wk_cd = clndr_wk_cd;
	}

	public String getMrkt_cd() {
		return mrkt_cd;
	}

	public void setMrkt_cd(String mrkt_cd) {
		this.mrkt_cd = mrkt_cd;
	}

	public String getItem_cd_6bit() {
		return item_cd_6bit;
	}

	public void setItem_cd_6bit(String item_cd_6bit) {
		this.item_cd_6bit = item_cd_6bit;
	}

	public String getItem_cd_8bit() {
		return item_cd_8bit;
	}

	public void setItem_cd_8bit(String item_cd_8bit) {
		this.item_cd_8bit = item_cd_8bit;
	}

	public String getIs_sale_unsalable() {
		return is_sale_unsalable;
	}

	public void setIs_sale_unsalable(String is_sale_unsalable) {
		this.is_sale_unsalable = is_sale_unsalable;
	}

	public Date getLoad_dt() {
		return load_dt;
	}

	public void setLoad_dt(Date load_dt) {
		this.load_dt = load_dt;
	}

	public double getTarget_unit_amt() {
		return target_unit_amt;
	}

	public void setTarget_unit_amt(double target_unit_amt) {
		this.target_unit_amt = target_unit_amt;
	}

	public double getSuggest_disc() {
		return suggest_disc;
	}

	public void setSuggest_disc(double suggest_disc) {
		this.suggest_disc = suggest_disc;
	}

	public double getSku_cnt() {
		return sku_cnt;
	}

	public void setSku_cnt(double sku_cnt) {
		this.sku_cnt = sku_cnt;
	}

	public double getPlan_unit_cnt() {
		return plan_unit_cnt;
	}

	public void setPlan_unit_cnt(double plan_unit_cnt) {
		this.plan_unit_cnt = plan_unit_cnt;
	}

	public double getTarget_gm_amt() {
		return target_gm_amt;
	}

	public void setTarget_gm_amt(double target_gm_amt) {
		this.target_gm_amt = target_gm_amt;
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

	public Integer getTarget_unit_cnt() {
		return target_unit_cnt;
	}

	public void setTarget_unit_cnt(Integer target_unit_cnt) {
		this.target_unit_cnt = target_unit_cnt;
	}

	public Integer getRest_life() {
		return rest_life;
	}

	public void setRest_life(Integer rest_life) {
		this.rest_life = rest_life;
	}

}