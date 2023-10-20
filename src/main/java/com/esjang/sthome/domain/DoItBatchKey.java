package com.esjang.sthome.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoItBatchKey implements Serializable {
	private String userid;
	private DayType defineday;
	private int	seq;
}

//userid varchar2(10) not null,
//defineday varchar2(7) not null,
//seq number(10) not null,