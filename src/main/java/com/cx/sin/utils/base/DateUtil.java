package com.cx.sin.utils.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期格式化
 * @author XuXu
 * @version ：2015年1月8日  上午10:31:05
 */
public final class DateUtil {

	public static final String STANDARD_DATE_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

	public static final String STANDARD_DATE_FORMAT_STR = "yyyy-MM-dd";

	public static final SimpleDateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat(
			STANDARD_DATE_TIME_FORMAT_STR);

	/**
	 * 通用日期模式
	 */
	private static final String[] GENERIC_DATE_PATTERNS = {
			STANDARD_DATE_TIME_FORMAT_STR, STANDARD_DATE_FORMAT_STR };

	/**
	 * 日期字符串转化为日期
	 * 
	 * @param src
	 *            日期字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String str) throws ParseException {
		return DateUtils.parseDate(str, GENERIC_DATE_PATTERNS);
	}

	/**
	 * 格式日期时间为系统的标准格式(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDatetime(Date date) {
		return DateFormatUtils.format(date, STANDARD_DATE_TIME_FORMAT_STR);
	}

	/**
	 * 格式日期为系统的标准格式(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDate(Date date) {
		return DateFormatUtils.format(date, STANDARD_DATE_FORMAT_STR);
	}

}
