package com.esjang.sthome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.CouponTime;


@Repository
public interface CouponTimeRepository extends JpaRepository<CouponTime, String> {
	
	public CouponTime findByUserid(String userid);
}
