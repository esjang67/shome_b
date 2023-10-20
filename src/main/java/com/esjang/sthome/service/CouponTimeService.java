package com.esjang.sthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.CouponTime;
import com.esjang.sthome.repository.CouponTimeRepository;

@Service
public class CouponTimeService {

	@Autowired
	private CouponTimeRepository timeRepository;
	
	//---------------------------------------------------
	// Time 조회
	public List<CouponTime> getList(){
		return timeRepository.findAll();
	}
	
	public CouponTime getTimeByUserid(String userid){
		return timeRepository.findById(userid).orElseGet(()-> new CouponTime());
	}
	
	// 수정 : 시간 -
	public void updateSubTime(String userid, int subTime) {
		CouponTime time = timeRepository.findById(userid).get();
		time.setTotaltime(time.getTotaltime() - subTime);
		timeRepository.save(time);
	}	
	
	//-------------------------------------
	// COUPON SERVISE 연동 메서드 정의
	//-------------------------------------
	// 등록
	public CouponTime insertTime(String userid) {
		CouponTime time = new CouponTime();
		time.setUserid(userid);
		time.setTotaltime(0);
		return timeRepository.save(time);
	}
	
	// 수정 : 시간 +
	public void updateAddTime(String userid, int addTime) {
		CouponTime time = timeRepository.findById(userid).get();
		time.setTotaltime(time.getTotaltime() + addTime);
		timeRepository.save(time);
	}	
	
}
