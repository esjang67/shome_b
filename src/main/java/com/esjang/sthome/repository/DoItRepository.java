package com.esjang.sthome.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.DoIt;
import com.esjang.sthome.domain.User;

@Repository
public interface DoItRepository extends JpaRepository<DoIt, Integer> {
//	public List<DoIt> findAllByUser(User user);
	
	// 조회 : 사용자 + 기준일자
	public List<DoIt> findListByUserAndIndate(User user, String indate);
	
	// 조회 : 기준일자
	public List<DoIt> findListByIndate(String indate);
	
//	@Query(value = "select * from tbl_doit where userid=:userid and basedate=:basedate")
//	public List<DoIt> selfindAllByUserAndBasedate(@Param(value = "userid") String userid, 
//			@Param(value = "basedate") Date basedate);
	
	public List<DoIt> findAllByUser(String userid);
	
	public void deleteByUserAndBasedate(String userid, Date basedate);
	
//	Containing
	
//	@Modifying 
//	@Transactional
//	@Query(value = "insert into tbl_doit(id, BASEDATE, CONTENT, USERID, DONE) "
//			+ "    select HIBERNATE_SEQUENCE.nextval as id, :basedate AS BASEDATE, content, :userid AS USERID, 'N' AS DONE "
//			+ "    from tbl_doitbatch where userid=:userid ", nativeQuery = true)		//and defineday like ':#{#day}'
//	public void sqlBatchToDoit(@Param(value = "userid") String userid, 
//							@Param(value = "basedate") Date basedate, 
//							@Param(value = "day") String day);
 
	
}

