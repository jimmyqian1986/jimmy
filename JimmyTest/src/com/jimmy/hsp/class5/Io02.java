package com.jimmy.hsp.class5;

/**
 *
 * File类的基本用法
 * io流--文件字节流
 * FileInputStream类的使用
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
		// 得到一个文件对象，f指向e:\ff\hsp.txt文件
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
	 * InputStream读取文件信息
	 * 
	 * @param file
	 */
	public void fileInputStream(File file) {
		FileInputStream fis = null;
		try {
			// 因为File没有读写的能力，所以需要使用InputStream类
			fis = new FileInputStream(file);
			// 定义一个字节数组，相当于缓存
			byte[] bytes = new byte[1024];
			int n = 0;// 得到实际读取到的字节数
			// 循环读取
			while ((n = fis.read(bytes)) != -1) {
				// 把字节转成String
				String s = new String(bytes, 0, n);
				logger.info(s);
			}
		} catch (Exception e) {
			logger.error("读取文件流异常");
			e.printStackTrace();
		} finally {
			// 关闭文件流必需放在finally语句块中
			try {
				fis.close();
			} catch (Exception e) {
				logger.error("关闭文件流异常");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入数据到文件中
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void fileOutputStream(File file) throws FileNotFoundException {
		// File f=new File("e:\\ff\\ss.txt");//直接覆盖写同一个文件
		// 字节输出流
		FileOutputStream fos = null;
		if (file.exists()) {
			logger.info("文件已存在");
			fos = new FileOutputStream(file, true);
		} else {
			fos = new FileOutputStream(file, false);
		}
		try {

			String s = "hello,world!\r\n";
			String s1 = "中国人";
			fos.write(s.getBytes());
			fos.write(s1.getBytes());
		} catch (Exception e) {
			logger.error("写入文件流异常");
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
	 * 删除文件最后一行数据
	 * 
	 * @param file
	 */
	public void deletLastLine(File file, int num) {

		String str = null;
		BufferedReader bre = null;
		OutputStreamWriter pw = null;// 定义一个流

		// String file = "D:/test/test.txt";
		try {
			bre = new BufferedReader(new FileReader(file));// 此时获取到的bre就是整个文件的缓存流

			pw = new OutputStreamWriter(new FileOutputStream(file), "GBK");
			// 确认流的输出文件和编码格式，此过程创建了“test.txt”实例
			for (int i = 0; i < num; i++) {
				while ((str = bre.readLine()) != null) // 判断最后一行不存在，为空结束循环
				{
					if (str.indexOf("排除") < 0) {// 判断是否需要舍弃
						pw.write(str);// 将要写入文件的内容，可以多次write
					}
					bre.close();// 关闭流
					pw.close();// 关闭流
				}
				if (str == null || str.length() == 0) {
					i = num;
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持的编码格式，请输入GBK编码格式");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error("没有找到文件");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("IO读取异常");
			e.printStackTrace();
		}
	}

	/**
	 * File类的基本用法 io流--文件字节流 图片拷贝--FileInputStream类与 FileOutputStream类
	 */

	public void copyFile(File fromFileName, File toFileName) {
		// 先将图片读入到内存，再将内存中的图片写入到某个文件
		// 因为二进制文件只能拿使用字节流来处理
		// 输入流
		FileInputStream fis = null;
		// 输出流
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(fromFileName);
			fos = new FileOutputStream(toFileName);
			byte buf[] = new byte[1024];
			// @SuppressWarnings("unused")
			int n = 0;// 记录实际读取到的字节数
			// 循环读取图片
			while ((n = fis.read(buf)) != -1) {
				// 输出到指定文件
				fos.write(buf);
				logger.info("获取的字节数是：" + n);
			}

		} catch (Exception e) {
			logger.error("复制文件异常");
			e.printStackTrace();
		} finally {
			// 一定要关闭打开的文件流
			try {
				fis.close();
				fos.close();
			} catch (Exception e) {
				logger.error("关闭数据流异常");
				e.printStackTrace();
			}
		}
	}

	/**
	 * File类的基本用法 io流--文件字符流，只能用于完全为字符的文件 TXT文件拷贝--FileReader类与 FileWriter类
	 */

	public void fileReaderWriter(File reader, File writer) {
		// 文件取出字符流对象(输入流)
		FileReader fr = null;
		// 写入到文件(输出流)
		FileWriter fw = null;
		try {
			// 创建fr对象
			fr = new FileReader(reader);
			// 创建输出对象
			fw = new FileWriter(writer);
			// 创建字符数组
			char c[] = new char[1024];
			int n = 0;
			// 读入到内存
			while ((n = fr.read(c)) != -1) {
				// 控制台输出TXT文件内容
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
	 * File类的基本用法 io流--缓冲字符流 BufferedReader类与BufferedWriter类
	 */

	public void bufferedReaderWriter(File reader, File writer) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			// 先创建FileReader对象
			FileReader fr = new FileReader(reader);
			br = new BufferedReader(fr);

			// 创建FileWriter对象
			FileWriter fw = new FileWriter(writer);
			bw = new BufferedWriter(fw);

			// 循环读取
			String s = "";
			while ((s = br.readLine()) != null) {
				// 输出到磁盘
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
