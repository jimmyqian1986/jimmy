/**
 * 
 */
package com.jimmy.hsp.class3;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author jimmy
 *
 */
public class Demo1 extends JFrame {

	/**
	 * @param args
	 */
	JButton jb1 = null;
	JButton jb2 = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo1 dm1 = new Demo1();
		dm1.getWindows01();
		dm1.Window010();
	}

	public void getWindows01() {
		// ����һ��button��ť
		jb1 = new JButton("OK");
		jb2 = new JButton("RESET");
		jb1.setSize(100, 100);
		jb2.setSize(100, 100);
		// ���JButton���
		this.add(jb1, BorderLayout.WEST);
		this.add(jb2, BorderLayout.EAST);

		// ���������ñ���
		this.setTitle("Hello World!");

		// ���ô����С,���������ô�С
		this.setSize(500, 500);

		// ���ô����ʼλ��
		this.setLocation(500, 150);

		// ���õ��رմ���ʱ����֤JVMҲ�˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ��ʾ����
		this.setVisible(true);// true��ʾ��false����ʾ
	}

	// �������
	JTextArea jta = null;
	JScrollPane jsp = null;
	JPanel jp1 = null;
	JComboBox jcb = null;
	JTextField jtf = null;
	JButton jb = null;

	// ���캯��
	public void Window010() {
		// �������
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jp1 = new JPanel();
		String[] chatter = { "��ʲ", "����", "Ǯ����", "JACK" };
		jcb = new JComboBox(chatter);
		jtf = new JTextField(10);
		jb = new JButton("����");
		// �趨���ֹ�����

		// �������
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jb);

		this.add(jsp);
		this.add(jp1, BorderLayout.SOUTH);

		// �趨����
		this.setTitle("QQ���촰��");
		this.setIconImage((new ImageIcon("images\\qe.jpg")).getImage());// ���ñ�������ͼ��
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
}
