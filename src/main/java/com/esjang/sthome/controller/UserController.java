package com.esjang.sthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esjang.sthome.domain.User;

import com.esjang.sthome.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// id, pw로 사용자의 grade, name을 가져와야함
	@GetMapping("/login")
	public ResponseEntity<?> getUser(@RequestBody User user) {
		// 없는 유저인지 확인
		User loginUser = userService.getUser(user);
		
		if(loginUser.getUserid() == null) {
			return new ResponseEntity<>("로그인실패 사용자/비밀번호 확인", HttpStatus.BAD_REQUEST);
		}
		System.out.println(user);
		
//		session.setAttribute("user", user);
	
		return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
				
	}
	
//	http://localhost:8585/user/check	
//	{
//	    "userid": "MOM",
//	    "pw": "1234"
//	}	
	
	
}
