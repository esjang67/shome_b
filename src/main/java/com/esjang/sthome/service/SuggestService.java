package com.esjang.sthome.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.Suggest;
import com.esjang.sthome.repository.SuggestRopository;

@Service
public class SuggestService {
	
	@Autowired
	private SuggestRopository suggestRopository;
	
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
	public void updateToOkflag(int id) {
		Suggest oriSuggest = suggestRopository.findById(id).get();
		oriSuggest.setOkflag("Y");
		suggestRopository.save(oriSuggest);
	}
	
	// 삭제
	public void delete(int id) {
		suggestRopository.deleteById(id);
	}
	
	// 조회 : 1건
	public Suggest findById(int id){
		Suggest suggest = suggestRopository.findById(id).orElseGet(()-> new Suggest());
		System.out.println("SuggestService : findById " + id + " : " + suggest);
		return suggest;
	}
	
	//	조회(기간)
	public List<Suggest> getAllByBasedate(Date start, Date end){
		return suggestRopository.findByBasedateBetween(start, end);
	}
	
	//	조회(사용자, 기간)
	public List<Suggest> getAllByUseridBasedate(String userid, Date start, Date end){
		return suggestRopository.findByUserAndBasedateBetween(userid, start, end);
	}
	
	//	조회(사용자, 기간, OK)
	public List<Suggest> getAllByUseridBasedateOK(String userid, Date start, Date end, String okflag){
		return suggestRopository.findByUserAndBasedateBetweenAndOkflagIs(userid, start, end, okflag);
	}
	//	조회(기간, OK)	
	public List<Suggest> getAllByBasedateOK(Date start, Date end, String okflag){
		return suggestRopository.findByBasedateBetweenAndOkflagIs(start, end, okflag);
	}
	
}
