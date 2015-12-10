/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class Demo4 {

	/**
	 * @param args
	 */
	static StringBuffer sbf = new StringBuffer();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ar[] = { 3, 4, 1, 2, 5, 8, 6, 70, 9, 10 };
		Demo4 dm4 = new Demo4();
		sbf = dm4.mo(ar);

		System.out.println(sbf.toString());
		sbf = dm4.xz(ar);
		System.out.println(sbf.toString() + ar[ar.length - 1]);
		// System.out.println(sbf.length());
		dm4.sort(0, ar.length - 1, ar);
	}

	public StringBuffer mo(int ar[]) {

		int temp = 0;
		for (int i = 0; i < ar.length - 1; i++) {
			for (int j = 0; j < ar.length - 1 - i; j++) {

				if (ar[j] > ar[j + 1]) {
					temp = ar[j];
					ar[j] = ar[j + 1];
					ar[j + 1] = temp;
				}
			}

		}
		int i = 0;
		StringBuffer sbf = new StringBuffer();
		while (i < ar.length) {

			// sbf.append(" ");
			sbf = sbf.append(ar[i] + ",");
			i++;
			// System.out.println(sbf.toString());
			// System.out.println(sbf.length());
		}
		return sbf;
	}

	public StringBuffer xz(int ar[]) {
		StringBuffer sbf = new StringBuffer();

		for (int i = 0; i < ar.length - 1; i++) {
			int minindex = ar[i];

			for (int j = i; j < ar.length - 1; j++) {
				if (minindex > ar[j]) {
					int temp = ar[j];
					ar[j] = minindex;
					minindex = temp;
				}
			}
			sbf = sbf.append(ar[i] + ",");
		}

		return sbf;
	}

	public void sort(int left, int right, int[] arr) {
		int l = left;
		int r = right;
		int pivot = arr[(left + right) / 2];// ’“÷–º‰÷µ
		int temp = 0;
		while (l < r) {
			while (arr[l] < pivot)
				l++;
			while (arr[r] > pivot)
				r--;
			if (l >= r)
				break;
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			if (arr[l] == pivot)
				--r;
			if (arr[r] == pivot)
				++l;
		}
		if (l == r) {
			l++;
			r--;
		}
		if (left < r)
			sort(left, r, arr);
		if (right > l)
			sort(l, right, arr);
		System.out.println(arr[arr.length - 1]);
	}

}
