package com.esjang.sthome.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name = "tbl_schedule")
@Data
public class Schedule {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@CreatedDate
	@Column(updatable = false)
	private Date basedate;
	
	@Column(nullable = false, length = 500)
	private String content;
	
}
