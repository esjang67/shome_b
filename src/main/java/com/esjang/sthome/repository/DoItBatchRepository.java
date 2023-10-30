package com.esjang.sthome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.DoItBatch;
import com.esjang.sthome.domain.User;


@Repository
public interface DoItBatchRepository extends JpaRepository<DoItBatch, Integer> {
	
	// 요일 + 사용자로 조회한 list
//	@Query(value = "select * from tbl_doitbatch where userid=?1 and defineday=?2 and useyn=true", nativeQuery = true)
	public List<DoItBatch> findAllListByUserAndDefinedayContaining(User userid, String defineday);
	
	// 조회 : 전체 사용자별
	public List<DoItBatch> findAllListByUser(User userid);
	
}
 