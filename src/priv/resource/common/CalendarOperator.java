package priv.resource.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarOperator {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		int YEAR = cal.get(Calendar.YEAR);
		int MONTH = cal.get(Calendar.MONTH) + 1;
		int DATE = cal.get(Calendar.DATE);
		int HOUR = cal.get(Calendar.HOUR);
		int MINUTE = cal.get(Calendar.MINUTE);
		int SECOND = cal.get(Calendar.SECOND);
		System.out.println(YEAR + "-" + MONTH + "-" + DATE + " " + HOUR + ":" + MINUTE + ":" + SECOND);

		int DAY_OF_YEAR = cal.get(Calendar.DAY_OF_YEAR);
		int DAY_OF_MONTH = cal.get(Calendar.DAY_OF_MONTH);
		int DAY_OF_WEEK = cal.get(Calendar.DAY_OF_WEEK);
		int HOUR_OF_DAY = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + ",当年第" + DAY_OF_YEAR + "日-当月第" + DAY_OF_MONTH + "日-当周第" + DAY_OF_WEEK + "天-当天第" + HOUR_OF_DAY + "个小时");

		System.out.println("--------------------------");

		cal.add(Calendar.DAY_OF_MONTH, -25); // // 不只改变该属性的值，还改变其它值，当时要注意顺序
		int DAY_OF_MONTH1 = cal.get(Calendar.DAY_OF_MONTH);
		cal.add(Calendar.DAY_OF_YEAR, 0);
		int DAY_OF_YEAR1 = cal.get(Calendar.DAY_OF_YEAR);
		cal.add(Calendar.DAY_OF_WEEK, 0);
		int DAY_OF_WEEK1 = cal.get(Calendar.DAY_OF_WEEK);
		int HOUR_OF_DAY1 = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + ",当年第" + DAY_OF_YEAR1 + "日-当月第" + DAY_OF_MONTH1 + "日-当周第" + DAY_OF_WEEK1 + "天-当天第" + HOUR_OF_DAY1 + "个小时");

		System.out.println("--------------------------");

		Calendar cal1 = Calendar.getInstance();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime()));
		// cal.set(Calendar.DAY_OF_YEAR, -200);
		cal1.set(Calendar.DAY_OF_MONTH, -40); // 不只改变该属性的值，还改变其它值
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime()));

		System.out.println("--------------------------");

		Calendar cal2 = Calendar.getInstance();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime()));
		// cal.set(Calendar.DAY_OF_YEAR, -200);
		cal2.set(Calendar.MONTH, 40); // 不只改变该属性的值，还改变其它值
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime()));

		System.out.println("--------------------------");

		Calendar c = Calendar.getInstance();
		c.set(2014, Calendar.MARCH, 31);
		c.add(Calendar.MONTH, 13);
		System.out.println(c.getTime());// 2015-04-30

		c.set(2014, Calendar.MARCH, 31);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 13);
		System.out.println(c.getTime());// 2015-05-01
		c.set(2014, Calendar.MARCH, 31);
		c.roll(Calendar.MONTH, 13);
		System.out.println(c.getTime());// 2014-04-30

	}
}