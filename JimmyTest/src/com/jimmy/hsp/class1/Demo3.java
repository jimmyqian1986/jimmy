/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.Scanner;

/**
 * @author jimmy
 *
 */
public class Demo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo3 dm3 = new Demo3();
		dm3.getSquare();
		dm3.getRightTriangle();
		dm3.getTriangle();
		dm3.js();
		dm3.cfkj();
	}

	// 正方形

	public void getSquare() {
		for (int i = 1; i < 5; i++) {
			System.out.println("********");
		}
	}

	// *******************************************************************************

	// 直角三角形

	public void getRightTriangle() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// *******************************************************************************

	// 三角形

	public void getTriangle() {
		int lay = 10;// 行数
		for (int i = 1; i <= lay; i++) {// 循环行
			for (int k = 1; k <= lay - i; k++) {// 找空格
				System.out.print(" ");
			}
			for (int j = 1; j <= (i - 1) * 2 + 1; j++) {// 找星位置并输入
				System.out.print("*");
			}
			System.out.println();// 换行
		}
	}

	/***
	 * 已知手掌游戏机一台320元，MP3一台260元。公司举行抽奖大会，公司有18人，一等奖游戏机，二等奖MP3，
	 * 购入5公游戏机，13台MP3。问总价为多少，公司实际平均给每人花费多少钱。
	 */
	public void js() {
		// 游戏机单价
		int yxj = 320;
		int mp3 = 260;
		int num_p = 18;
		int num_y = 5;
		int num_m = 13;
		double total_p = 0.0;
		double eche = 0.0;
		total_p = yxj * num_y + (mp3 * num_m);
		eche = total_p / num_p;
		System.out.println("总价是：" + total_p);
		System.out.println("平均是：" + eche);
	}

	public void cfkj() {
		Scanner myScanner = new Scanner(System.in);// 调用键盘输入
		System.out.print("请输入1-9中的一个数：");
		int num = myScanner.nextInt();
		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i + "×" + j + "=" + (i * j) + "\t");
			}
			System.out.println();
		}
	}
}
