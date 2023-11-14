package com.esjang.sthome.vo;

import java.time.LocalDate;

import com.esjang.sthome.domain.CouponType;

import lombok.Data;

@Data
public class CouponVo {
	private Integer id;
	private LocalDate basedate;
	private CouponType type;
	private String content;
	private Integer playtime;
	private String userid;
	private String username;
}
