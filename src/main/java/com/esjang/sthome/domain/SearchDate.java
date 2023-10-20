package com.esjang.sthome.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDate {
	// 클라이언트는 숫자형으로 보낼것이야
	// 기간조회용  
	private Date startDate;
	private Date endDate;
	
}

