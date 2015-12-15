/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.Scanner;

/**
 * @author jimmy
 * @category 查找算法的学习
 */
public class Demo5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 2, 5, 7, 12, 25 };// 定义arr数组并赋值
		System.out.print("请输入你需要查找的数：");
		Scanner sr = new Scanner(System.in);
		int a = sr.nextInt();
		Demo5 bf = new Demo5();// 创建BinaryFind对象
		bf.BinaryFind(0, arr.length - 1, a, arr);
	}

	/**
	 * @param i
	 * @param j
	 * @param a
	 * @param arr
	 */
	private void BinaryFind(int leftIndex, int rightIndex, int val, int[] arr) {
		// TODO Auto-generated method stub
		// 首先找到中间的数
		int midIndex = ((rightIndex + leftIndex) / 2);
		int midVal = arr[midIndex];
		if (rightIndex >= leftIndex) {
			// 如果要找的数比midVal大
			if (midVal > val) {
				// 在arr数组左边数列中找
				BinaryFind(leftIndex, midIndex - 1, val, arr);
			} else if (midVal < val) {
				// 在arr数组右边数列中找
				BinaryFind(midIndex + 1, rightIndex, val, arr);
			} else if (midVal == val) {
				System.out.println("数组arr[" + midIndex + "]中的数字是"
						+ arr[midIndex]);
			}
		} else {
			System.out.println("没有找到你要找的数!");
		}
	}

}
