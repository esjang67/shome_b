package com.esjang.sthome.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.CouponTime;



@Repository
public interface CouponTimeRepository extends JpaRepository<CouponTime, String> {
	
	public Optional<CouponTime> findById(String id);
}

