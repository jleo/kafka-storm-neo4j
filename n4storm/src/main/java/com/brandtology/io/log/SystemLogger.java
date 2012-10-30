/**
 * 
 */
package com.brandtology.io.log;

import java.util.logging.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * @ Author: Lim Teck Lee
 * @ Date: 9 October 2008
 */
public class SystemLogger {
	
	private static final Logger fLogger = Logger.getLogger(SystemLogger.class.getPackage().getName());	
	
	private static final String PRINT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat psdf = new SimpleDateFormat(PRINT_TIME_FORMAT);
	
	public static void initialise() {
		Properties props = new Properties();
	   	props.setProperty("log4j.rootLogger", "ERROR, stdout");
	   	props.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
	   	props.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
	   	props.setProperty("log4j.appender.stdout.layout.ConversionPattern", "%-5p (%20F:%-3L) - %m\n");
	   	PropertyConfigurator.configure(props);
	   	
		String TIME_FORMAT = "yyyyMMdd-HHmmss";
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		
		try {
			FileHandler fh = new FileHandler("logs/" + 	sdf.format(date) + "-logs.txt");
			fLogger.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			printWarning(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			printWarning(e.getMessage());
		}
	}
	
	public static void printInfo(String str) {
//	    fLogger.info(str);
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		
		System.out.println(psdf.format(date) + " - " + str);
	}
	
	public static void printWarning(String str) {
		fLogger.warning(str);
	}
	
	public static void printSevere(String str) {
		fLogger.severe(str);
	}
}
