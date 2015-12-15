/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CycLink cyclink = new CycLink();
		cyclink.setLen(9);// 链表长度
		cyclink.createLink();
		cyclink.setK(3);// 从第几个人开始数
		cyclink.setM(2);// 数几下
		cyclink.show();
		cyclink.play();
	}

}
