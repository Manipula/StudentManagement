package com.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Assistance {
	public static String getTime() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Calendar calendar=new GregorianCalendar(); 
		String str=sdf.format(calendar.getTime()); 
		return str;
	}
	
	public static String getTimeDay() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		Calendar calendar=new GregorianCalendar(); 
		String str=sdf.format(calendar.getTime()); 
		return str;
	}
}
