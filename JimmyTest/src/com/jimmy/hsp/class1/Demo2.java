/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.ArrayList;
import java.util.List;

/**
 * Josephus问题(丢手帕问题)
 * 
 * @author jimmy
 *
 */
public class Demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo2 dm2 = new Demo2();
		dm2.setLen(100);
		dm2.setStart(10);
		dm2.setCout(4);
		if (dm2.getStart() > dm2.getLen() || dm2.getStart() < 1) {
			System.out.println("开始序号必须大于1并且小于总人数");
			System.exit(1);
		}
		List<Memb> list = dm2.createMemb(dm2.getLen());
		// list.remove(2);
		System.out.println(list.get(2).no);
		System.out.println("length:" + list.size());
		System.out.println("length is " + dm2.getLen() + " start "
				+ dm2.getStart() + " count :" + dm2.getCout());
		dm2.run(dm2.getStart(), dm2.getCout(), list);
		// int i = 20 % 9;

	}

	int len;
	int start;
	int cout;

	/**
	 * 通过list保存创建的对象
	 * 
	 * @param leng
	 * @return
	 */

	public List<Memb> createMemb(int leng) {
		List<Memb> list = new ArrayList<Memb>();
		for (int i = 1; i <= leng; i++) {
			Memb mb = new Memb(i);
			list.add(mb);
		}
		return list;
	}

	/**
	 * 开始去处
	 * 
	 * @param start
	 * @param count
	 * @param list
	 */
	public void run(int start, int count, List<Memb> list) {

		// int nextstart;
		int nowout = 0;
		while (!list.isEmpty()) {
			// 获取当前要出去的对象在list中的index
			if (start + (count - 1) < list.size()) {
				// 从1开始数2个，就是第2个出列，1+2-1
				nowout = start + count - 1;
			} else if (start + (count - 1) == list.size()) {
				nowout = list.size() - 1;
			} else if (start + (count - 1) > list.size()) {
				nowout = (start + count - list.size() - 1) % list.size();
			} else {
				break;
			}

			System.out.println("Start :" + start + " Now out :" + nowout
					+ " Size :" + list.size() + " NO :" + list.get(nowout).no);
			start = nowout + 1;
			list.remove(nowout);
		}
	}

	/**
	 * @return the len
	 */
	public int getLen() {
		return len;
	}

	/**
	 * @param len
	 *            the len to set
	 */
	public void setLen(int len) {
		this.len = len;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the cout
	 */
	public int getCout() {
		return cout;
	}

	/**
	 * @param cout
	 *            the cout to set
	 */
	public void setCout(int cout) {
		this.cout = cout;
	}
}
