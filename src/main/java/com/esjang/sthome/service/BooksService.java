package com.esjang.sthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.BookCollect;
import com.esjang.sthome.domain.Books;
import com.esjang.sthome.repository.BookCollectRepository;
import com.esjang.sthome.repository.BoosRepository;

@Service
public class BooksService {
	
	@Autowired
	private BoosRepository booksRepository;
	
	@Autowired
	private BookCollectRepository bookCollectRepository;
	
	// 들록
	public void insert(Books book) {
		booksRepository.save(book);
	}
	// 수정(이름, 삭제여부)
	public void update(Books book) {
		Books oriBook = booksRepository.findById(book.getId()).get();
		oriBook.setName(book.getName());
		oriBook.setDelyn(book.getDelyn());
		
		booksRepository.save(oriBook);
	}
	
	// 삭제(관리자용)
	public void delete(int id) {
		booksRepository.deleteById(id);
	}
	// 조회 bookcolid(전집id)로 조회하는 list
	public List<Books> getAllListByCollectid(Integer bookcolid){
		BookCollect bookCollect = bookCollectRepository.findById(bookcolid).get();
		List<Books> list = booksRepository.findAllByBookcollect(bookCollect);
		System.out.println("BooksService : getAllListByBookcolId " + list);
		return list;
	}
	
	// 조회 : 책
	public Books getById(int id) {
		return booksRepository.findById(id).get();
	}
	
	
}
