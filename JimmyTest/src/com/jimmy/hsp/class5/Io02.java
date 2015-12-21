package com.jimmy.hsp.class5;

/**
 *
 * File��Ļ����÷�
 * io��--�ļ��ֽ���
 * FileInputStream���ʹ��
 */
import java.io.File;
import java.io.FileInputStream;

public class Io02 {
	public static void main(String[] args) {
		// �õ�һ���ļ�����fָ��e:\ff\hsp.txt�ļ�
		File f = new File("e:\\ff\\hsp.txt");
		FileInputStream fis = null;
		try {
			// ��ΪFileû�ж�д��������������Ҫʹ��InputStream��
			fis = new FileInputStream(f);
			// ����һ���ֽ����飬�൱�ڻ���
			byte[] bytes = new byte[1024];
			int n = 0;// �õ�ʵ�ʶ�ȡ�����ֽ���
			// ѭ����ȡ
			while ((n = fis.read(bytes)) != -1) {
				// ���ֽ�ת��String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر��ļ����������finally������
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}