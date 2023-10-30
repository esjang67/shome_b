package com.esjang.sthome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_bookcollect")
@Data
@SequenceGenerator(
		name = "COLLECT_SEQ_GENERATOR"
	    , sequenceName = "COLLECT_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class BookCollect {
	
	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLLECT_SEQ_GENERATOR")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(columnDefinition = "varchar(1) default 'N'")
	private String delyn;
}
