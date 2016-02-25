package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.utils.DateUtil;

public class Test {

	public static void main(String[] agrs) {
	}
	
	/**
	 * 
	 */
	private static void rntest() {
		
	}
	
	/**
	 * double intValue() method test
	 */
	private static void doubleIntValueTest() {
		Double aa = 1.90123465;
		System.out.println(aa.intValue());
	}
	
	/**
	 * String lastIndexOf方法测试
	 */
	private static void stringLastIndexOfTest() {
		String str = "qweoowe}{roo";
		int indexof = str.lastIndexOf("}{1");
		System.out.println(indexof);
	}
	
	/**
	 * String split方法
	 * 关于点的问题是用string.split("[.]") 解决。
	 * 关于竖线的问题用 string.split("\\|")解决。
	 * 关于星号的问题用 string.split("\\*")解决。
	 * 关于斜线的问题用 sring.split("\\\\")解决。
	 * 关于中括号的问题用 sring.split("\\[\\]")解决。
	 */
	private static void stringSplitTest() {
		String str = "abcd}{efg";
		String[] s = str.split("\\}\\{");
		for (int i=0; i<s.length; i++) {
			System.out.println(s[i]);
		}
	}
	
	/**
	 * Array copy方法 
	 */
	private static void arrayCopyTest() {
		int[] strs = new int[10];
		strs[0] = 0;
		strs[1] = 1;
		strs[2] = 2;
		strs[3] = 3;
		strs[4] = 4;
		strs[5] = 5;
		for (int i=0; i<strs.length; i++) { 
			System.out.println(strs[i]);
		}
		System.out.println("---------------");
		int[] strsCopy = Arrays.copyOf(strs, 5);
		for (int i=0; i<strsCopy.length; i++) { 
			System.out.println(strsCopy[i]);
		}
	}
	
	/**
	 * List引用类型测试
	 */
	private static void listEdit() {
		List<AA> aaList = new ArrayList<AA>();
		AA aa = new AA();
		aa.setRightcode(11l);
		AA bb = new AA();
		bb.setRightcode(22l);
		aaList.add(aa);
		aaList.add(bb);
		for (AA aa2 : aaList) {
			System.out.println(aa2.getRightcode());
		}
		for (AA aa2 : aaList) {
			if (aa2.getRightcode().longValue() == 11l) {
				aa2.setRightcode(33l);
			}
		}
		for (AA aa2 : aaList) {
			System.out.println(aa2.getRightcode());
		}
	}

	private static void currentMethod() {
		int level = 1;
		Random random = new Random(System.currentTimeMillis());
		System.out.println(random.nextInt((int) Math.pow(10, level)));
		int i = 1000;
		while (i > 0) {
			System.out.println(new Random().nextInt(10));
			i--;
		}

	}

	private static void longToDate() {
		DateUtil date = new DateUtil();
		System.out.println(date.longToDate(1428902298368l));
		// LoggerUtil.info(test.class, date.longToDate(1416383427784L));
	}

	private static void testMethod() {

		// 数组初始化
		Test.initArray();

		// 值类型，引用类型测试
		int i = 0;
		String j = new String("aaa");
		Integer k = new Integer(2);
		int[] is = { 1, 2, 3 };
		List<String> a = new ArrayList<String>();
		a.add("aa");
		a.add("bb");
		typeTest(i, j, k, is, a);
		System.out.println("i=" + i + ",j=" + j + ",is[2]=" + is[2] + ",k=" + k);
		System.out.println(a);
	}

	/**
	 * 值类型，引用类型
	 * 
	 * 参数有两种方式，普通类型如int，String等为传值，其他的如JAVA对象，数组，集合等均为传址。 传值方式只是把值传入参数，在方法里的任何动作与源无关，源的值不变；
	 * 传址方式是把源对象的地址传入方法，在方法里的动作都是直接操作源对象，所以能改变其值。
	 */
	private static void typeTest(int i, String j, Integer k, int[] is, List<String> a) {
		i = 2;
		j = "bbb";
		k = 3;
		is[2] = 4;
		a.add("cc");
	}

	/**
	 * 
	 */
	private static void typeTest2() {
		Map<Long, List<String>> map = new HashMap<Long, List<String>>();
		List<String> sList1 = new ArrayList<String>();
		sList1.add("11");
		sList1.add("22");
		List<String> sList2 = new ArrayList<String>();
		sList2.add("33");
		sList2.add("55");
		map.put(1l, sList1);
		map.put(2l, sList2);

		List<String> tList = map.get(1l);
		for (String a : tList) {
			System.out.println(a);
		}
		System.out.println("------------------");
		tList.add("66");
		List<String> newList = map.get(1l);
		for (String a : newList) {
			System.out.println(a);
		}
	}

	/**
	 * int, char, string, boolean 数组初始化
	 */
	private static void initArray() {
		Random rd = new Random(47);

		int[] intArr = new int[rd.nextInt(20)];
		System.out.println("length:" + intArr.length + " " + Arrays.toString(intArr));

		char[] charArr = new char[rd.nextInt(20)];
		System.out.println("length:" + charArr.length + " " + Arrays.toString(charArr));

		String[] stringArr = new String[rd.nextInt(20)];
		System.out.println("length:" + stringArr.length + " " + Arrays.toString(stringArr));

		boolean[] boolArr = new boolean[rd.nextInt(20)];
		System.out.println("length:" + boolArr.length + " " + Arrays.toString(boolArr));
	}

}

class AA {
	private Long rightcode;

	public Long getRightcode() {
		return rightcode;
	}

	public void setRightcode(Long rightcode) {
		this.rightcode = rightcode;
	}
}
