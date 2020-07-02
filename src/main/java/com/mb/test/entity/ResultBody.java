package com.mb.test.entity;

import java.io.Serializable;
import java.util.List;

public class ResultBody implements Serializable {

	private static final long serialVersionUID = 2369035496509850695L;

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 描述
	 */
	private String message;

	/**
	 * 结果列表
	 */
	private List<?> objList;

	/**
	 * 总数
	 */
	private Integer total;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getObjList() {
		return objList;
	}

	public void setObjList(List<?> objList) {
		this.objList = objList;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
