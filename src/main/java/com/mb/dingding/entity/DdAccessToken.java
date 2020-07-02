package com.mb.dingding.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉的access_token的实体
 */
public class DdAccessToken implements Serializable {

	private static final long serialVersionUID = -5110836025955338876L;

	/**
	 * 项目key
	 */
	private String appKey;

	/**
	 * token生成的时间
	 */
	private Date beginTime;

	/**
	 * token值
	 */
	private String assessToken;

	/**
	 * 扩展字段1
	 */
	private String extend1;

	/**
	 * 扩展字段2
	 */
	private String extend2;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getAssessToken() {
		return assessToken;
	}

	public void setAssessToken(String assessToken) {
		this.assessToken = assessToken;
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

}
