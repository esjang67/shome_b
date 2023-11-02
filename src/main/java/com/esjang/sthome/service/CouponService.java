package com.esjang.sthome.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.Coupon;
import com.esjang.sthome.domain.CouponTime;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.CouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponTimeService timeService;
	
	// 조회 : 기간
	public List<Coupon> getAllByDateRange(String start, String end){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		return couponRepository.findByBasedateBetweenOrderByIdDesc(stdate, eddate);
	}

	// 조회 : 사용자별, 기간
	public List<Coupon> getAllByUserDateRange(String userid, String start, String end){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate stdate = LocalDate.parse(start,f);
		LocalDate eddate = LocalDate.parse(end,f);
		User user = new User();
		user.setUserid(userid);
		return couponRepository.findByUserAndBasedateBetweenOrderByIdDesc(user, stdate, eddate);
	}
		
	// [시스템]
	// Coupon
	// 등록 : coupon에 등록 + coupontime에 추가(coupontime에 없으면 등록할것!)
	// 추후 기타 추가건 생길 수 있으므로
	public void insertCoupon(Coupon coupon) {
		
		couponRepository.save(coupon);
		
		//----------------------------------------------
		// 1 time에 userid 확인
		String userid = coupon.getUser().getUserid();
		CouponTime time = timeService.getTimeByUserid(userid);
		
		// 1-1 userid 가 없으면 생성함
		if(time.getUser() == null) {
			time = timeService.insertTime(userid);
		}
		// 2 time 에 Playtime 더하기
		timeService.updateAddTime(userid, coupon.getPlaytime());
		
	}
	
	// 수정
	// 삭제
	
}
