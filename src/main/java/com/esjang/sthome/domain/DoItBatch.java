package com.esjang.sthome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_doitbatch")
//@IdClass(DoItBatchKey.class)
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class DoItBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer	id;
	
//	@Column(nullable = false, length = 10)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", referencedColumnName = "userid", insertable = true, updatable = false)
	private User user;
	
	@Column(nullable = false, length = 7)
	private String defineday;
		
	@Column(nullable = false, length = 500)
	private String content;
	
//	public DoItBatch(User user) {
//        this.user = user;
//    }
	
}
//https://www.icatpark.com/entry/jpa-%EB%B3%B5%ED%95%A9%ED%82%A4%EC%97%90%EC%84%9C-auto-increment
//https://toload.tistory.com/entry/JPA-JPA%EB%A1%9C-%EB%B3%B5%ED%95%A9%ED%82%A4%EB%A1%9C-%EA%B5%AC%EC%84%B1%EB%90%9C-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%83%9D%EC%84%B1-%EB%B0%8F-%EC%82%AD%EC%A0%9C%ED%95%98%EA%B8%B0

//-- 월요일 민찬이가 수학문제집을 푼다
//-- 민찬이가 월요일에 .. 1번
//create table tbl_doitbatch(
//userid varchar2(10) not null,
//defineday varchar2(7) not null,
//seq number(10) not null,
//content varchar2(1000) not null,
//useyn varchar(1),
//CONSTRAINT DOITBATCH_PK PRIMARY KEY(seq, defineday, userid)
//);