/**
 * 
 */
package com.jimmy.hsp.class4;

import java.text.SimpleDateFormat;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author jimmy
 *
 */
public class Demo1 {

	/**
	 * @param args
	 */
	Runable rab = new Runable();
	Thread t = new Thread(rab);
	Thread t1 = new Thread(rab);
	Thread t2 = new Thread(rab);
	Thread t3 = new Thread(rab);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Log4j().getLogger(Runable.class.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		logger.info("" + sdf.format(System.currentTimeMillis()));
		Demo1 dm1 = new Demo1();
		dm1.thread1();
		dm1.thread2();
	}

	public void thread1() {
		rab.setThreadName(t.getName());
		t.run();
		t1.run();
		t2.run();
	}

	DateFormat dft = new DateFormat();

	public void thread2() {

		dft.thread3(rab);
	}
}
