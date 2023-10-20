package com.esjang.sthome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.User;
import com.esjang.sthome.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(User user) {
		// 쿼리를 다시 만들지 않고 유저를 가져와 비번을 다시한번 체크함
		
		User findUser = userRepository.findById(user.getUserid()).orElseGet(()->{
			return new User();
		}); 
		
		// 비번체크
		if(!findUser.getPassword().equals(user.getPassword())) {
			return new User();
		}
		
		return findUser;
	}
}
