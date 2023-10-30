package com.esjang.sthome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.DoItBatch;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.DoItBatchRepository;
import com.esjang.sthome.repository.UserRepository;

@Service
public class DoItBatchService {
	
	@Autowired
	private DoItBatchRepository doItBatchRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// 조회 : 전체(관리자용)
	public List<DoItBatch> getAll(){
		return doItBatchRepository.findAll();
	}	 
	
	// 조회 : 전체(관리자용)
	public List<DoItBatch> getAllByUser(String userid){
		User user = new User();
		user = userRepository.findById(userid).get();
		return doItBatchRepository.findAllListByUser(user);
	}	 
	
	// 조회 : 1건
	public DoItBatch get(Integer id) {
		return doItBatchRepository.findById(id).get();
	}
	
	// 등록
	public void insert(DoItBatch doitBatch){
		doItBatchRepository.save(doitBatch);
	}
	
	// 수정 : content, defineday
	public void update(DoItBatch doItBatch){
		DoItBatch oriDoItBatch = doItBatchRepository.findById(doItBatch.getId()).get();
		oriDoItBatch.setDefineday(doItBatch.getDefineday());
		oriDoItBatch.setContent(doItBatch.getContent());
		doItBatchRepository.save(oriDoItBatch);
	}
	
	// 삭제 : 관리자만 사용할수있는 삭제 (데이터오류로 인한 진짜 삭제 필요할때)
	public void delete(Integer id){
		doItBatchRepository.deleteById(id);
	}
		
}
