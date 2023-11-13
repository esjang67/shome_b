package com.esjang.sthome.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.Report;
import com.esjang.sthome.domain.User;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	// order by id desc 
	// 조회 : 전체
	public List<Report> findAllByOrderByIdDesc();
	
	// where basedate order by id desc
//	public List<Report> findAllListByBasedateOrderByIdDesc(Date basedate);
	
	// 조회 기간
	public List<Report> findAllByBasedateBetweenOrderByIdDesc(LocalDate start, LocalDate end);
	
	// 조회 사용자별 + 기준일자
	public List<Report> findAllByUserAndBasedateOrderByIdDesc(User user, LocalDate seldate);
	
	// 조회 사용자별 + 기간
	public List<Report> findAllByUserAndBasedateBetweenOrderByIdDesc(User user, LocalDate start, LocalDate end);
	
	// 조회 사용자별
	//public List<Report> findAllByUserOrderByIdDesc(String userid);
	
}
