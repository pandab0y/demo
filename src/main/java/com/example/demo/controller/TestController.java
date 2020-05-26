package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/login/{id}")
	public String login(HttpServletRequest request,@PathVariable("id") int id, HttpSession session) {
		String userName = "";
		if(id == 1){
			userName = "张三";
		}else {
			userName = "李四";
		}
		session.setAttribute(String.valueOf(id), userName);
		request.setAttribute("id", id);
		return "/test/index";
	}
	
	@RequestMapping("/index")
	@ResponseBody
	public String index(HttpServletRequest request, HttpSession session) {
		int id = (Integer)(request.getAttribute("id"));
		String userName = (String)session.getAttribute(String.valueOf(id));
		
		return "用户：" + userName +"登录成功！" ;
	}
	
}
