/**
 * 
 */
package com.jimmy.hsp.class5;

/**
 * 我的记事本(界面+功能)
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class NoteBook extends JFrame implements ActionListener {
	// 定义组件
	JTextArea jta = null;// 文本框
	// 菜单条
	JMenuBar jmb = null;
	// 定义第一个JMenu
	JMenu jm1 = null;
	// 定义JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;

	public static void main(String[] args) {
		NoteBook io = new NoteBook();
	}

	// 构造函数
	public NoteBook() {
		// 创建组件
		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("文件(F)");
		// 设置助记符
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("打开(O)");
		// open打开注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");

		jmi2 = new JMenuItem("保存(S)");
		// save保存注册监听
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");

		jmi3 = new JMenuItem("退出(X)");
		// exit退出注册监听
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");

		// 加入到菜单
		this.setJMenuBar(jmb);
		// 把jm1放到jmb
		jmb.add(jm1);
		// 把 jmi1放入jm1
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);

		// 设置界面管理器(默认BorderLayout边界布局管理器)

		// 加入组件
		this.add(jta);

		// 设置JFrame面板
		this.setTitle("记事本界面与功能");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// 判断"打开"菜单被选中
		if (e.getActionCommand().equals("open")) {
			/**
			 * 隆重推荐JFileChooser组件
			 */
			// 创建一个文件选择组件
			JFileChooser jfc = new JFileChooser();
			// 设置名字
			jfc.setDialogTitle("请选择要打开的文件...");
			// 使用默认方式
			jfc.showOpenDialog(null);
			// 显示
			jfc.setVisible(true);

			// 得到用户选择的文件全(绝对)路径
			String filename = jfc.getSelectedFile().getAbsolutePath();

			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				// 从文件中读取信息并显示到jta(JTextArea)中
				String s = "";
				String allCon = "";
				while ((s = br.readLine()) != null) {
					allCon += s + "\r\n";// "\r\n"显示文本时将文件中原有的格式显示到jta中
				}
				// 放置到jta即可
				jta.setText(allCon);
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					br.close();
					fr.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}// 判断"保存"菜单被选中
		} else if (e.getActionCommand().equals("save")) {
			// 创建保存对话框
			JFileChooser jfc = new JFileChooser();
			// 设置名字
			jfc.setDialogTitle("将文件保存到...");
			// 使用默认方式
			jfc.showSaveDialog(null);
			// 显示
			jfc.setVisible(true);

			// 得到用户希望把文件保存到何处，文件全路径
			String file = jfc.getSelectedFile().getAbsolutePath();

			// 准备写入到指定文件
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				// 将JtextArea中的内容输出到指定文件中
				bw.write(this.jta.getText());
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					bw.close();
					fw.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		} else if (e.getActionCommand().equals("exit")) {
			System.exit(0);
		}
	}
}