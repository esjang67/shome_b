package com.esjang.sthome.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esjang.sthome.domain.Report;
import com.esjang.sthome.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	// 등록
	@PostMapping("/report")
	public ResponseEntity<?> insert(@RequestBody Report report){
		System.out.println("요청: " + report);
		reportService.insert(report);
		return new ResponseEntity<>("등록 성공", HttpStatus.OK);
	}	
	
	// 수정
	@PutMapping("/report")
	public ResponseEntity<?> update(@RequestBody Report report){
		System.out.println("요청: " + report);
		reportService.update(report);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제(관리자용)
	@DeleteMapping("/report/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		System.out.println("요청: delete id " + id);
		reportService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
	// 조회 
	@GetMapping("/report/{id}")
	public ResponseEntity<?> get(@PathVariable int id){
		Report report = reportService.findById(id);
		System.out.println("요청: " + report);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	// 조회(기간)
	@GetMapping("/report/all")
	public ResponseEntity<?> getListByBasedate(Date start, Date end){
		return new ResponseEntity<>(reportService.findAllByBasedate(start, end), HttpStatus.OK);
	}
	// 조회(사용자별 + 기간)
	@GetMapping("/report/all/{userid}")
	public ResponseEntity<?> getListByBasedate(@PathVariable String userid, Date start, Date end){
		return new ResponseEntity<>(reportService.findAllByUseridAndBasedate(userid, start, end), HttpStatus.OK);
	}
	
}
