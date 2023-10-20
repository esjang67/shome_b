package com.esjang.sthome.controller;

import java.util.List;

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

import com.esjang.sthome.domain.SearchDate;
import com.esjang.sthome.domain.Suggest;

import com.esjang.sthome.service.SuggestService;

@RestController
public class SuggestController {

	@Autowired
	private SuggestService suggestService;
	
	// 등록
	@PostMapping("/suggest")
	public ResponseEntity<?> insert(@RequestBody Suggest suggest){
		System.out.println("요청: " + suggest);
		suggestService.insert(suggest);
		return new ResponseEntity<>("등록 성공", HttpStatus.OK);
	}
	
	// 수정 (type, content)
	@PutMapping("/suggest")
	public ResponseEntity<?> update(@RequestBody Suggest suggest){
		System.out.println("요청: " + suggest);
		suggestService.updateToTypeContent(suggest);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 수정 (okflag)
	@PutMapping("/suggest/ok/{id}")
	public ResponseEntity<?> updateOkflag(@PathVariable int id){
		System.out.println("요청: " + id);
		suggestService.updateToOkflag(id);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/suggest/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		System.out.println("요청: delete id " + id);
		suggestService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
	// 조회 : 1건
	@GetMapping("/suggest/{id}")
	public ResponseEntity<?> get(@PathVariable int id){
		Suggest suggest = suggestService.findById(id);
		return new ResponseEntity<>(suggest, HttpStatus.OK);
	}
	
	// 조회(기간)
	@GetMapping("/suggest/all")
	public ResponseEntity<?> getListBasedate(@RequestBody SearchDate searchDate){
		List<Suggest> list = suggestService.getAllByBasedate(searchDate.getStartDate(), searchDate.getEndDate());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 조회(사용자, 기간)
	@GetMapping("/suggest/all/{userid}")
	public ResponseEntity<?> getListUseridBasedate(@PathVariable String userid, @RequestBody SearchDate searchDate){
		List<Suggest> list = suggestService.getAllByUseridBasedate(userid, searchDate.getStartDate(), searchDate.getEndDate());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 조회(사용자, 기간, OK)
	@GetMapping("/suggest/all/ok/{userid}")
	public ResponseEntity<?> getListUseridBasedateOK(@PathVariable String userid, @RequestBody SearchDate searchDate){
		List<Suggest> list = suggestService.getAllByUseridBasedateOK(userid, searchDate.getStartDate(), searchDate.getEndDate(), "Y");
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 조회(기간, OK)
	@GetMapping("/suggest/all/ok")
	public ResponseEntity<?> getListBasedateOK(@RequestBody SearchDate searchDate){
		List<Suggest> list = suggestService.getAllByBasedateOK(searchDate.getStartDate(), searchDate.getEndDate(), "Y");
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
