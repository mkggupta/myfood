package com.myfood.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtils extends java.lang.Object {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DD_MMM_YYYY = "dd-MMM-yy";
	public static String getCurrentGMT() {

		java.util.TimeZone tz_gmt = java.util.TimeZone.getTimeZone("GMT");
		java.text.SimpleDateFormat sdformat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		sdformat.setTimeZone(tz_gmt);
		return sdformat.format(new java.util.GregorianCalendar(tz_gmt)
				.getTime());

	}

	public static Date getCurrentGMTDate() {
		Date gmtDate;
		java.util.TimeZone tz_gmt = java.util.TimeZone.getTimeZone("GMT");
		gmtDate = new java.util.GregorianCalendar(tz_gmt).getTime();
		return gmtDate;

	}

	public static java.util.Date convertStringToDate(
			java.lang.String dateToBeConverted) {

		try {
			if (null == dateToBeConverted || dateToBeConverted.length() == 0) {
				throw new java.lang.IllegalArgumentException(
						"The date to be converted was found to be null @ TimeUtils:convertStringToDate");
			}
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd/mm/yyyy");
			return sdf.parse(dateToBeConverted);
		} catch (java.text.ParseException exception) {
			java.lang.RuntimeException runtimeException = new java.lang.RuntimeException();
			runtimeException.setStackTrace(exception.getStackTrace());
			throw runtimeException;
		}

	}

	public static String getIfAMorPM() {
		java.util.TimeZone tz_gmt = java.util.TimeZone.getTimeZone("GMT");
		java.text.SimpleDateFormat sdformat = new java.text.SimpleDateFormat(
				"h:mm a");
		sdformat.setTimeZone(tz_gmt);
		if (sdformat.format(new java.util.GregorianCalendar(tz_gmt).getTime())
				.endsWith("AM")) {
			return "AM";
		}
		return "PM";
	}

	public static String computeDirToWrite(String repositoryURL) {
		try {
			String currentTime = TimeUtils.getCurrentGMT();
			System.out.println("Current Time :: " + currentTime);
			// String amOrPm = TimeUtils.getIfAMorPM();
			// Wait for 2 secs and restart if the time ends with 59:59, in order
			// to
			// avoid a wrong computation of the dir to which we need to write to
			if (currentTime.endsWith("59:59")) {
				Thread.sleep(2000);
				currentTime = TimeUtils.getCurrentGMT();
				// amOrPm = TimeUtils.getIfAMorPM();
			}
			StringBuilder dirToWrite = new StringBuilder(repositoryURL);
			dirToWrite.append("/");
			dirToWrite.append(currentTime.substring(0, 4));
			dirToWrite.append("/");
			dirToWrite.append(currentTime.substring(5, 7));
			dirToWrite.append("/");
			dirToWrite.append(currentTime.substring(8, 10));
			dirToWrite.append("/");
			dirToWrite.append(currentTime.substring(11, 13));
			return dirToWrite.toString();
		} catch (InterruptedException exception) {
			// Log the stack trace
			exception.printStackTrace();
			throw new RuntimeException(exception);
		}
	}

	public static void main(String args[]) {
		String dir = computeDirToWrite("");
		System.out.println(dir);
	}

	public static String addDaysToCurrentDate(int days) {
		DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		String lastDate = null;
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, days);
			lastDate = df.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastDate;
	}

	public static String formatDate(String date, String originalFormat,
			String requiredFormat) throws ParseException {
		if (isAnyNullOrEmpty(new String[] { date, originalFormat,
				requiredFormat })) {
			return null;
		}
		SimpleDateFormat originalDateFormat = new SimpleDateFormat(
				originalFormat);
		SimpleDateFormat requiredDateFormat = new SimpleDateFormat(
				requiredFormat);
		return requiredDateFormat.format(originalDateFormat.parse(date));
	}

	public static boolean isAnyNullOrEmpty(String[] texts) {

		for (String s : texts) {
			if (s == null || s.trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int getAge(String date, String dateFormat) throws ParseException {
		int age = 0;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date birthDate = format.parse(date);
		Calendar birthCal = Calendar.getInstance();
		birthCal.setTime(birthDate);
		Calendar curCal = Calendar.getInstance();
		
		age = curCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
		return age;
	}
}