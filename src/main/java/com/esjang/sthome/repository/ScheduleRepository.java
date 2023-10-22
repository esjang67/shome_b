package com.esjang.sthome.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	
	// 조회 : 기간
	public List<Schedule> findAllByBasedateBetween(Date startBasedate, Date endBasedate);
	
}
