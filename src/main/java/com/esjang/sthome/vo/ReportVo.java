package com.esjang.sthome.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportVo {

	private Integer id;
	private LocalDate basedate;
	private String content;
	private String userid;
	private Integer bookid;
}
