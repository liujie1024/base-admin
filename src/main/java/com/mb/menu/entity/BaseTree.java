package com.mb.menu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * id：节点ID，对加载远程数据很重要。 text：显示节点文本。 state：节点状态，'open'
 * 或'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。 checked：表示该节点是否被选中。 attributes:
 * 被添加到节点的自定义属性。 children: 一个节点数组声明了若干节点。
 */
public class BaseTree implements Serializable {

	private static final long serialVersionUID = 1424380006823987560L;

	/**
	 * 节点ID，对加载远程数据很重要
	 */
	private String id;

	/**
	 * 显示节点文本
	 */
	private String text;

	/**
	 * state：节点状态，'open'或'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点
	 */
	private String state = "open";

	/**
	 * checked：表示该节点是否被选中
	 */
	private boolean checked = false;

	/**
	 * attributes:被添加到节点的自定义属性
	 */
	private Object attributes;

	/**
	 * children: 一个节点数组声明了若干节点
	 */
	private List<BaseTree> children;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<BaseTree> getChildren() {
		return children;
	}

	public void setChildren(List<BaseTree> children) {
		this.children = children;
	}

}