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
@Table(name = "tbl_suggest")
@Data
@SequenceGenerator(
		name = "SUGGEST_SEQ_GENERATOR"
	    , sequenceName = "SUGGEST_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Suggest {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUGGEST_SEQ_GENERATOR")
	private Integer id;
	
	@CreatedDate
	private LocalDate basedate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SuggestType type;
	
	@Column(nullable = false)
	private String content;
	
	@Column(columnDefinition = "varchar(1) default 'N'")
	private String okflag;
	
}