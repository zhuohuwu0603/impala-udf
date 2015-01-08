package com.zhuohuawu;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateToTimeStampConverterTest {

	@Test
	public void test1() {
		String dateStr = "Fri Aug 05 00:00:00 EDT 2011";
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		assertEquals("2011-08-05 00:00:00.000", result);
	}
	
	@Test
	public void test2() {
		String dateStr = "05/06/2014 13:39:24";
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		assertEquals("2014-05-06 13:39:24.000", result);
	}	
	
	@Test
	public void test3() {
		String dateStr = "2014-05-06T17:39:25Z";
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		assertEquals("2014-05-06 17:39:25.000", result);
	}		
	
	@Test
	public void test4() {
		String dateStr = "20110802";
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		assertEquals("2011-08-02 00:00:00.000", result);
	}		
	
	@Test
	public void test5() {
		String dateStr = null;
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		assertEquals(null, result);
	}
	
	@Test
	public void test6() {
		String dateStr = "1312307833000";
		String result = DateToTimeStampConverter.convertToTimeStamp(dateStr);
		assertEquals("1312307833000", result);
	}	
}
