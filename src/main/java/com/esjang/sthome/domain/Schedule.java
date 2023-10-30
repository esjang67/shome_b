package com.esjang.sthome.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name = "tbl_schedule")
@Data
@SequenceGenerator(
		name = "SCHEDULE_SEQ_GENERATOR"
	    , sequenceName = "SCHEDULE_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Schedule {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_SEQ_GENERATOR")
	private Integer id;
	
	@CreatedDate
	private LocalDate basedate;
	
	@Column(nullable = false, length = 500)
	private String content;
	
}
