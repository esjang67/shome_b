package com.esjang.sthome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esjang.sthome.domain.User;
import com.esjang.sthome.mapper.UserMapper;
import com.esjang.sthome.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(String userid, String password) {
		// 쿼리를 다시 만들지 않고 유저를 가져와 비번을 다시한번 체크함
		
		User findUser = userMapper.m_findById(userid).orElseGet(()->{
			return new User();
		}); 
		
		// 비번체크
		if(!findUser.getPassword().equals(password)) {
			return new User();
		}
		
		return findUser;
	}
}
