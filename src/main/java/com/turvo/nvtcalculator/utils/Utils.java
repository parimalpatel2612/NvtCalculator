package com.turvo.nvtcalculator.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public static String parseDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
		return dateFormat.format(date);  
	}
}
