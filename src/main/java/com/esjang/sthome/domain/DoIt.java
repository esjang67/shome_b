package com.esjang.sthome.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name = "tbl_doit")
@Data
public class DoIt {

	@Id			// 기본키 컬럼 설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
//	@Column(nullable = false, length = 10)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
	
	//@CreatedDate, @LastModifiedDate를 사용하여 생성된 시간 정보, 수정된 시간 정보를 자동으로 저장할 수 있다
	@CreatedDate()
	@Column(updatable = false)
	private Date basedate;
	
	@Column(nullable = false, length = 500)
	private String content;
	
	@Column(columnDefinition = "varchar(1) default 'N'")
	private String done;
	
	
}

// 하루 할일은 doitbatch에서 가져옴
// 화면에 다시가져오기 버튼이 필요한가???

//create table tbl_doit(
//seq number(10) not null primary key,
//userid varchar2(10) not null,
//basedate date not null,
//content varchar2(1000) not null,
//done varchar2(1)
//);