package com.esjang.sthome.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDate {
	
	// 기간조회용  
	private Date startDate;
	private Date endDate;
	
}
