/**
 * 
 */
package com.jimmy.hsp.class5;

/**
 * �ҵļ��±�(����+����)
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
	// �������
	JTextArea jta = null;// �ı���
	// �˵���
	JMenuBar jmb = null;
	// �����һ��JMenu
	JMenu jm1 = null;
	// ����JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;

	public static void main(String[] args) {
		NoteBook io = new NoteBook();
	}

	// ���캯��
	public NoteBook() {
		// �������
		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("�ļ�(F)");
		// �������Ƿ�
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("��(O)");
		// open��ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");

		jmi2 = new JMenuItem("����(S)");
		// save����ע�����
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");

		jmi3 = new JMenuItem("�˳�(X)");
		// exit�˳�ע�����
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");

		// ���뵽�˵�
		this.setJMenuBar(jmb);
		// ��jm1�ŵ�jmb
		jmb.add(jm1);
		// �� jmi1����jm1
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);

		// ���ý��������(Ĭ��BorderLayout�߽粼�ֹ�����)

		// �������
		this.add(jta);

		// ����JFrame���
		this.setTitle("���±������빦��");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// �ж�"��"�˵���ѡ��
		if (e.getActionCommand().equals("open")) {
			/**
			 * ¡���Ƽ�JFileChooser���
			 */
			// ����һ���ļ�ѡ�����
			JFileChooser jfc = new JFileChooser();
			// ��������
			jfc.setDialogTitle("��ѡ��Ҫ�򿪵��ļ�...");
			// ʹ��Ĭ�Ϸ�ʽ
			jfc.showOpenDialog(null);
			// ��ʾ
			jfc.setVisible(true);

			// �õ��û�ѡ����ļ�ȫ(����)·��
			String filename = jfc.getSelectedFile().getAbsolutePath();

			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				// ���ļ��ж�ȡ��Ϣ����ʾ��jta(JTextArea)��
				String s = "";
				String allCon = "";
				while ((s = br.readLine()) != null) {
					allCon += s + "\r\n";// "\r\n"��ʾ�ı�ʱ���ļ���ԭ�еĸ�ʽ��ʾ��jta��
				}
				// ���õ�jta����
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
			}// �ж�"����"�˵���ѡ��
		} else if (e.getActionCommand().equals("save")) {
			// ��������Ի���
			JFileChooser jfc = new JFileChooser();
			// ��������
			jfc.setDialogTitle("���ļ����浽...");
			// ʹ��Ĭ�Ϸ�ʽ
			jfc.showSaveDialog(null);
			// ��ʾ
			jfc.setVisible(true);

			// �õ��û�ϣ�����ļ����浽�δ����ļ�ȫ·��
			String file = jfc.getSelectedFile().getAbsolutePath();

			// ׼��д�뵽ָ���ļ�
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				// ��JtextArea�е����������ָ���ļ���
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