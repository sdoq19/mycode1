package com.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LongToDateTest {
	
	public static void main(String[] args) {
		//System.out.println(new Date(1429523983882l).toLocaleString());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss SSS");
		System.out.println(df.format(new Date(1423115131756L)));
		
		Date dd = new Date();
		System.out.println(dd.getTime());
	}
}
