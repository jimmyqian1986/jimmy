/**
 * @author jimmy
 */
package com.jimmy.hsp.class4;

import java.text.SimpleDateFormat;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author jimmy
 *
 */
public class Runable implements Runnable {
	Logger logger = new Log4j().getLogger(Runable.class.getName());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	int nums = 1000;
	String threadName = "";

	/**
	 * @return the threadName
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName
	 *            the threadName to set
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("" + sdf.format(System.currentTimeMillis()));
		while (true) {
			synchronized (new DateFormat()) {
				if (nums > 0) {
					// 显示售票信息
					// Thread.currentThread().getName()得到当前线程的名字
					logger.info(getThreadName() + "正在售出第" + nums + "张票");
					nums--;
				} else {
					// 售票结束
					break;
				}
			}
		}
	}
}
