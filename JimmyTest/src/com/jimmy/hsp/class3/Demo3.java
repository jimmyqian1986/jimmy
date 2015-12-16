/**
 * 
 */
package com.jimmy.hsp.class3;

/**
 * @author jimmy
 *
 */
/**
 * 功能:事件处理机制
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo3 extends JFrame implements ActionListener {
	// 定义组件
	JPanel mp = null;
	JButton jb1, jb2;

	public static void main(String[] args) {
		Demo3 win = new Demo3();
	}

	// 构造函数
	public Demo3() {
		// 创建组件
		mp = new JPanel();
		jb1 = new JButton("黑色");
		jb2 = new JButton("红色");
		// 设定布局管理器

		// 加入组件
		mp.setBackground(Color.black);
		this.add(mp);
		this.add(jb1, BorderLayout.NORTH);
		this.add(jb2, BorderLayout.SOUTH);

		// 猫类在监听
		Cat mycat1 = new Cat();
		jb1.addActionListener(mycat1);
		jb2.addActionListener(mycat1);
		// 注册监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		// 指定action命令
		jb1.setActionCommand("黑色");
		jb2.setActionCommand("红色");

		// JFrame窗体设置
		this.setTitle("事件处理机制");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 对事件处理的方法
	public void actionPerformed(ActionEvent e) {
		// 判断是哪个按钮被点击
		if (e.getActionCommand().equals("黑色")) {
			System.out.println("点击了黑色按钮");
			mp.setBackground(Color.BLACK);
		} else if (e.getActionCommand().equals("红色")) {
			System.out.println("点击了红色按钮");
			mp.setBackground(Color.RED);
		} else {
			System.out.println("不知道");
		}
	}
}

class Cat implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("黑色")) {
			System.out.println("Cat也知道你按下了黑色按钮");
		} else if (arg0.getActionCommand().equals("红色")) {
			System.out.println("Cat也知道你按下了红色按钮");
		} else {
			System.out.println("Cat也不知道");
		}
	}
}