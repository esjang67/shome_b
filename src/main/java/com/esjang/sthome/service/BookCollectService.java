package com.esjang.sthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.BookCollect;
import com.esjang.sthome.repository.BookCollectRepository;

@Service
public class BookCollectService {

	@Autowired
	private BookCollectRepository bookColRepository;
	
	// 들록
	public void insert(BookCollect collect) {
		bookColRepository.save(collect);
	}
	// 수정(이름, 삭제여부)
	public void update(BookCollect collect) {
		BookCollect oricollect = bookColRepository.findById(collect.getId()).get();
		oricollect.setName(collect.getName());
		oricollect.setDelyn(collect.getDelyn());
		
		bookColRepository.save(oricollect);
	}
	
	// 삭제(관리자용)
	public void delete(int id) {
		bookColRepository.deleteById(id);
	}
	// 전체 조회 
	public List<BookCollect> getAllList(){
		List<BookCollect> list = bookColRepository.findAll();
		System.out.println("BookCollectService : getAllListByBookcolId " + list);
		return list;
	}
	
	// 조회(1건) 
	public BookCollect getData(Integer id){
		return bookColRepository.findById(id).get();
	}
	
}
