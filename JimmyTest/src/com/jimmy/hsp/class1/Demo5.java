/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.Scanner;

/**
 * @author jimmy
 * @category �����㷨��ѧϰ
 */
public class Demo5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 2, 5, 7, 12, 25 };// ����arr���鲢��ֵ
		System.out.print("����������Ҫ���ҵ�����");
		Scanner sr = new Scanner(System.in);
		int a = sr.nextInt();
		Demo5 bf = new Demo5();// ����BinaryFind����
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
		// �����ҵ��м����
		int midIndex = ((rightIndex + leftIndex) / 2);
		int midVal = arr[midIndex];
		if (rightIndex >= leftIndex) {
			// ���Ҫ�ҵ�����midVal��
			if (midVal > val) {
				// ��arr���������������
				BinaryFind(leftIndex, midIndex - 1, val, arr);
			} else if (midVal < val) {
				// ��arr�����ұ���������
				BinaryFind(midIndex + 1, rightIndex, val, arr);
			} else if (midVal == val) {
				System.out.println("����arr[" + midIndex + "]�е�������"
						+ arr[midIndex]);
			}
		} else {
			System.out.println("û���ҵ���Ҫ�ҵ���!");
		}
	}

}
