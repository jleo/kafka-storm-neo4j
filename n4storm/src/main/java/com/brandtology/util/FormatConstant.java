package com.brandtology.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormatConstant {
	public static final Long MINUTE_MILI = 1000*60L;
	public static final Long HOURLY_MILI = MINUTE_MILI*60L;
	public static final Long DAILY_MILI = HOURLY_MILI*24;
	public static final Long WEEKLY_MILI = DAILY_MILI*7;
	public static final Long MONTHLY_MILI = WEEKLY_MILI*30;
	
	public static final SimpleDateFormat DATASIFT_FILE_DF = new SimpleDateFormat("yyyyMMdd_HHmm");     
	public static final SimpleDateFormat MONGODB_DF= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat SINA_SEARCH_DF = new SimpleDateFormat("yyyy-MM-dd-HH");
	public static final SimpleDateFormat DATASIFT_TWEET_DF(){
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
    }

	public static final SimpleDateFormat TWITTER_CA_DF (){
        return  new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.US);
    }
	public static final SimpleDateFormat SIMPLE_DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SHORT_SIMPLE_DF = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
	public static final SimpleDateFormat SHORT_DP_DF = new SimpleDateFormat( "yyyyMMdd hhmmss" );
	public static final SimpleDateFormat TRADITIONAL_DF = new SimpleDateFormat("dd MMMM yyyy");
	
	public static final DecimalFormat DIGITAL_FORMAT = new DecimalFormat("###.##");

	public static final String DIGIT_REGEX = "\\p{Digit}+";
	public static final String PUNCT_REGEX = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	
	public static void main(String[] arg) throws Exception{
		long time = SIMPLE_DF.parse("2012-07-18 14:00:00").getTime();
		System.out.println(SIMPLE_DF.format(time).substring(11));
	}
}
