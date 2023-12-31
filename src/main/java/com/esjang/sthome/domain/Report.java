package com.esjang.sthome.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tbl_report")
@Data
@SequenceGenerator(
		name = "REPORT_SEQ_GENERATOR"
	    , sequenceName = "REPORT_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Report {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_SEQ_GENERATOR")
	private Integer id;
	
//	@CreatedDate
//	@Column(updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreatedDate
	private LocalDate basedate;
	
//	@Column(nullable = false, length = 10)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)		// 다 : 1  관계설정(post 기준)
	@JoinColumn(name = "bookid", referencedColumnName = "id")
	private Books book;
	
//	@Lob  // Postgres는 안됨
	@Column(nullable = false, length = 50000)
	private String content;
	
}