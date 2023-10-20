package com.esjang.sthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.Books;
import com.esjang.sthome.repository.BoosRepository;

@Service
public class BooksService {
	
	@Autowired
	private BoosRepository booksRepository;
	
	// 들록
	public void insert(Books book) {
		booksRepository.save(book);
	}
	// 수정(이름, 삭제여부)
	public void update(Books book) {
		Books oriBook = booksRepository.findById(book.getId()).get();
		oriBook.setName(book.getName());
		oriBook.setDelyn(book.isDelyn());
		
		booksRepository.save(oriBook);
	}
	
	// 삭제(관리자용)
	public void delete(int id) {
		booksRepository.deleteById(id);
	}
	// 조회 bookcolid(전집id)로 조회하는 list
	public List<Books> getAllListByBookcolId(int bookcolid){
		List<Books> list = booksRepository.findAllBybookCollect(bookcolid);
		System.out.println("BooksService : getAllListByBookcolId " + list);
		return list;
	}
	
	// 조회 : 책
	public Books getById(int id) {
		return booksRepository.findById(id).get();
	}
	
	
}
