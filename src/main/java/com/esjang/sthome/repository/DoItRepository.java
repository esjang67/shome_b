package com.esjang.sthome.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.DoIt;
import com.esjang.sthome.domain.User;

@Repository
public interface DoItRepository extends JpaRepository<DoIt, Integer> {
//	public List<DoIt> findAllByUser(User user);
	
	// 조회 : 사용자 + 기준일자
	public List<DoIt> findListByUserAndBasedate(User user, LocalDate basedate);
	
	// 조회 : 기준일자
	public List<DoIt> findListByBasedate(LocalDate basedate);
	
	// 갯수 : 오늘의 할일을 다 완료했는가?? 안한갯수 가져오기
	public int countByUserAndBasedateAndDoneIs(User user, LocalDate basedate, String done);
	
	public List<DoIt> findAllByUser(String userid);
	
	public void deleteByUserAndBasedate(String userid, LocalDate basedate);
	
//	@Query(value = "select * from tbl_doit where userid=:userid and basedate=:basedate")
//	public List<DoIt> selfindAllByUserAndBasedate(@Param(value = "userid") String userid, 
//			@Param(value = "basedate") Date basedate);
	
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

