package com.esjang.sthome.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DoitVo {
	
	private Integer id;
	private LocalDate basedate;
	private String content;
	private String done;
	private String userid;
	private String username;
}
