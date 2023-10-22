package com.esjang.sthome.controller;

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

import com.esjang.sthome.domain.DoItBatch;
import com.esjang.sthome.service.DoItBatchService;

@RestController
public class DoItBatchController {
	
	@Autowired
	private DoItBatchService doItBatchService;
	
	// 등록
	@PostMapping("/doitbatch")
	public ResponseEntity<?> insert(@RequestBody DoItBatch doItBatch){
		System.out.println("요청: " + doItBatch);
		doItBatchService.insert(doItBatch);
		return new ResponseEntity<>("저장 성공", HttpStatus.OK);
	}
	
	// 수정 : content
	@PutMapping("/doitbatch")
	public ResponseEntity<?> update(@RequestBody DoItBatch doItBatch){
		System.out.println("요청: " + doItBatch);
		doItBatchService.update(doItBatch);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제 : 관리자만 사용할수있는 삭제 (데이터오류로 인한 진짜 삭제필요함)
	@DeleteMapping("/doitbatch/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		doItBatchService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}

	// 조회 : 전체(관리자용)
	@GetMapping("/doitbatch/all")
	public ResponseEntity<?> getAll(){
		System.out.println("요청: ");
		return new ResponseEntity<>(doItBatchService.getAll(), HttpStatus.OK);
	}
	
	// 조회 : 1건
	@GetMapping("/doitbatch")
	public ResponseEntity<?> get(@RequestParam("id") Integer id){
		return new ResponseEntity<>(doItBatchService.get(id), HttpStatus.OK);
	}
}


//{
//    "userid":"MIN",
//    "defineday":"월",
//    "content":"수학문제집"
//}
