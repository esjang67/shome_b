package com.esjang.sthome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.CouponTime;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.CouponTimeRepository;
import com.esjang.sthome.repository.UserRepository;
import com.esjang.sthome.vo.CouponTimeVo;

@Service
public class CouponTimeService {

	@Autowired
	private CouponTimeRepository timeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//---------------------------------------------------
	// Time 조회
	public List<CouponTimeVo> getList(){
		
		List<CouponTime> couponList = timeRepository.findAll();
		
		List<CouponTimeVo> times = new ArrayList<>();
		for(CouponTime time : couponList) {
			CouponTimeVo vo = new CouponTimeVo();
			vo.setId(time.getId());
			vo.setTotaltime(time.getTotaltime());
			vo.setUserid(time.getUser().getUserid());
			vo.setUsername(time.getUser().getName());
			times.add(vo);
		}
		return times;	
	}
	
	// 사용자별 총시간
	public Integer getTotalTimeByUserid(String userid){
		CouponTime time = timeRepository.findById(userid).orElseGet(()-> new CouponTime()); 
		return time.getTotaltime();
	}
	
	// 사용자별 정보 가져오기
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
		User user = new User();
		user = userRepository.findById(userid).get();
		CouponTime time = new CouponTime();
		time.setId(userid);
		time.setUser(user);
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
