package com.mb.importbi.entity;

/**
 * 定义校验错误的类型
 */
public enum ErrorType {

	ERROR_1("含有中文", 1), ERROR_2("不能为空", 2);

	// 成员变量
	private String name;

	private int index;

	// 构造方法
	private ErrorType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	/**
	 * 根据序号选择对应的值
	 * 
	 * @param index
	 *            序号
	 * @return
	 */
	public static String getName(int index) {
		for (ErrorType c : ErrorType.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static void main(String[] args) {
	}

}
