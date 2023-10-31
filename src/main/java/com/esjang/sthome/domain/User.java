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

//insert into tbl_user(userid, grade, name, password) values('DAD','P','아빠','9876');
//insert into tbl_user(userid, grade, name, password) values('MOM','P','엄마','9999');
//insert into tbl_user(userid, grade, name, password) values('MIN','K','민찬', '0714');
//insert into tbl_user(userid, grade, name, password) values('DO','K','도현', '0613');
//commit;
//
//select * from tbl_user;
