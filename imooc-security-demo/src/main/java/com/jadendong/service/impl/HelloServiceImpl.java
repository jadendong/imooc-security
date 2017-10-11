package com.jadendong.service.impl;

import org.springframework.stereotype.Service;

import com.jadendong.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		
		System.out.println("hello greeting");
		return "hello "+name;
	}

}
