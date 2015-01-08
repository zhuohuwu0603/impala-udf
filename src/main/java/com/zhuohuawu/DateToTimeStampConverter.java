package com.zhuohuawu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class DateToTimeStampConverter extends UDF {
	
	public String evaluate(Text s){
		String result = new String("");
		
		if(result!=null){
			String dateStr = s.toString();
			
			result = convertToTimeStamp(dateStr);
		}
		return result;
	}

	public static String convertToTimeStamp(String dateStr) {
		
		if(dateStr == null)
			return null;
		
		if(Pattern.compile("\\d{13}").matcher(dateStr).matches()){ //
			return dateStr;
		}
		
		final String[] DATE_PATTERNS = {//"^\\d{13}", //String testDate = "1312307833000";
		         "[a-zA-Z]{3}\\s[a-zA-Z]{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s[a-zA-Z]{3}\\s\\d{4}", //Fri Aug 05 00:00:00 EDT 2011
		         "\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}.*", //"05/06/2014 13:39:24";
		         "\\d{4}-\\d{2}-\\d{2}[a-zA-Z]\\d{2}:\\d{2}:\\d{2}[a-zA-Z]", //String testDate = "2014-05-06T17:39:25Z";
		         "^\\d{8}",  //String testDate = "20110802";
		         };           
		
		final String[] DATEFORMATS = {"EEE MMM dd hh:mm:ss z yyyy", //Fri Aug 05 00:00:00 EDT 2011
		    "MM/dd/yyyy HH:mm:ss", //"05/06/2014 13:39:24"
		    "yyyy-MM-dd'T'HH:mm:ss'Z'", //"2014-05-06T17:39:25Z";
		    "yyyyMMdd" //"20110802"
		};
		
		
		Pattern pattern = null;
		String result = null;
		for(int i = 0; i < DATE_PATTERNS.length; i++){
			pattern = Pattern.compile(DATE_PATTERNS[i]);
			if(pattern.matcher(dateStr).matches()){
				DateFormat inputDateFormat = new SimpleDateFormat(DATEFORMATS[i]);
				
				Date inputDate = null;
				
				try {
					inputDate = inputDateFormat.parse(dateStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String outputDateStr = "";
				if(inputDate!=null){
					outputDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(inputDate);
				}
				
				result = outputDateStr;
				break;
			}
		}	
		return result;
	}
	
	public static void main(String[] argss){
		//String dateStr = "Fri Aug 05 00:00:00 EDT 2011";
		String dateStr = "131230783300";
		
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		System.out.println(result);
	}
	
}
