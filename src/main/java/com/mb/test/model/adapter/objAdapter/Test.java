package com.mb.test.model.adapter.objAdapter;

/**
 * 对象的适配器模式
 * 
 * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题
 */
public class Test {

	public static void main(String[] args) {
		Source source = new Source();
		Targetable target = new Adapter(source);
		target.method1();
		target.method2();
	}

}