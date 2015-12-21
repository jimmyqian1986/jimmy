package com.jimmy.hsp.class5;

/**
 *
 * File类的基本用法
 * io流--文件字节流
 * FileInputStream类的使用
 */
import java.io.File;
import java.io.FileInputStream;

public class Io02 {
	public static void main(String[] args) {
		// 得到一个文件对象，f指向e:\ff\hsp.txt文件
		File f = new File("e:\\ff\\hsp.txt");
		FileInputStream fis = null;
		try {
			// 因为File没有读写的能力，所以需要使用InputStream类
			fis = new FileInputStream(f);
			// 定义一个字节数组，相当于缓存
			byte[] bytes = new byte[1024];
			int n = 0;// 得到实际读取到的字节数
			// 循环读取
			while ((n = fis.read(bytes)) != -1) {
				// 把字节转成String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭文件流必需放在finally语句块中
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}