package com.esjang.sthome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_coupontime")
@Data
public class CouponTime {
	
	@Id			// 기본키 컬럼 설정
	@Column(nullable = false, length = 10)
	private String id;		// user.userid
	
	// 조회용 user 추가
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;	
	
	private Integer totaltime;
	
}