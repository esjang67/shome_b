package com.esjang.sthome.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esjang.sthome.domain.Coupon;
import com.esjang.sthome.service.CouponService;
import com.esjang.sthome.service.CouponTimeService;

@RestController
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CouponTimeService timeService;
	
	// coupon
	// 조회 : 기간 조회
	@GetMapping("/coupon/all")
	public ResponseEntity<?> getAllBasedate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		
		return new ResponseEntity<>(couponService.getAllByDateRange(startDate, endDate), HttpStatus.OK);
	}
	
	// 조회 : 사용자별 기간 조회
	@GetMapping("/coupon/all/user")
	public ResponseEntity<?> getAllBasedateUser(@RequestParam("userid") String userid, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
//		// date format change
//		Date stdate = DateCustom.longToDataCange(startDate);
//		Date eddate = DateCustom.longToDataCange(endDate);
		return new ResponseEntity<>(couponService.getAllByUserDateRange(userid, startDate, endDate), HttpStatus.OK);
	}
	
	// 기타 쿠폰 입력
	@PostMapping("/coupon")
	public ResponseEntity<?> insertCoupon(@RequestBody Coupon coupon) {
		couponService.insertCoupon(coupon);
		return new ResponseEntity<>( "등록 성공", HttpStatus.OK);
	}
	
	// time
	// 쿠폰시간 조회 : 사용자별
	@GetMapping("/coupon/time")
	public ResponseEntity<?> getTimeByUserid(@RequestParam("userid") String userid){
		return new ResponseEntity<>(timeService.getTotalTimeByUserid(userid), HttpStatus.OK);
	}
	
	// 쿠폰시간 조회 : 전체 (관리자)
	@GetMapping("/coupon/time/all")
	public ResponseEntity<?> getTimeAll(){
		return new ResponseEntity<>(timeService.getList(), HttpStatus.OK);
	}
	
	// 쿠폰시간 사용
	@PutMapping("coupon/time")			// 사용자, 사용할 시간 보내기
	public ResponseEntity<?> useTimeByUserid(@RequestBody HashMap<String, String> useTime){
		String userid = useTime.get("id");
		Integer time = Integer.parseInt(useTime.get("time"));
		timeService.updateSubTime(userid, time);
		return new ResponseEntity<>("쿠톤시간 사용", HttpStatus.OK);
	}
	
	
}
