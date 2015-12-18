/**
 * 
 */
package com.jimmy.hsp.class4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jimmy
 *
 */
public class DateFormat {
	public SimpleDateFormat YYYYMMDD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf;
	}

	public SimpleDateFormat YYYY_MM_DD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf;
	}

	public SimpleDateFormat YYYYMMDDwithBlank(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
		return sdf;
	}
}
