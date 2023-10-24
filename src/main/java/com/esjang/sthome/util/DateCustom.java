package com.esjang.sthome.util;

import java.sql.Date;

public class DateCustom {

	public static Date longToDataCange(Long timeData) {
		// date format change
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date setDate = new Date(timeData);
		
		System.out.println("longToDataCange 후 : " + timeData + " -> " + setDate);
		return setDate;
	}
}
