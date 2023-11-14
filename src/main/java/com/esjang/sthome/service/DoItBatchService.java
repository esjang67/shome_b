package com.esjang.sthome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.DoItBatch;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.DoItBatchRepository;
import com.esjang.sthome.repository.UserRepository;
import com.esjang.sthome.vo.DoitBatchVo;

@Service
public class DoItBatchService {
	
	@Autowired
	private DoItBatchRepository doItBatchRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// 조회 : 전체(관리자용)
	public List<DoitBatchVo> getAll(){
		List<DoItBatch> batchList = new ArrayList<>();
		batchList = doItBatchRepository.findAll();
		
		List<DoitBatchVo> batchs = new ArrayList<>();
		for(DoItBatch batch : batchList) {
			DoitBatchVo vo = new DoitBatchVo();
			vo.setId(batch.getId());
			vo.setDefineday(batch.getDefineday());
			vo.setContent(batch.getContent());
			vo.setUserid(batch.getUser().getUserid());
			vo.setUsername(batch.getUser().getName());
			batchs.add(vo);
		}
		return batchs;		
		 
	}	 
	
	// 조회 : 전체(관리자용)
	public List<DoitBatchVo> getAllByUser(String userid){
		User user = new User();
		user = userRepository.findById(userid).get();
		
		List<DoItBatch> batchList = new ArrayList<>();
		batchList = doItBatchRepository.findAllListByUser(user);
		
		List<DoitBatchVo> batchs = new ArrayList<>();
		for(DoItBatch batch : batchList) {
			DoitBatchVo vo = new DoitBatchVo();
			vo.setId(batch.getId());
			vo.setDefineday(batch.getDefineday());
			vo.setContent(batch.getContent());
			vo.setUserid(batch.getUser().getUserid());
			vo.setUsername(batch.getUser().getName());
			batchs.add(vo);
		}
		return batchs;		
	}	 
	
	// 조회 : 1건
	public DoitBatchVo get(Integer id) {
		DoItBatch batch = doItBatchRepository.findById(id).get();
		
		DoitBatchVo vo = new DoitBatchVo();
		vo.setId(batch.getId());
		vo.setDefineday(batch.getDefineday());
		vo.setContent(batch.getContent());
		vo.setUserid(batch.getUser().getUserid());
		vo.setUsername(batch.getUser().getName());
		
		return vo;		
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
