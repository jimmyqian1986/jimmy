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
		cyclink.setLen(9);// ������
		cyclink.createLink();
		cyclink.setK(3);// �ӵڼ����˿�ʼ��
		cyclink.setM(2);// ������
		cyclink.show();
		cyclink.play();
	}

}
