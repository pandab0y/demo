package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Define;

@RestController
public class HelloController {

	@Value("${test.name}")
	private String name;
	
	@Autowired
	private Define define;
	
	@RequestMapping("/hello")
	public String hello(){
		
		return define.getName();
	}
}
