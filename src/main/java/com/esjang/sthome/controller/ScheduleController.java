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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esjang.sthome.domain.Schedule;
import com.esjang.sthome.service.ScheduleService;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	// 등록
	@PostMapping("/schedule")
	public ResponseEntity<?> insert(@RequestBody Schedule schedule){
		scheduleService.insert(schedule);
		return new ResponseEntity<>("등록 성공", HttpStatus.OK);
	}
	
	// 수정
	@PutMapping("/schedule")
	public ResponseEntity<?> update(@RequestBody Schedule schedule){
		scheduleService.update(schedule);
		return new ResponseEntity<>("수정 성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		scheduleService.delete(id);
		return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
	}
	
	// 조회 : 1건
	@GetMapping("/schedule/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id){
		Schedule schedule = scheduleService.findById(id);
		return new ResponseEntity<>(schedule, HttpStatus.OK);
	}
	
	// 조회(관리자) : 기간
	@GetMapping("/schedule/all")
	public ResponseEntity<?> getList(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		List<Schedule> list = scheduleService.getAllByDateRange(startDate, endDate);
		System.out.println(list);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	 
}
