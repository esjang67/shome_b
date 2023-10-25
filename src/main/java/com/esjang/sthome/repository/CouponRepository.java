package com.esjang.sthome.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	public Coupon findByUser(String userid);
	
	// 사용자 + 남은 시간있음 list
//	public List<Coupon> findByUserAndPlaytimeGreaterThan(String userid, int playtime);
	
	// 사용자 top30 list
//	public List<Coupon> findTop30ByUser(String userid);
	
	// 기간 조회
	public List<Coupon> findByBasedateBetween(LocalDate start, LocalDate end);
	
	// 사용자별  기간 조회
	public List<Coupon> findByUserAndBasedateBetween(String userid, LocalDate start, LocalDate end);
}
