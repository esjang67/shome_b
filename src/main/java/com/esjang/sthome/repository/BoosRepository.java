package com.esjang.sthome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esjang.sthome.domain.BookCollect;
import com.esjang.sthome.domain.Books;

@Repository
public interface BoosRepository extends JpaRepository<Books, Integer> {

	// 전집id로 리스트 조회
	public List<Books> findAllByBookcollect(BookCollect bookCollect);
	
}
