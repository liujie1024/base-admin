package com.mb.importbi.entity;

import java.io.Serializable;

/**
 * 导入功能，有错误数据的实体
 */
public class ErrorMsg implements Serializable {

	private static final long serialVersionUID = 127972566685895143L;

	/**
	 * execl行数
	 */
	private String cellId;

	/**
	 * 错误的对象
	 */
	private Object object;

	/**
	 * 错误描述
	 */
	private String msg;

	public ErrorMsg() {
		super();
	}

	public ErrorMsg(String cellId, Object object, String msg) {
		super();
		this.cellId = cellId;
		this.object = object;
		this.msg = msg;
	}

	public String getCellId() {
		return cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}