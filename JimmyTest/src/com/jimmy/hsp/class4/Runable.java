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
public class Runable implements Runnable {
	Logger logger = new Log4j().getLogger(Runable.class.getName());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSS sss");

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("" + sdf.format(System.currentTimeMillis()));
	}
}
