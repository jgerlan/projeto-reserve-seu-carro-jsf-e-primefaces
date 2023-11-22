package com.projetoes.ecommerce.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DateTimeExtensions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Date truncateToDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
	
	public Date addDays(Date date, int days) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_MONTH, days);
	    return calendar.getTime();
	}
}
