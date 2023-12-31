package com.esjang.sthome.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name = "tbl_coupon")
@Data
@SequenceGenerator(
		name = "COUPON_SEQ_GENERATOR"
	    , sequenceName = "COUPON_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Coupon {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUPON_SEQ_GENERATOR")
	private Integer id;
	
	@CreatedDate()
	@Column(updatable = false)
	private LocalDate basedate;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private CouponType type;
	
	@Column(nullable = false, length = 500)
	private String content;
	
	private Integer playtime;
	
//	// 쿠폰 사용여부 20231130 임시추가)
//	@Column(columnDefinition = "varchar(1) default 'N'")
//	private String use;
//	
//	// 쿠폰 발행 id(type으로 report인지 다른것인지 확인할 수 있음)
//	private Integer impid;
	
}