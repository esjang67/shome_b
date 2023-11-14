package com.esjang.sthome.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esjang.sthome.domain.Books;
import com.esjang.sthome.domain.Coupon;
import com.esjang.sthome.domain.CouponType;
import com.esjang.sthome.domain.Report;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.BookRepository;
import com.esjang.sthome.repository.ReportRepository;
import com.esjang.sthome.repository.UserRepository;
import com.esjang.sthome.vo.ReportVo;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UserRepository userRepository;
	
	// 들록
	@Transactional
	public void insert(ReportVo resReport) {
		User user = new User();
		user = userRepository.findById(resReport.getUserid()).get();
		
		Books book = new Books();
		book = bookRepository.findById(resReport.getBookid()).get();
		
		Report report = new Report();
		report.setId(resReport.getId());
		report.setBasedate(resReport.getBasedate());
		report.setContent(resReport.getContent());
		report.setUser(user);
		report.setBook(book);
		
		reportRepository.save(report);

		// 등록하면 coupon 추가
		if(resReport.getId() == null) {
			Coupon coupon = new Coupon();
			coupon.setBasedate(resReport.getBasedate());
			coupon.setContent("독후감 - " + book.getName());
			coupon.setPlaytime(10);
			coupon.setType(CouponType.BOOK);
			coupon.setUser(user);
			
			couponService.insertCoupon(coupon);
		}
		
	}
	// 수정
	public void update(ReportVo resReport) {
		
		Report oriReport = reportRepository.findById(resReport.getId()).get();
		oriReport.setContent(resReport.getContent());
		reportRepository.save(oriReport);
	}
	
	// 삭제(관리자용)
	public void delete(int id) {
		reportRepository.deleteById(id);
	}
	// 조회 1건
	public ReportVo findById(int id){
		Report report = reportRepository.findById(id).orElseGet(()-> new Report());
		
		ReportVo vo = new ReportVo();
		vo.setId(report.getId());
		vo.setBasedate(report.getBasedate());
		vo.setContent(report.getContent());
		vo.setBookid(report.getBook().getId());
		vo.setBookname(report.getBook().getName());
		vo.setUserid(report.getUser().getUserid());
		vo.setUsername(report.getUser().getName());

		return vo;	
	}
	
	
	// 조회 : 사용자 + 기준일자
	public List<ReportVo> findByUserToday(String userid, String selDate){
		User user = new User();
		user.setUserid(userid);
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate sdate = LocalDate.parse(selDate,f);
		
		List<Report> reportList = new ArrayList<>();
		reportList = reportRepository.findAllByUserAndBasedateOrderByIdDesc(user, sdate);
		
		List<ReportVo> reports = new ArrayList<>();
		for(Report report : reportList) {
			ReportVo vo = new ReportVo();
			vo.setId(report.getId());
			vo.setBasedate(report.getBasedate());
			vo.setContent(report.getContent());
			vo.setBookid(report.getBook().getId());
			vo.setBookname(report.getBook().getName());
			vo.setUserid(report.getUser().getUserid());
			vo.setUsername(report.getUser().getName());
			reports.add(vo);
		}
		return reports;	
	}
	
	// 조회 기간
	public List<ReportVo> findAllByBasedate(String start, String end){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		
		List<Report> reportList = new ArrayList<>();
		reportList = reportRepository.findAllByBasedateBetweenOrderByIdDesc(stdate, eddate);
		
		List<ReportVo> reports = new ArrayList<>();
		for(Report report : reportList) {
			ReportVo vo = new ReportVo();
			vo.setId(report.getId());
			vo.setBasedate(report.getBasedate());
			vo.setContent(report.getContent());
			vo.setBookid(report.getBook().getId());
			vo.setBookname(report.getBook().getName());
			vo.setUserid(report.getUser().getUserid());
			vo.setUsername(report.getUser().getName());
			reports.add(vo);
		}
		return reports;	
	}
	
	// 조회 사용자별 + 기간
	public List<ReportVo> findAllByUseridAndBasedate(String userid, String start, String end){
		User user = new User();
		user.setUserid(userid);
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		
		List<Report> reportList = new ArrayList<>();
		reportList = reportRepository.findAllByUserAndBasedateBetweenOrderByIdDesc(user, stdate, eddate);
		
		List<ReportVo> reports = new ArrayList<>();
		for(Report report : reportList) {
			ReportVo vo = new ReportVo();
			vo.setId(report.getId());
			vo.setBasedate(report.getBasedate());
			vo.setContent(report.getContent());
			vo.setBookid(report.getBook().getId());
			vo.setBookname(report.getBook().getName());
			vo.setUserid(report.getUser().getUserid());
			vo.setUsername(report.getUser().getName());
			reports.add(vo);
		}
		return reports;	
	}
	
}
