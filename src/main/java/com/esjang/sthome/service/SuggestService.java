package com.esjang.sthome.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esjang.sthome.domain.Schedule;
import com.esjang.sthome.domain.Suggest;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.SuggestRopository;

@Service
public class SuggestService {
	
	@Autowired
	private SuggestRopository suggestRopository;
	
	@Autowired
	private ScheduleService scheduleService;
	
	// 등록
	public void insert(Suggest suggest) {
		suggestRopository.save(suggest);
	}
	// 수정 (type, content)
	public void updateToTypeContent(Suggest suggest) {
		Suggest oriSuggest = suggestRopository.findById(suggest.getId()).get();
		oriSuggest.setType(suggest.getType());
		oriSuggest.setContent(suggest.getContent());
		suggestRopository.save(oriSuggest);
	}
	
	// 수정 (okflag)
	@Transactional
	public void updateToOkflag(Integer id, String selDate) {
		Suggest oriSuggest = suggestRopository.findById(id).get();
		oriSuggest.setOkflag("Y");
		suggestRopository.save(oriSuggest);
		
		// ok 했으면 일정에 추가할것!!!
		Schedule schedule = new Schedule();
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(selDate,f);
	
		schedule.setBasedate(stdate);
		schedule.setContent("[" + oriSuggest.getUser().getName() + "] " + oriSuggest.getContent());
		scheduleService.insert(schedule);
	}
	
	// 삭제
	public void delete(Integer id) {
		suggestRopository.deleteById(id);
	}
	
	// 조회 : 1건
	public Suggest findById(Integer id){
		Suggest suggest = suggestRopository.findById(id).orElseGet(()-> new Suggest());
		System.out.println("SuggestService : findById " + id + " : " + suggest);
		return suggest;
	}
	
	//	조회(기간)
	public List<Suggest> getAllByBasedate(String start, String end){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		return suggestRopository.findByBasedateBetweenOrderByIdDesc(stdate, eddate);
	}
	
	//	조회(사용자, 기간)
	public List<Suggest> getAllByUseridBasedate(String userid, String start, String end){
		User user = new User();
		user.setUserid(userid);
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		return suggestRopository.findByUserAndBasedateBetweenOrderByIdDesc(user, stdate, eddate);
	}
	
	//	조회(사용자, 기간, OK)
	public List<Suggest> getAllByUseridBasedateOK(String userid, String start, String end, String okflag){
		User user = new User();
		user.setUserid(userid);
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		return suggestRopository.findByUserAndBasedateBetweenAndOkflagIsOrderByIdDesc(user, stdate, eddate, okflag);
	}
	//	조회(기간, OK)	
	public List<Suggest> getAllByBasedateOK(String start, String end, String okflag){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		return suggestRopository.findByBasedateBetweenAndOkflagIsOrderByIdDesc(stdate, eddate, okflag);
	}
	
}
