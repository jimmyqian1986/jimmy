package com.jimmy.hsp.class5;

/**
 *
 * File��Ļ����÷�
 * io��--�ļ��ֽ���
 * FileInputStream���ʹ��
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

public class Io02 {
	Log4j log = new Log4j();
	Logger logger = log.getLogger(Io02.class.getName());

	public static void main(String[] args) {
		// �õ�һ���ļ�����fָ��e:\ff\hsp.txt�ļ�
		String file = System.getProperty("user.dir") + "/logs/2.txt";
		File f = new File(file);
		Io02 io02 = new Io02();
		io02.deletLastLine(new File(System.getProperty("user.dir")
				+ "/logs/app1.log"), 100);
		try {
			io02.fileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		io02.fileInputStream(f);

	}

	/**
	 * InputStream��ȡ�ļ���Ϣ
	 * 
	 * @param file
	 */
	public void fileInputStream(File file) {
		FileInputStream fis = null;
		try {
			// ��ΪFileû�ж�д��������������Ҫʹ��InputStream��
			fis = new FileInputStream(file);
			// ����һ���ֽ����飬�൱�ڻ���
			byte[] bytes = new byte[1024];
			int n = 0;// �õ�ʵ�ʶ�ȡ�����ֽ���
			// ѭ����ȡ
			while ((n = fis.read(bytes)) != -1) {
				// ���ֽ�ת��String
				String s = new String(bytes, 0, n);
				logger.info(s);
			}
		} catch (Exception e) {
			logger.error("��ȡ�ļ����쳣");
			e.printStackTrace();
		} finally {
			// �ر��ļ����������finally������
			try {
				fis.close();
			} catch (Exception e) {
				logger.error("�ر��ļ����쳣");
				e.printStackTrace();
			}
		}
	}

	/**
	 * д�����ݵ��ļ���
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void fileOutputStream(File file) throws FileNotFoundException {
		// File f=new File("e:\\ff\\ss.txt");//ֱ�Ӹ���дͬһ���ļ�
		// �ֽ������
		FileOutputStream fos = null;
		if (file.exists()) {
			logger.info("�ļ��Ѵ���");
			fos = new FileOutputStream(file, true);
		} else {
			fos = new FileOutputStream(file, false);
		}
		try {

			String s = "hello,world!\r\n";
			String s1 = "�й���";
			fos.write(s.getBytes());
			fos.write(s1.getBytes());
		} catch (Exception e) {
			logger.error("д���ļ����쳣");
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * ɾ���ļ����һ������
	 * 
	 * @param file
	 */
	public void deletLastLine(File file, int num) {

		String str = null;
		BufferedReader bre = null;
		OutputStreamWriter pw = null;// ����һ����

		// String file = "D:/test/test.txt";
		try {
			bre = new BufferedReader(new FileReader(file));// ��ʱ��ȡ����bre���������ļ��Ļ�����

			pw = new OutputStreamWriter(new FileOutputStream(file), "GBK");
			// ȷ����������ļ��ͱ����ʽ���˹��̴����ˡ�test.txt��ʵ��
			for (int i = 0; i < num; i++) {
				while ((str = bre.readLine()) != null) // �ж����һ�в����ڣ�Ϊ�ս���ѭ��
				{
					if (str.indexOf("�ų�") < 0) {// �ж��Ƿ���Ҫ����
						pw.write(str);// ��Ҫд���ļ������ݣ����Զ��write
					}
					bre.close();// �ر���
					pw.close();// �ر���
				}
				if (str == null || str.length() == 0) {
					i = num;
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("��֧�ֵı����ʽ��������GBK�����ʽ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error("û���ҵ��ļ�");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IO��ȡ�쳣");
			e.printStackTrace();
		}
	}

	/**
	 * File��Ļ����÷� io��--�ļ��ֽ��� ͼƬ����--FileInputStream���� FileOutputStream��
	 */

	public void copyFile(File fromFileName, File toFileName) {
		// �Ƚ�ͼƬ���뵽�ڴ棬�ٽ��ڴ��е�ͼƬд�뵽ĳ���ļ�
		// ��Ϊ�������ļ�ֻ����ʹ���ֽ���������
		// ������
		FileInputStream fis = null;
		// �����
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(fromFileName);
			fos = new FileOutputStream(toFileName);
			byte buf[] = new byte[1024];
			// @SuppressWarnings("unused")
			int n = 0;// ��¼ʵ�ʶ�ȡ�����ֽ���
			// ѭ����ȡͼƬ
			while ((n = fis.read(buf)) != -1) {
				// �����ָ���ļ�
				fos.write(buf);
				logger.info("��ȡ���ֽ����ǣ�" + n);
			}

		} catch (Exception e) {
			logger.error("�����ļ��쳣");
			e.printStackTrace();
		} finally {
			// һ��Ҫ�رմ򿪵��ļ���
			try {
				fis.close();
				fos.close();
			} catch (Exception e) {
				logger.error("�ر��������쳣");
				e.printStackTrace();
			}
		}
	}

	/**
	 * File��Ļ����÷� io��--�ļ��ַ�����ֻ��������ȫΪ�ַ����ļ� TXT�ļ�����--FileReader���� FileWriter��
	 */

	public void fileReaderWriter(File reader, File writer) {
		// �ļ�ȡ���ַ�������(������)
		FileReader fr = null;
		// д�뵽�ļ�(�����)
		FileWriter fw = null;
		try {
			// ����fr����
			fr = new FileReader(reader);
			// �����������
			fw = new FileWriter(writer);
			// �����ַ�����
			char c[] = new char[1024];
			int n = 0;
			// ���뵽�ڴ�
			while ((n = fr.read(c)) != -1) {
				// ����̨���TXT�ļ�����
				String s = new String(c, 0, n);
				logger.info(s);
				fw.write(c, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * File��Ļ����÷� io��--�����ַ��� BufferedReader����BufferedWriter��
	 */

	public void bufferedReaderWriter(File reader, File writer) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			// �ȴ���FileReader����
			FileReader fr = new FileReader(reader);
			br = new BufferedReader(fr);

			// ����FileWriter����
			FileWriter fw = new FileWriter(writer);
			bw = new BufferedWriter(fw);

			// ѭ����ȡ
			String s = "";
			while ((s = br.readLine()) != null) {
				// ���������
				bw.write(s + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
