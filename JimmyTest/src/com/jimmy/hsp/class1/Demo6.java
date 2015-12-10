/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class Demo6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo6 dm6 = new Demo6();
		int[][] ar = new int[2][3];
		ar[1][1] = 100;
		System.out.println(" " + ar.length);
		System.out.println(" " + ar[1].length);
		System.out.println(ar[1][1]);
		System.out.println(dm6.ewsz(ar).toString());
	}

	public StringBuffer ewsz(int[][] ar) {
		StringBuffer sbf = new StringBuffer();
		int str = 0;
		for (int i = 0; i <= ar.length - 1; i++) {
			for (int j = 0; j <= ar[i].length - 1; j++) {
				// System.out.print(ar[i][j] + ",");
				// str = ar[i][j];
				sbf = sbf.append(ar[i][j] + ",");
				// System.out.println(sbf);
			}
			// System.out.println("");
		}
		return sbf;
	}
}
