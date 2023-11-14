package com.esjang.sthome.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esjang.sthome.domain.Coupon;
import com.esjang.sthome.domain.CouponType;
import com.esjang.sthome.domain.DoIt;
import com.esjang.sthome.domain.DoItBatch;
import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.CouponRepository;
import com.esjang.sthome.repository.DoItBatchRepository;
import com.esjang.sthome.repository.DoItRepository;
import com.esjang.sthome.repository.UserRepository;
import com.esjang.sthome.vo.DoitVo;

@Service
public class DoItService {

	@Autowired
	private DoItRepository doItRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoItBatchRepository doItBatchRepository;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponTimeService couponTimeService;
	
	// 할일 조회(전체, 기준일자)
	public List<DoitVo> getAllByBasedate(String basedate){
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate seldate = LocalDate.parse(basedate,f);
		List<DoIt> doitList = doItRepository.findListByBasedate(seldate);
		
		List<DoitVo> doits = new ArrayList<>();
		for(DoIt doit : doitList) {
			DoitVo vo = new DoitVo();
			vo.setId(doit.getId());
			vo.setBasedate(doit.getBasedate());
			vo.setContent(doit.getContent());
			vo.setDone(doit.getDone());
			vo.setUserid(doit.getUser().getUserid());
			vo.setUsername(doit.getUser().getName());
			doits.add(vo);
		}
		return doits;
	}
	
	// doit 리스트는 DoItBatch 에서 가져오므로 조회시 작업이 필요함
	// 조회 : userid, basedate 전체 
	@Transactional
	public List<DoitVo> getAllByUseridAndBasedate(String userid, String basedate){
		// 1 doit에 기준일자+사용자 로 저장된 리스트가 있는지 확인
		User user = new User();
		user.setUserid(userid);
		
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate seldate = LocalDate.parse(basedate,f);
		  
		List<DoIt> doitList = doItRepository.findListByUserAndBasedate(user, seldate);
		
		System.out.println("doitList " + doitList);
		// 2 1번 리스트가 없으면 DoItBatch 에서 조회 insert 작업
		if(doitList == null || doitList.size() == 0) {
			// basedate -> 요일로 변경해야함
			String dayStr = getDayStr(seldate);
			
			// batch에서 요일 + 사용자 로 조회
			List<DoItBatch> batchList = getFromDoItBatchByUserDay(user, dayStr);
			System.out.println(batchList);
			// doit으로 저장
			
			insertDoit(batchList, userid, seldate);
			
			doitList = doItRepository.findListByUserAndBasedate(user, seldate);
		}
		
		List<DoitVo> doits = new ArrayList<>();
		for(DoIt doit : doitList) {
			DoitVo vo = new DoitVo();
			vo.setId(doit.getId());
			vo.setBasedate(doit.getBasedate());
			vo.setContent(doit.getContent());
			vo.setDone(doit.getDone());
			vo.setUserid(doit.getUser().getUserid());
			vo.setUsername(doit.getUser().getName());
			doits.add(vo);
		}
		return doits;
	}
	
	// DoItBatch 에서 조회
	// 조회 : 요일 + 사용자 으로 조회한 list
	private List<DoItBatch> getFromDoItBatchByUserDay(User userid, String defineday){
		return doItBatchRepository.findAllListByUserAndDefinedayContaining(userid, defineday);
	}
	
	// doitList insert : DoItBatch -> DoIt 으로 변경해서 저장함
	private void insertDoit(List<DoItBatch> batchs, String userid, LocalDate basedate) {
		
		System.out.println("DoItBatch List Insert Cnt : " + batchs.size());
		User user = userRepository.findById(userid).get();

		for(DoItBatch batch : batchs) {
			// DoIt 으로 변경함
			DoIt doit = new DoIt();

			doit.setBasedate(basedate);
//			doit.setIndate(basedate.toString());
			doit.setUser(user);
			doit.setContent(batch.getContent());
			doit.setDone("N");
			
			System.out.println("Doit New Save : " + doit);
			doItRepository.save(doit);
		}
	}

	// 등록 : 조회 작업시 작업됨	
	
	// 수정 : done -> true
	@Transactional
	public void updateToDone(Integer id) {
		DoIt doit = doItRepository.findById(id).get();
		doit.setDone((doit.getDone().equals("Y") ? "N" : "Y"));
		doItRepository.save(doit);
		
		// 오늘 할일을 다 마치면 쿠폰추가
		int notCnt = doItRepository.countByUserAndBasedateAndDoneIs(doit.getUser(), doit.getBasedate(), "N");
		if(notCnt == 0) {
			Coupon coupon = new Coupon();
			coupon.setBasedate(doit.getBasedate());
			coupon.setContent("오늘의 할일 완료");
			coupon.setPlaytime(10);
			coupon.setType(CouponType.ONEDAY);
			coupon.setUser(doit.getUser());
			
			couponService.insertCoupon(coupon);
		}
		
	}
	
	// 삭제 : userid, basedate 전체 삭제 + 쿠폰발급되었다면 삭제해야함
	@Transactional
	public void deleteToDayList(String userid, String basedate){
		User user = new User();
		user = userRepository.findById(userid).get();
		
		DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
		LocalDate seldate = LocalDate.parse(basedate,f);
		// doit 삭제
		doItRepository.deleteByUserAndBasedate(user, seldate);
		// 쿠폰발급 확인
		int couponCnt = couponRepository.countByUserAndBasedateAndType(user, seldate, CouponType.ONEDAY);
		// 쿠폰발급이 있다면 삭제
		if(couponCnt > 0) {
			couponRepository.deleteByUserAndBasedateAndType(user, seldate, CouponType.ONEDAY);
			// 쿠폰타임 시간빼기
			couponTimeService.updateSubTime(userid, 10);
		}
	}
		
	// String  basedate에서 요일 가져오기
	private String getDayStr(LocalDate basedate) {
		// 2. DayOfWeek 객체 구하기
        DayOfWeek dayOfWeek = basedate.getDayOfWeek();

        // 3. 숫자 요일 구하기
        int dayOfWeekNumber = dayOfWeek.getValue();
        
		String result="";
        switch (dayOfWeekNumber) {
		case 1: result= "월"; break; 
		case 2: result= "화"; break;
		case 3: result= "수"; break;
		case 4: result= "목"; break;
		case 5: result= "금"; break;
		case 6: result= "토"; break;
		case 7: result= "일"; break;
		} 
        return result;
	}	

}
