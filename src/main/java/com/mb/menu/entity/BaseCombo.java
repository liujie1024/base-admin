package com.mb.menu.entity;

import java.io.Serializable;

/**
 * 基础的combo控件属性
 */
public class BaseCombo implements Serializable {

	private static final long serialVersionUID = 1753462905328242844L;

	private String id;

	private String text;

	private boolean selected = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}