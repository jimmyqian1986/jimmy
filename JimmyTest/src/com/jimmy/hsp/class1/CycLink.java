/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class CycLink {
	// �ȶ���һ��ָ�������һ��С��������
	// ָ���һ��С�������ã����ܶ�
	Child firstChild = null;
	Child temp = null;
	int len = 0;// ��ʾ���ж��ٸ�С��
	int k = 0;
	int m = 0;

	// ����m������
	public void setM(int m) {
		this.m = m;
	}

	// ���û��������С
	public void setLen(int len) {
		this.len = len;
	}

	// ���ôӵڼ����˿�ʼ����
	public void setK(int k) {
		this.k = k;
	}

	// ��ʼplay
	public void play() {
		Child temp = this.firstChild;
		// 1.���ҵ���ʼ��������
		for (int i = 1; i < k; i++) {
			temp = temp.nextChild;
		}
		while (this.len != 1) {
			// 2.��m��
			for (int j = 1; j < m; j++) {
				temp = temp.nextChild;
			}
			// �ҵ�Ҫ��Ȧ��ǰһ��С��
			Child temp2 = temp;
			// System.out.println("����˳��");
			while (temp2.nextChild != temp) {
				temp2 = temp2.nextChild;

			}

			// 3.������m��С�����˳�Ȧ
			temp2.nextChild = temp.nextChild;
			// ��tempָ����һ��������С��
			temp = temp.nextChild;
			// System.out.println("now :" + temp.no);
			this.len--;
		}
		// ���һ��С��
		System.out.println("����Ȧ��С��:" + temp.no);
	}

	// ��ʼ������������
	public void createLink() {
		for (int i = 1; i <= len; i++) {
			if (i == 1) {
				// ������һ��С��
				Child ch = new Child(i);
				this.firstChild = ch;
				this.temp = ch;
				System.out.println("��һ��" + this.temp.no);
			} else {
				// �������һ��С��
				if (i == len) {
					Child ch = new Child(i);
					temp.nextChild = ch;
					temp = ch;
					temp.nextChild = this.firstChild;
					System.out.println("���һ��" + this.temp.no);
				} else {
					// ��������С��
					Child ch = new Child(i);
					temp.nextChild = ch;
					temp = ch;
					System.out.println(this.temp.no);
				}
			}
		}
	}

	// ��ӡ�û�������
	public void show() {
		// ����һ��������
		Child temp = this.firstChild;
		do {
			System.out.print(temp.no + " ");
			temp = temp.nextChild;
		} while (temp != this.firstChild);
	}
}
