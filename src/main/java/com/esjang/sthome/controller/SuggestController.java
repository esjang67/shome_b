package com.esjang.sthome.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esjang.sthome.domain.Suggest;
import com.esjang.sthome.service.SuggestService;

@RestController
public class SuggestController {

	@Autowired
	private SuggestService suggestService;
	
	// 등록
	@PostMapping("/suggest")
	public ResponseEntity<?> insert(@RequestBody Suggest suggest){
		suggestService.insert(suggest);
		return new ResponseEntity<>("등록 성공", HttpStatus.OK);
	}
	
	// 수정 (type, content)
//	@PutMapping("/suggest")
//	public ResponseEntity<?> update(@RequestBody Suggest suggest){
//		suggestService.updateToTypeContent(suggest);
//		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
//	}
	
	// 수정 (okflag) -> 일정추가할것!!!
	@PutMapping("/suggest/ok/{id}")
	public ResponseEntity<?> updateOkflag(@PathVariable Integer id, @RequestBody HashMap<String, String> seldate){
		suggestService.updateToOkflag(id, seldate.get("seldate"));
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/suggest/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		suggestService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
	// 조회 : 1건
	@GetMapping("/suggest/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id){
		return new ResponseEntity<>(suggestService.findById(id), HttpStatus.OK);
	}
	
	// 조회(기간)
	@GetMapping("/suggest/all")
	public ResponseEntity<?> getListBasedate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return new ResponseEntity<>(suggestService.getAllByBasedate(startDate, endDate), HttpStatus.OK);
	}
	
	// 조회(사용자, 기간)
	@GetMapping("/suggest/all/user")
	public ResponseEntity<?> getListUseridBasedate(@RequestParam("userid") String userid, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return new ResponseEntity<>(suggestService.getAllByUseridBasedate(userid, startDate, endDate), HttpStatus.OK);
	}
	
	// 조회(사용자, 기간, OK)
	@GetMapping("/suggest/all/ok/user")
	public ResponseEntity<?> getListUseridBasedateOK(@RequestParam("userid") String userid, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return new ResponseEntity<>(suggestService.getAllByUseridBasedateOK(userid, startDate, endDate, "Y"), HttpStatus.OK);
	}
	
	// 조회(기간, OK)
	@GetMapping("/suggest/all/ok")
	public ResponseEntity<?> getListBasedateOK(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		return new ResponseEntity<>(suggestService.getAllByBasedateOK(startDate, endDate, "Y"), HttpStatus.OK);
	}
	
}
