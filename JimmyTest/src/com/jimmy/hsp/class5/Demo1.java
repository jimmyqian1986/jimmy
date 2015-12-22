/**
 * 
 */
package com.jimmy.hsp.class5;

import java.io.File;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author jimmy
 *
 */
public class Demo1 {
	Log4j log = new Log4j();
	Logger logger = log.getLogger(Demo1.class.getName());
	// ��֤�ַ����Ƿ�Ϊ��ȷ·������������ʽ
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	private boolean flag = false;
	private File file = null;

	// ͨ�� sPath.matches(matches) �����ķ���ֵ�ж��Ƿ���ȷ
	// sPath Ϊ·���ַ���
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo1 dm1 = new Demo1();
		String url = "Demo1.class";
		String path = "";
		path = dm1.getPath(url) + "/test/";
		System.out.println(path);
		File file = new File(path);
		// dm1.deleteFile(file);
		dm1.DeleteFolder(path);
		dm1.createPath(file);
		dm1.createFile(new File(path + "test.t"));
		dm1.listPath(file);
	}

	/**
	 * ����Ŀ¼��Ϣ
	 * 
	 * @param path
	 */
	public void createPath(File file) {
		// �����ļ���

		// �ж��ļ����Ƿ����
		if (file.isDirectory()) {
			logger.info("�ļ��д��ڣ����ܴ���!");
		} else {
			// �����ļ���
			file.mkdir();
		}
	}

	/**
	 * ��ѯĿ¼���ļ���Ϣ
	 * 
	 * @param path
	 */
	public void listPath(File file) {
		// �г�һ���ļ�������������ļ�
		// �ж��ļ������´���
		if (file.isDirectory()) {
			// ���ļ��е��ļ�������lists����
			File lists[] = file.listFiles();
			// ��������
			for (int i = 0; i < lists.length; i++) {
				// ����ļ����������ļ��ļ���
				logger.info("��ʾ���ļ�����" + lists[i].getName());
			}
		}
	}

	/**
	 * �����ļ���Ϣ
	 * 
	 * @param path
	 * @param filename
	 */
	public void createFile(File file) {
		// �����ļ��ʹ����ļ���
		// �ж��ļ��Ƿ����
		if (!file.exists()) {
			// ���Դ���
			try {
				file.createNewFile();// ����һ�����ļ�
			} catch (Exception e) {
				e.printStackTrace();
			}
			// �õ��ļ���·��
			logger.info("�ļ�·��" + file.getAbsolutePath());
			// �õ��ļ��Ĵ�С,�ֽ���
			logger.info("�ļ��Ĵ�С" + file.length());
		} else {
			logger.info("�ļ����ڣ����ܴ���!");
		}
	}

	public void deleteFile(File file) {
		// �ж��ļ��Ƿ����
		if (file.exists()) {
			// �õ��ļ���·��
			logger.info("�ļ�·��" + file.getAbsolutePath());
			// �õ��ļ��Ĵ�С,�ֽ���
			logger.info("�ļ��Ĵ�С" + file.length());
			try {
				file.delete();// ����һ�����ļ�
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			logger.info("�ļ������ڣ����܄h��!");
		}
	}

	public String getPath(String url) {
		logger.info(url);
		logger.info(Thread.currentThread().getContextClassLoader()
				.getResource("")); // �����Դ�ļ�(.class�ļ�)����·��
		logger.info(ClassLoader.getSystemResource(""));
		logger.info(Demo1.class.getClassLoader().getResource(""));
		logger.info(Demo1.class.getResource("/"));
		logger.info(Demo1.class.getResource("")); // ��õ�ǰ������·��
		logger.info(System.getProperty("user.dir")); // �����Ŀ��Ŀ¼�ľ���·��
		logger.info(System.getProperty("java.class.path")); // �õ���·���Ͱ�·��
		String u_url = System.getProperty("user.dir");
		logger.info(u_url.toString());
		return u_url.toString();
	}

	/**
	 * ����·��ɾ��ָ����Ŀ¼���ļ������۴������
	 *
	 * @param sPath
	 *            Ҫɾ����Ŀ¼���ļ�
	 * @return ɾ���ɹ����� true�����򷵻� false��
	 */
	public boolean DeleteFolder(String sPath) {
		flag = false;
		file = new File(sPath);
		// �ж�Ŀ¼���ļ��Ƿ����
		if (!file.exists()) { // �����ڷ��� false
			logger.info(sPath + "������");
			return flag;

		} else {
			// �ж��Ƿ�Ϊ�ļ�
			if (file.isFile()) { // Ϊ�ļ�ʱ����ɾ���ļ�����
				logger.info(sPath + " :Delete ing");
				return deleteFile(sPath);
			} else { // ΪĿ¼ʱ����ɾ��Ŀ¼����
				logger.info(sPath + " :Delete ing");
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * ɾ�������ļ�
	 * 
	 * @param sPath
	 *            ��ɾ���ļ����ļ���
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
	 */
	public boolean deleteFile(String sPath) {
		flag = false;
		file = new File(sPath);
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		if (file.isFile() && file.exists()) {
			logger.info(sPath + " :Delete ing");
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�
	 * 
	 * @param sPath
	 *            ��ɾ��Ŀ¼���ļ�·��
	 * @return Ŀ¼ɾ���ɹ�����true�����򷵻�false
	 */
	public boolean deleteDirectory(String sPath) {
		// ���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// ���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// ɾ���ļ����µ������ļ�(������Ŀ¼)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// ɾ�����ļ�
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // ɾ����Ŀ¼
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// ɾ����ǰĿ¼
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}
