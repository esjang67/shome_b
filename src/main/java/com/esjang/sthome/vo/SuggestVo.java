package com.esjang.sthome.vo;

import java.time.LocalDate;

import com.esjang.sthome.domain.SuggestType;

import lombok.Data;

@Data
public class SuggestVo {
	
	private Integer id;
	private LocalDate basedate;
	private SuggestType type;
	private String content;
	private String okflag;
	private String userid;
	private String username;
	
}
