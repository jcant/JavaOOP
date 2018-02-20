package com.gmail.gm.jcant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JDate {

	private static String defaultFormat = "dd-MM-yyyy";

	private Date date;
	private Calendar calendar;
	private String format;

	// --- constructors ---
	public JDate() {
		super();
		calendar = Calendar.getInstance();
	}

	public JDate(Date date) {
		super();
		calendar = Calendar.getInstance();
		initDate(date);
	}

	public JDate(String date, String format) throws ParseException {
		super();
		this.format = format;
		calendar = Calendar.getInstance();
		initDate(date);
	}

	private final void initDate(String date) throws ParseException {
		String currFormat = (format != null) ? (format) : (defaultFormat);
		SimpleDateFormat sdf = new SimpleDateFormat(currFormat);
		// try {
		this.date = sdf.parse(date);
		calendar.setTime(this.date);
		// } catch (ParseException e) {
		// System.err.println("Error setting date!");
		// }
	}

	private final void initDate(Date date) {
		this.date = date;
		calendar.setTime(date);
	}

	// --- setters getters ---
	public void setDate(String date) {
		try {
			initDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setDate(Date date) {
		initDate(date);
	}

	public Date getDate() {
		return date;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public String toString() {
		String currFormat = (format != null) ? (format) : (defaultFormat);
		SimpleDateFormat sdf = new SimpleDateFormat(currFormat);
		return sdf.format(date);
	}

	public int getDifferenceYears(Date date) {
		Calendar day = Calendar.getInstance();
		day.setTime(date);

		int diff = day.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		day.set(Calendar.YEAR, calendar.get(Calendar.YEAR));

		if (calendar.compareTo(day) >= 0) {
			diff--;
		}

		return diff;
	}

	// --- static ---
	public static Date getDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			System.err.println("Error getting quick date!");
		}
		return result;
	}

	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
		String result = sdf.format(date);
		return result;
	}

	public static void setDefaultFormat(String format) {
		defaultFormat = format;
	}

}
