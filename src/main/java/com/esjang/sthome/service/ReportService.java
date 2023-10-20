package com.esjang.sthome.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.Report;
import com.esjang.sthome.repository.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	// 들록
	public void insert(Report report) {
		reportRepository.save(report);
	}
	// 수정
	public void update(Report report) {
		Report oriReport = reportRepository.findById(report.getId()).get();
		oriReport.setContent(report.getContent());
		reportRepository.save(oriReport);
	}
	
	// 삭제(관리자용)
	public void delete(int id) {
		reportRepository.deleteById(id);
	}
	// 조회 1건
	public Report findById(int id){
		Report report = reportRepository.findById(id).orElseGet(()-> new Report());
		System.out.println("ReportService : findById " + id + " : " + report);
		return report;
	}
	
	// 조회 기간
	public List<Report> findAllByBasedate(Date start, Date end){
		List<Report> list = reportRepository.findAllByBasedateBetweenOrderByIdDesc(start, end);
		return list;
	}
	
	// 조회 사용자별 + 기간
	public List<Report> findAllByUseridAndBasedate(String userid, Date start, Date end){
		List<Report> list = reportRepository.findAllByUserAndBasedateBetweenOrderByIdDesc(userid, start, end);
		return list;
	}
	
	// 조회 사용자별
//	public List<Report> findAllByUserid(String userid){
//		List<Report> list = reportRepository.findAllByUserOrderByIdDesc(userid);
//		return list;
//	}		
	
}
