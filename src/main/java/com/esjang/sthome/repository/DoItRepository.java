package com.esjang.sthome.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.DoIt;

@Repository
public interface DoItRepository extends JpaRepository<DoIt, Integer> {
	
	public List<DoIt> findAllByUserAndBasedate(String userid, Date basedate);
	
	public void deleteByUserAndBasedate(String userid, Date basedate);
	
	
	
}
