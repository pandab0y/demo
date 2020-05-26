package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Company;
import com.example.demo.service.db2.UnionService;

@RestController
@RequestMapping("/union")
public class UnionController {
	@Autowired
	private UnionService unionService;
	
	@RequestMapping("/index")
	public Company index(){
		return unionService.getCompanyInfo(3310);
	}
	
	@RequestMapping("/list")
	public List<Company> list(){
		return unionService.getCompanyList();
	}
}
