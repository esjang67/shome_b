package com.esjang.sthome.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.Suggest;
import com.esjang.sthome.domain.User;

@Repository
public interface SuggestRopository extends JpaRepository<Suggest, Integer> {

	// 조회(관리자) : 기간
	public List<Suggest> findByBasedateBetweenOrderByIdDesc(LocalDate start, LocalDate end);
	
	
	//	조회(사용자, 기간)
	public List<Suggest> findByUserAndBasedateBetweenOrderByIdDesc(User user, LocalDate start, LocalDate end);
	
	//	조회(사용자, 기간, OK)
	public List<Suggest> findByUserAndBasedateBetweenAndOkflagIsOrderByIdDesc(User user, LocalDate start, LocalDate end, String okflag);
	
	//	조회(기간, OK)
	public List<Suggest> findByBasedateBetweenAndOkflagIsOrderByIdDesc(LocalDate start, LocalDate end, String okflag);


}
