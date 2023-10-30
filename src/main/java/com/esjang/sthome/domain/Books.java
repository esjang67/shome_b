package com.esjang.sthome.domain;

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

import lombok.Data;

@Entity
@Table(name = "tbl_books")
@Data
@SequenceGenerator(
		name = "BOOK_SEQ_GENERATOR"
	    , sequenceName = "BOOK_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Books {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ_GENERATOR")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "collectid", referencedColumnName = "id")
	private BookCollect bookcollect;
	
//	@Column(columnDefinition = "boolean default false")
	@Column(columnDefinition = "varchar(1) default 'N'")
	private String delyn;
}