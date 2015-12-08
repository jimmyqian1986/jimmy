/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class Child {
	int no;
	Child nextChild = null;

	public Child(int no) {
		// 给一个编号
		this.no = no;
		System.out.println("现在创建的是第:" + no + "对象!");
	}
}
