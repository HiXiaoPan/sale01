package com.atguigu.b2c.sale.utils;

import java.util.Calendar;
import java.util.Date;

public class OrderDate {
	public static Date get_yjsdshj(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
}
