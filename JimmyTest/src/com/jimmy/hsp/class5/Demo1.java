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
	// 验证字符串是否为正确路径名的正则表达式
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	private boolean flag = false;
	private File file = null;

	// 通过 sPath.matches(matches) 方法的返回值判断是否正确
	// sPath 为路径字符串
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
	 * 创建目录信息
	 * 
	 * @param path
	 */
	public void createPath(File file) {
		// 创建文件夹

		// 判断文件夹是否存在
		if (file.isDirectory()) {
			logger.info("文件夹存在，不能创建!");
		} else {
			// 创建文件夹
			file.mkdir();
		}
	}

	/**
	 * 查询目录下文件信息
	 * 
	 * @param path
	 */
	public void listPath(File file) {
		// 列出一个文件夹下面的所有文件
		// 判断文件夹是事存在
		if (file.isDirectory()) {
			// 将文件夹的文件，传给lists数组
			File lists[] = file.listFiles();
			// 遍历数组
			for (int i = 0; i < lists.length; i++) {
				// 输出文件夹下所有文件文件名
				logger.info("显示出文件名是" + lists[i].getName());
			}
		}
	}

	/**
	 * 创建文件信息
	 * 
	 * @param path
	 * @param filename
	 */
	public void createFile(File file) {
		// 创建文件和创建文件夹
		// 判断文件是否存在
		if (!file.exists()) {
			// 可以创建
			try {
				file.createNewFile();// 创建一个新文件
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 得到文件的路径
			logger.info("文件路径" + file.getAbsolutePath());
			// 得到文件的大小,字节数
			logger.info("文件的大小" + file.length());
		} else {
			logger.info("文件存在，不能创建!");
		}
	}

	public void deleteFile(File file) {
		// 判断文件是否存在
		if (file.exists()) {
			// 得到文件的路径
			logger.info("文件路径" + file.getAbsolutePath());
			// 得到文件的大小,字节数
			logger.info("文件的大小" + file.length());
			try {
				file.delete();// 创建一个新文件
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			logger.info("文件不存在，不能刪除!");
		}
	}

	public String getPath(String url) {
		logger.info(url);
		logger.info(Thread.currentThread().getContextClassLoader()
				.getResource("")); // 获得资源文件(.class文件)所在路径
		logger.info(ClassLoader.getSystemResource(""));
		logger.info(Demo1.class.getClassLoader().getResource(""));
		logger.info(Demo1.class.getResource("/"));
		logger.info(Demo1.class.getResource("")); // 获得当前类所在路径
		logger.info(System.getProperty("user.dir")); // 获得项目根目录的绝对路径
		logger.info(System.getProperty("java.class.path")); // 得到类路径和包路径
		String u_url = System.getProperty("user.dir");
		logger.info(u_url.toString());
		return u_url.toString();
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 *
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public boolean DeleteFolder(String sPath) {
		flag = false;
		file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			logger.info(sPath + "不存在");
			return flag;

		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				logger.info(sPath + " :Delete ing");
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				logger.info(sPath + " :Delete ing");
				return deleteDirectory(sPath);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		flag = false;
		file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			logger.info(sPath + " :Delete ing");
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}
