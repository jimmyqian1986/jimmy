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

	// ������

	public void getSquare() {
		for (int i = 1; i < 5; i++) {
			System.out.println("********");
		}
	}

	// *******************************************************************************

	// ֱ��������

	public void getRightTriangle() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// *******************************************************************************

	// ������

	public void getTriangle() {
		int lay = 10;// ����
		for (int i = 1; i <= lay; i++) {// ѭ����
			for (int k = 1; k <= lay - i; k++) {// �ҿո�
				System.out.print(" ");
			}
			for (int j = 1; j <= (i - 1) * 2 + 1; j++) {// ����λ�ò�����
				System.out.print("*");
			}
			System.out.println();// ����
		}
	}

	/***
	 * ��֪������Ϸ��һ̨320Ԫ��MP3һ̨260Ԫ����˾���г齱��ᣬ��˾��18�ˣ�һ�Ƚ���Ϸ�������Ƚ�MP3��
	 * ����5����Ϸ����13̨MP3�����ܼ�Ϊ���٣���˾ʵ��ƽ����ÿ�˻��Ѷ���Ǯ��
	 */
	public void js() {
		// ��Ϸ������
		int yxj = 320;
		int mp3 = 260;
		int num_p = 18;
		int num_y = 5;
		int num_m = 13;
		double total_p = 0.0;
		double eche = 0.0;
		total_p = yxj * num_y + (mp3 * num_m);
		eche = total_p / num_p;
		System.out.println("�ܼ��ǣ�" + total_p);
		System.out.println("ƽ���ǣ�" + eche);
	}

	public void cfkj() {
		Scanner myScanner = new Scanner(System.in);// ���ü�������
		System.out.print("������1-9�е�һ������");
		int num = myScanner.nextInt();
		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i + "��" + j + "=" + (i * j) + "\t");
			}
			System.out.println();
		}
	}
}
