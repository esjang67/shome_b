package com.esjang.sthome.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name = "tbl_suggest")
@Data
public class Suggest {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@CreatedDate()
	private Date basedate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SuggestType type;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	@Column(columnDefinition = "varchar(1) default 'N'")
	private String okflag;
	
}