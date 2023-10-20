package com.esjang.sthome.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.Suggest;

@Repository
public interface SuggestRopository extends JpaRepository<Suggest, Integer> {

	// 조회(관리자) : 기간
	public List<Suggest> findByBasedateBetween(Date start, Date end);
	
	
	//	조회(사용자, 기간)
	public List<Suggest> findByUserAndBasedateBetween(String userid, Date start, Date end);
	
	//	조회(사용자, 기간, OK)
	public List<Suggest> findByUserAndBasedateBetweenAndOkflagIs(String userid, Date start, Date end, String okflag);
	
	//	조회(기간, OK)
	public List<Suggest> findByBasedateBetweenAndOkflagIs(Date start, Date end, String okflag);


}
