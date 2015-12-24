/*
 * MainFrm.java
 *
 * Created on __DATE__, __TIME__
 */

package com.java1234.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author __USER__
 */
public class MainFrm extends javax.swing.JFrame {

	/** Creates new form MainFrm */
	public MainFrm() {
		initComponents();
		// 设置最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		table = new javax.swing.JDesktopPane();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu3 = new javax.swing.JMenu();
		jmiBookTypeAdd = new javax.swing.JMenuItem();
		jmiBookTypeManage = new javax.swing.JMenuItem();
		jMenu4 = new javax.swing.JMenu();
		jmiBookAdd = new javax.swing.JMenuItem();
		jmiBookManage = new javax.swing.JMenuItem();
		jmiExit = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jmiaboutJava1234 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u56fe\u4e66\u7ba1\u7406\u7cfb\u7edf\u4e3b\u754c\u9762");

		jMenu1.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir")
				+ "\\images\\base.png")); // NOI18N
		jMenu1.setText("\u57fa\u672c\u6570\u636e\u7ef4\u62a4");

		jMenu3.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir")
				+ "\\images\\bookTypeManager.png")); // NOI18N
		jMenu3.setText("\u56fe\u4e66\u7c7b\u522b\u7ba1\u7406");

		jmiBookTypeAdd.setIcon(new javax.swing.ImageIcon(System
				.getProperty("user.dir") + "\\images\\add.png")); // NOI18N
		jmiBookTypeAdd.setText("\u6dfb\u52a0\u56fe\u4e66\u7c7b\u522b");
		jmiBookTypeAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiBookTypeAddActionPerformed(evt);
			}
		});
		jMenu3.add(jmiBookTypeAdd);

		jmiBookTypeManage.setIcon(new javax.swing.ImageIcon(System
				.getProperty("user.dir") + "\\images\\edit.png")); // NOI18N
		jmiBookTypeManage.setText("\u56fe\u4e66\u7c7b\u522b\u7ef4\u62a4");
		jmiBookTypeManage
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jmiBookTypeManageActionPerformed(evt);
					}
				});
		jMenu3.add(jmiBookTypeManage);

		jMenu1.add(jMenu3);

		jMenu4.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir")
				+ "\\images\\bookManager.png")); // NOI18N
		jMenu4.setText("\u56fe\u4e66\u7ba1\u7406");

		jmiBookAdd.setIcon(new javax.swing.ImageIcon(System
				.getProperty("user.dir") + "\\images\\add.png")); // NOI18N
		jmiBookAdd.setText("\u6dfb\u52a0\u56fe\u4e66");
		jmiBookAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiBookAddActionPerformed(evt);
			}
		});
		jMenu4.add(jmiBookAdd);

		jmiBookManage.setIcon(new javax.swing.ImageIcon(System
				.getProperty("user.dir") + "\\images\\edit.png")); // NOI18N
		jmiBookManage.setText("\u56fe\u4e66\u7ef4\u62a4");
		jmiBookManage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiBookManageActionPerformed(evt);
			}
		});
		jMenu4.add(jmiBookManage);

		jMenu1.add(jMenu4);

		jmiExit.setIcon(new javax.swing.ImageIcon(System
				.getProperty("user.dir") + "\\images\\exit.png")); // NOI18N
		jmiExit.setText("\u9000\u51fa\u7cfb\u7edf");
		jmiExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiExitActionPerformed(evt);
			}
		});
		jMenu1.add(jmiExit);

		jMenuBar1.add(jMenu1);

		jMenu2.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir")
				+ "\\images\\about.png")); // NOI18N
		jMenu2.setText("\u5173\u4e8e\u6211\u4eec");

		jmiaboutJava1234.setIcon(new javax.swing.ImageIcon(System
				.getProperty("user.dir") + "\\images\\me.png")); // NOI18N
		jmiaboutJava1234.setText("\u5173\u4e8eJava1234");
		jmiaboutJava1234.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiaboutJava1234ActionPerformed(evt);
			}
		});
		jMenu2.add(jmiaboutJava1234);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(table,
				javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(table,
				javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void jmiaboutJava1234ActionPerformed(java.awt.event.ActionEvent evt) {
		AboutJava1234InterFrm aboutJava1234InterFrm = new AboutJava1234InterFrm();
		aboutJava1234InterFrm.setVisible(true);
		this.table.add(aboutJava1234InterFrm);
	}

	private void jmiBookManageActionPerformed(java.awt.event.ActionEvent evt) {
		BookManageInterFrm BookManageInterFrm = new BookManageInterFrm();
		BookManageInterFrm.setVisible(true);
		this.table.add(BookManageInterFrm);
	}

	private void jmiBookAddActionPerformed(java.awt.event.ActionEvent evt) {
		BookAddInterFrm BookAddInterFrm = new BookAddInterFrm();
		BookAddInterFrm.setVisible(true);
		this.table.add(BookAddInterFrm);
	}

	private void jmiBookTypeManageActionPerformed(java.awt.event.ActionEvent evt) {
		BookTypeManageInterFrm BookTypeManageInterFrm = new BookTypeManageInterFrm();
		BookTypeManageInterFrm.setVisible(true);
		this.table.add(BookTypeManageInterFrm);
	}

	private void jmiBookTypeAddActionPerformed(java.awt.event.ActionEvent evt) {
		BookTypeInterFrm bookTypeInterFrm = new BookTypeInterFrm();
		bookTypeInterFrm.setVisible(true);
		this.table.add(bookTypeInterFrm);
	}

	private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
		if (result == 0) {
			this.dispose();
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrm().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jmiBookAdd;
	private javax.swing.JMenuItem jmiBookManage;
	private javax.swing.JMenuItem jmiBookTypeAdd;
	private javax.swing.JMenuItem jmiBookTypeManage;
	private javax.swing.JMenuItem jmiExit;
	private javax.swing.JMenuItem jmiaboutJava1234;
	private javax.swing.JDesktopPane table;
	// End of variables declaration//GEN-END:variables

}