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
		// ��һ�����
		this.no = no;
		System.out.println("���ڴ������ǵ�:" + no + "����!");
	}
}
