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

	public JDate() {
		super();
		calendar = Calendar.getInstance();
	}

	public JDate(Date date) {
		super();
		this.format = defaultFormat;
		calendar = Calendar.getInstance();
		setDate(date);
	}

	public JDate(String date, String format) {
		super();
		this.format = format;
		calendar = Calendar.getInstance();
		setDate(date);
	}

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

	public void setDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			this.date = sdf.parse(date);
			calendar.setTime(this.date);
		} catch (ParseException e) {
			System.err.println("Error setting date!");
		}
	}

	public void setDate(Date date) {
		this.date = date;
		calendar.setTime(date);
	}

	public Date getDate() {
		return date;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
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

}
