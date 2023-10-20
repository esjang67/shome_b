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

import com.esjang.sthome.domain.BookCollect;
import com.esjang.sthome.service.BookCollectService;

@RestController
public class BookCollectController {

	@Autowired
	private BookCollectService bookColService;
	
	// 등록
	@PostMapping("/collect")
	public ResponseEntity<?> insert(@RequestBody BookCollect collect){
		System.out.println("요청: " + collect);
		bookColService.insert(collect);
		return new ResponseEntity<>("등록 성공", HttpStatus.OK);
	}	
	
	// 수정(이름, 삭제여부)
	@PutMapping("/collect")
	public ResponseEntity<?> update(@RequestBody BookCollect collect){
		System.out.println("요청: " + collect);
		bookColService.update(collect);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제(관리자용)
	@DeleteMapping("/collect/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		System.out.println("요청: delete id " + id);
		bookColService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
	// 조회
	@GetMapping("/collect")
	public ResponseEntity<?> getListByColid(){
		List<BookCollect> list = bookColService.getAllList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
