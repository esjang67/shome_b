package com.esjang.sthome.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.Coupon;
import com.esjang.sthome.domain.CouponType;
import com.esjang.sthome.domain.User;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
	
	public Coupon findByUser(String userid);
	
	// 기간 조회
	public List<Coupon> findByBasedateBetweenOrderByIdDesc(LocalDate start, LocalDate end);
	
	// 사용자별  기간 조회
	public List<Coupon> findByUserAndBasedateBetweenOrderByIdDesc(User user, LocalDate start, LocalDate end);
	
	// 쿠폰 발급 여부 확인(사용자 + 일자 + 타입)
	public int countByUserAndBasedateAndType(User user, LocalDate date, CouponType type);
	
	// 쿠폰 삭제(사용자 + 일자 + 타입)
	public void deleteByUserAndBasedateAndType(User user, LocalDate date, CouponType type);
	
}
