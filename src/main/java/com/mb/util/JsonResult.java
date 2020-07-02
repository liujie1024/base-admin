package com.mb.util;

import java.io.Serializable;

/**
 * 调用返回结果
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = -3128040798275686008L;

	private boolean success;// 执行操作是否成功

	private String msg;// 返回消息实体

	private Object data;// 承载数据

	// 构造函数
	public JsonResult() {
		super();
	}

	// 构造函数
	public JsonResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	// 构造函数
	public JsonResult(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}