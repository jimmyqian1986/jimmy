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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Log4j().getLogger(Runable.class.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

		logger.info("" + sdf.format(System.currentTimeMillis()));

	}

}
