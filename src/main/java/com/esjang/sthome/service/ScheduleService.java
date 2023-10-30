package com.esjang.sthome.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.Schedule;
import com.esjang.sthome.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	// 등록
	public void insert(Schedule schedule) {
		scheduleRepository.save(schedule);
	}
	
	// 수정
	public void update(Schedule schedule) {
		Schedule oriSchedule = scheduleRepository.findById(schedule.getId()).get();
		oriSchedule.setContent(schedule.getContent());
		scheduleRepository.save(oriSchedule);
	}
	// 삭제
	public void delete(int id) {
		scheduleRepository.deleteById(id);
	}
	
	// 조회 : 1건
	public Schedule findById(int id) {
		return scheduleRepository.findById(id).get();
	}
	
	// 조회(관리자) : 기간
	public List<Schedule> getAllByDateRange(String start, String end){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		return scheduleRepository.findAllByBasedateBetweenOrderByBasedateDesc(stdate, eddate);
	}

}
