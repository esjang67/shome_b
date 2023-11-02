package com.esjang.sthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esjang.sthome.service.DoItService;

@RestController
public class DoItController {
	
	@Autowired
	private DoItService doItService;
	
	// 조회 : 기준일자 + 사용자 로 조회
	@GetMapping("/doit")
	public ResponseEntity<?> get(@RequestParam("userid") String userid, @RequestParam("basedate") String basedate){
		return new ResponseEntity<>(doItService.getAllByUseridAndBasedate(userid, basedate), HttpStatus.OK);
	} 
	
	// 할일 조회(전체, 기준일자)
	@GetMapping("/doit/all")
	public ResponseEntity<?> getAll(@RequestParam("basedate") String basedate){
		return new ResponseEntity<>(doItService.getAllByBasedate(basedate), HttpStatus.OK);
	} 
	
	// 수정 : done true
	@PutMapping("/doit/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id){
		doItService.updateToDone(id);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제(관리자) : 기준일자 + 사용자 당일 데이터 삭제
	@DeleteMapping("/doit")
	public ResponseEntity<?> delete(@RequestParam("userid") String userid, @RequestParam("basedate") String basedate){
		doItService.deleteToDayList(userid, basedate);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
}
