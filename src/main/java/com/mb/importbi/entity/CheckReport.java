package com.mb.importbi.entity;

import java.io.Serializable;

/**
 * 报表导入的结果检测，主要分公司和片区的检查
 */
public class CheckReport implements Serializable {

	private static final long serialVersionUID = 5659473275574494491L;

	/**
	 * 片区（东北，华南等）
	 */
	private String mb_dist_name;

	/**
	 * 分公司
	 */
	private String manage_unit_name;

	/**
	 * 检测结果
	 */
	private String check_msg;

	/**
	 * 导入的总数，分类，按照品牌和店铺属性
	 */
	private Integer import_count;

	public Integer getImport_count() {
		return import_count;
	}

	public void setImport_count(Integer import_count) {
		this.import_count = import_count;
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

	public String getCheck_msg() {
		return check_msg;
	}

	public void setCheck_msg(String check_msg) {
		this.check_msg = check_msg;
	}

}