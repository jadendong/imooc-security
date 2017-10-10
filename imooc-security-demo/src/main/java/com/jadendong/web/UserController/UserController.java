package com.jadendong.web.UserController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jadendong.dto.User;
import com.jadendong.dto.UserQueryCondition;


@RestController
public class UserController {
	
	@GetMapping("/user")
	public List<User> query(UserQueryCondition condition) {
		
		List<User> users=new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		return users;
	}
}
