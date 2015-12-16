/**
 * 
 */
package com.jimmy.hsp.class3;

/**
 * @author jimmy
 *
 */
/**
 * ����:�¼��������
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo3 extends JFrame implements ActionListener {
	// �������
	JPanel mp = null;
	JButton jb1, jb2;

	public static void main(String[] args) {
		Demo3 win = new Demo3();
	}

	// ���캯��
	public Demo3() {
		// �������
		mp = new JPanel();
		jb1 = new JButton("��ɫ");
		jb2 = new JButton("��ɫ");
		// �趨���ֹ�����

		// �������
		mp.setBackground(Color.black);
		this.add(mp);
		this.add(jb1, BorderLayout.NORTH);
		this.add(jb2, BorderLayout.SOUTH);

		// è���ڼ���
		Cat mycat1 = new Cat();
		jb1.addActionListener(mycat1);
		jb2.addActionListener(mycat1);
		// ע�����
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		// ָ��action����
		jb1.setActionCommand("��ɫ");
		jb2.setActionCommand("��ɫ");

		// JFrame��������
		this.setTitle("�¼��������");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ���¼�����ķ���
	public void actionPerformed(ActionEvent e) {
		// �ж����ĸ���ť�����
		if (e.getActionCommand().equals("��ɫ")) {
			System.out.println("����˺�ɫ��ť");
			mp.setBackground(Color.BLACK);
		} else if (e.getActionCommand().equals("��ɫ")) {
			System.out.println("����˺�ɫ��ť");
			mp.setBackground(Color.RED);
		} else {
			System.out.println("��֪��");
		}
	}
}

class Cat implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("��ɫ")) {
			System.out.println("CatҲ֪���㰴���˺�ɫ��ť");
		} else if (arg0.getActionCommand().equals("��ɫ")) {
			System.out.println("CatҲ֪���㰴���˺�ɫ��ť");
		} else {
			System.out.println("CatҲ��֪��");
		}
	}
}