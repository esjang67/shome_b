package com.esjang.sthome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id			// 기본키 컬럼 설정
	private String userid;
	
	@Enumerated(EnumType.STRING)
	private GradeType grade;
	
	@Column(nullable = false, length = 50, unique = true)
	private String name;
	
	@Column(nullable = false, length = 10)
	private String password;
	
}

//create table tbl_user(
//userid varchar2(10) not null primary key,
//grade varchar2(1) not null,
//name varchar2(10) not null,
//pw varchar2(5)
//);
//
//insert into tbl_user values('DAD','P','아빠','1234');
//insert into tbl_user values('MOM','P','엄마','1234');
//insert into tbl_user values('MIN','K','민찬', '1234');
//insert into tbl_user values('DO','K','도현', '1234');
//commit;
//
//select * from tbl_user;
