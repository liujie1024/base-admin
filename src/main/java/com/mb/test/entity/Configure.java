package com.mb.test.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 */
public class Configure implements Serializable {

	private static final long serialVersionUID = 5064396540016126439L;

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 权限级别�?：管理员，查询全部数据，1：分公司，查看分公司数据�?：店铺，查看本店铺数�?
	 */
	private String configLevel;

	/**
	 * 分公司id
	 */
	private String companyId;

	/**
	 * 分公司名�?
	 */
	private String companyName;

	/**
	 * 门店id
	 */
	private String storeId;

	/**
	 * 门店名称
	 */
	private String storeName;

	/**
	 * 店员级别�?：店经理�?：店�?
	 */
	private String staffLevel;

	/**
	 * 金点�?类型
	 */
	private String goldideaProType;

	/**
	 * 投诉 类型
	 */
	private String complainProType;

	/**
	 * 创建�?
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 登录用户的id
	 */
	private String userId;

	/**
	 * 扩展字段1
	 */
	private String extend1;

	/**
	 * 扩展字段2
	 */
	private String extend2;

	/**
	 * 扩展字段3
	 */
	private String extend3;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 门店类型�?：直营；1：加盟；2：合�?
	 */
	private String storeType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigLevel() {
		return configLevel;
	}

	public void setConfigLevel(String configLevel) {
		this.configLevel = configLevel;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getGoldideaProType() {
		return goldideaProType;
	}

	public void setGoldideaProType(String goldideaProType) {
		this.goldideaProType = goldideaProType;
	}

	public String getComplainProType() {
		return complainProType;
	}

	public void setComplainProType(String complainProType) {
		this.complainProType = complainProType;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public String getStaffLevel() {
		return staffLevel;
	}

	public void setStaffLevel(String staffLevel) {
		this.staffLevel = staffLevel;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

}
