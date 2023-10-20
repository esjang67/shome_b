package com.esjang.sthome.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esjang.sthome.domain.DayType;
import com.esjang.sthome.domain.DoIt;
import com.esjang.sthome.domain.DoItBatch;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.DoItBatchRepository;
import com.esjang.sthome.repository.DoItRepository;
import com.esjang.sthome.repository.UserRepository;

@Service
public class DoItService {

	@Autowired
	private DoItRepository doItRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoItBatchRepository doItBatchRepository;
	
	// doit 리스트는 DoItBatch 에서 가져오므로 조회시 작업이 필요함
	// 조회 : userid, basedate 전체 
	public List<DoIt> getAllByUseridAndBasedate(DoIt doit){
		// 1 doit에 기준일자+사용자 로 저장된 리스트가 있는지 확인
		List<DoIt> doitList = doItRepository.findAllByUserAndBasedate(doit.getUser().getUserid(), doit.getBasedate());
		
		// 2 1번 리스트가 없으면 DoItBatch 에서 조회 insert 작업
		if(doitList == null || doitList.size() == 0) {
			// basedate -> 요일로 변경해야함
			String dayStr = getDayStr(doit.getBasedate());
			
			List<DoItBatch> batchList = getFromDoItBatchByUserDay(doit.getUser().getUserid(), dayStr);
			insertDoit(batchList, doit);
			
			doitList = doItRepository.findAllByUserAndBasedate(doit.getUser().getUserid(), doit.getBasedate());
		}

		return doitList;
	}

	// 등록 : 조회 작업시 작업됨	
	
	// 수정 : done -> true
	public void updateToDone(int id) {
		DoIt doit = doItRepository.findById(id).get();
		doit.setDone("Y");
		doItRepository.save(doit);
	}	
	// 삭제 : userid, basedate 전체 삭제
	public void deleteToDayList(DoIt doit){
		doItRepository.deleteByUserAndBasedate(doit.getUser().getUserid(), doit.getBasedate());
	}
	
	// DoItBatch 에서 조회
	// 조회 : 요일 + 사용자 으로 조회한 list
	private List<DoItBatch> getFromDoItBatchByUserDay(String userid, String defineday){
		System.out.println(doItBatchRepository.findAllListByUserAndDefineday(userid, DayType.valueOf(defineday)));
		return doItBatchRepository.findAllListByUserAndDefineday(userid, DayType.valueOf(defineday));
	}
	
	// doitList insert : DoItBatch -> DoIt 으로 변경해서 저장함
	@Transactional
	private void insertDoit(List<DoItBatch> batchs, DoIt setDoIt) {
		System.out.println("DoItBatch List Insert Cnt : " + batchs.size());
		User user = userRepository.findById(setDoIt.getUser().getUserid()).get();
		
		for(DoItBatch batch : batchs) {
			// DoIt 으로 변경함
			DoIt doit = new DoIt();
			doit.setBasedate(setDoIt.getBasedate());
			doit.setUser(user);
			doit.setContent(batch.getContent());
			doit.setDone("Y");
			
			System.out.println("Doit New Save : " + doit);
			doItRepository.save(doit);
		}
	}
	
	// String  basedate에서 요일 가져오기
	private String getDayStr(Date basedate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(basedate);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        
		String result="";
        switch (dayOfWeek) {
		case 1: result= "일"; break; 
		case 2: result= "월"; break;
		case 3: result= "화"; break;
		case 4: result= "수"; break;
		case 5: result= "목"; break;
		case 6: result= "금"; break;
		case 7: result= "토"; break;
		} 
        return result;
	}	

}
