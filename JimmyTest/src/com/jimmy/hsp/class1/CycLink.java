/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class CycLink {
	// 先定义一个指向链表第一个小孩的引用
	// 指向第一个小孩的引用，不能动
	Child firstChild = null;
	Child temp = null;
	int len = 0;// 表示共有多少个小孩
	int k = 0;
	int m = 0;

	// 设置m数几下
	public void setM(int m) {
		this.m = m;
	}

	// 设置环形链表大小
	public void setLen(int len) {
		this.len = len;
	}

	// 设置从第几个人开始数数
	public void setK(int k) {
		this.k = k;
	}

	// 开始play
	public void play() {
		Child temp = this.firstChild;
		// 1.先找到开始数数的人
		for (int i = 1; i < k; i++) {
			temp = temp.nextChild;
		}
		while (this.len != 1) {
			// 2.数m下
			for (int j = 1; j < m; j++) {
				temp = temp.nextChild;
			}
			// 找到要出圈的前一个小孩
			Child temp2 = temp;
			// System.out.println("出队顺序：");
			while (temp2.nextChild != temp) {
				temp2 = temp2.nextChild;

			}

			// 3.将数到m的小孩，退出圈
			temp2.nextChild = temp.nextChild;
			// 让temp指向下一个数数的小孩
			temp = temp.nextChild;
			// System.out.println("now :" + temp.no);
			this.len--;
		}
		// 最后一个小孩
		System.out.println("最后出圈的小孩:" + temp.no);
	}

	// 初始化单向环形链表
	public void createLink() {
		for (int i = 1; i <= len; i++) {
			if (i == 1) {
				// 创建第一个小孩
				Child ch = new Child(i);
				this.firstChild = ch;
				this.temp = ch;
				System.out.println("第一个" + this.temp.no);
			} else {
				// 创建最后一个小孩
				if (i == len) {
					Child ch = new Child(i);
					temp.nextChild = ch;
					temp = ch;
					temp.nextChild = this.firstChild;
					System.out.println("最后一个" + this.temp.no);
				} else {
					// 继续创建小孩
					Child ch = new Child(i);
					temp.nextChild = ch;
					temp = ch;
					System.out.println(this.temp.no);
				}
			}
		}
	}

	// 打印该环形链表
	public void show() {
		// 定义一个跑龙套
		Child temp = this.firstChild;
		do {
			System.out.print(temp.no + " ");
			temp = temp.nextChild;
		} while (temp != this.firstChild);
	}
}
