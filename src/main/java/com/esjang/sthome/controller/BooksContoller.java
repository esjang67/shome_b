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

import com.esjang.sthome.domain.Books;
import com.esjang.sthome.service.BooksService;

@RestController
public class BooksContoller {

	@Autowired
	private BooksService booksService;
	
	// 등록
	@PostMapping("/book")
	public ResponseEntity<?> insert(@RequestBody Books book){
		System.out.println("요청: " + book);
		booksService.insert(book);
		return new ResponseEntity<>("등록 성공", HttpStatus.OK);
	}	
	
	// 수정(이름, 삭제여부)
	@PutMapping("/book")
	public ResponseEntity<?> update(@RequestBody Books book){
		System.out.println("요청: " + book);
		booksService.update(book);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제(관리자용)
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		System.out.println("요청: delete id " + id);
		booksService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
	// 조회 책ID
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable int id){
		return new ResponseEntity<>(booksService.getById(id), HttpStatus.OK);
	}
	
	// 조회 (전집id로 조회하는 list)
	@GetMapping("/book/all/{colid}")
	public ResponseEntity<?> getListByColid(@PathVariable int colid){
		System.out.println("요청: list colid " + colid);
		List<Books> list = booksService.getAllListByBookcolId(colid);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
