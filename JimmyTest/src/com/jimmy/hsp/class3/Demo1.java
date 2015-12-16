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
		// 创建一个button按钮
		jb1 = new JButton("OK");
		jb2 = new JButton("RESET");
		jb1.setSize(100, 100);
		jb2.setSize(100, 100);
		// 添加JButton组件
		this.add(jb1, BorderLayout.WEST);
		this.add(jb2, BorderLayout.EAST);

		// 给窗体设置标题
		this.setTitle("Hello World!");

		// 设置窗体大小,按像素设置大小
		this.setSize(500, 500);

		// 设置窗体初始位置
		this.setLocation(500, 150);

		// 设置当关闭窗口时，保证JVM也退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 显示窗体
		this.setVisible(true);// true显示，false不显示
	}

	// 定义组件
	JTextArea jta = null;
	JScrollPane jsp = null;
	JPanel jp1 = null;
	JComboBox jcb = null;
	JTextField jtf = null;
	JButton jb = null;

	// 构造函数
	public void Window010() {
		// 构建组件
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jp1 = new JPanel();
		String[] chatter = { "布什", "拉登", "钱金敏", "JACK" };
		jcb = new JComboBox(chatter);
		jtf = new JTextField(10);
		jb = new JButton("发送");
		// 设定布局管理器

		// 加入组件
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jb);

		this.add(jsp);
		this.add(jp1, BorderLayout.SOUTH);

		// 设定窗体
		this.setTitle("QQ聊天窗口");
		this.setIconImage((new ImageIcon("images\\qe.jpg")).getImage());// 设置标题栏内图标
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
}
