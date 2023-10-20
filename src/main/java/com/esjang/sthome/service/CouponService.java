package com.esjang.sthome.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esjang.sthome.domain.Coupon;
import com.esjang.sthome.domain.CouponTime;
import com.esjang.sthome.repository.CouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponTimeService timeService;
	
	// [화면용]
//	// 조회 : 사용자 top 30 list
//	public List<Coupon> getTop30ByUserid(String userid){
//		return couponRepository.findTop30ByUser(userid);
//	}

	// 조회 : 기간
	public List<Coupon> getAllByDateRange(Date startBasedate, Date endBasedate){
		System.out.println(couponRepository.findByBasedateBetween(startBasedate, endBasedate));
		return couponRepository.findByBasedateBetween(startBasedate, endBasedate);
	}

	// 조회 : 사용자별, 기간
	public List<Coupon> getAllByUserDateRange(String userid, Date startBasedate, Date endBasedate){
		System.out.println(couponRepository.findByUserAndBasedateBetween(userid, startBasedate, endBasedate));
		return couponRepository.findByBasedateBetween(startBasedate, endBasedate);
	}
	
	
	// [시스템]
	// Coupon
	// 등록 : coupon에 등록 + coupontime에 추가(coupontime에 없으면 등록할것!)
	// 추후 기타 추가건 생길 수 있으므로
	@Transactional
	public void insertCoupon(Coupon coupon) {
		
		couponRepository.save(coupon);
		
		//----------------------------------------------
		// 1 time에 userid 확인
		String userid = coupon.getUser().getUserid();
		CouponTime time = timeService.getTimeByUserid(userid);
		
		// 1-1 userid 가 없으면 생성함
		if(time.getUserid() == null) {
			time = timeService.insertTime(userid);
		}
		// 2 time 에 Playtime 더하기
		timeService.updateAddTime(userid, coupon.getPlaytime());
		
	}
	
	// 수정
	// 삭제
	
}
