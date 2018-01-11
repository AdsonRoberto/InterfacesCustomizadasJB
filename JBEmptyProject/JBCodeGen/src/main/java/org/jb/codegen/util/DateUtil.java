package org.jb.codegen.util;

import org.jb.ui.annotation.visual.enums.TemporalType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String fromDate(Date date) {
		return fromDate(date, "dd/mm/yyyy");
	}
	
	public static String fromDate(Date date, String formatString) {
		if(date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat(formatString);  
		return format.format(date);
	}
	
	public static Date toDate(String date) {
		if(date == null || date.isEmpty())
			return null;
		return toDate(date, "dd/mm/yyyy");
	}
	
	public static Date toDate(String dateString, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);  
		try {
			return new Date(format.parse(dateString).getTime());
		} catch (ParseException e) {
			return null;
		} 
	}

	public static String stringFormat(TemporalType type) {
		if(type == TemporalType.TIMESTAMP)
			return "dd/MM/yyyy hh:mm:ss";
		if(type == TemporalType.DATE)
			return "dd/MM/yyyy";
		if(type == TemporalType.TIME)
			return "hh:mm:ss";
		return "";
	}

	public static String stringDatabaseFormat(TemporalType type) {
		if(type == TemporalType.TIMESTAMP)
			return "yyyy-MM-dd hh:mm:ss";
		if(type == TemporalType.DATE)
			return "yyyy-MM-dd";
		if(type == TemporalType.TIME)
			return "hh:mm:ss";
		return "";
	}

	public static String inputType(TemporalType type) {
		if(type == TemporalType.TIMESTAMP)
			return "datetime";
		if(type == TemporalType.DATE)
			return "date";
		if(type == TemporalType.TIME)
			return "time";
		return "";
	}
}
