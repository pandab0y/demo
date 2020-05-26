package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.db1.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;
	
	@RequestMapping("/index")
	@ResponseBody
	public User hello(){
		return userService.getUserById(732);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<User> list(){
		
		return userService.getUserList();
	}
	
	@RequestMapping("/total")
	@ResponseBody
	public int total(){
		
		return userService.getUserToatl();
	}
	
	@RequestMapping("/weibo")
	@ResponseBody
	public String getWeibo(){
		String url = "https://weibo.com/aj/v6/mblog/info/big";
		String id = "4275035533412499";
		int page = 2;
		String cookie = "SINAGLOBAL=2233232122327.238.1527578441406; wb_cmtLike_2501332124=1; YF-Page-G0=46f5b98560a83dd9bfdd28c040a3673e; ALF=1566283972; SSOLoginState=1534747973; SCF=ArSbOiAWeOb5Qnf6nSGUJrAY44eXmck_LlRqQoGaJDpTFQ7-FFj67lh6kT4QcNkcDvOus0eMtDryjJtAAPCM388.; SUB=_2A252fhUVDeRhGeRL61MS8yzNyTiIHXVVCgHdrDV8PUNbmtBeLXDCkW9NU0D9hVKLV5cpj6NVSt9Kr0uBLacKWNOc; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WhFWLDMvUulC6cynXem3z395JpX5KzhUgL.Fozfeh20e0zpeoB2dJLoIpvjdN9Li--fi-z7iKysi--fi-2XiKLhi--fi-82iK.7; SUHB=0nl_nCqekobByA; YF-Ugrow-G0=9642b0b34b4c0d569ed7a372f8823a8e; _s_tentry=login.sina.com.cn; Apache=3806398386283.776.1534747977463; ULV=1534747977497:26:3:1:3806398386283.776.1534747977463:1534128560571; wb_view_log_2501332124=1920*10801; YF-V5-G0=82f55bdb504a04aef59e3e99f6aad4ca; UOR=news.ifeng.com,widget.weibo.com,news.ifeng.com; wvr=6";
		String result = userService.getWeibo(url, id, page, cookie);
		JSONObject json = JSONObject.parseObject(result);
		String data = json.getString("data");
		System.out.println(json);
		return data;
	}
	
	@RequestMapping("/save")
	public void save(@RequestParam String username, @RequestParam String password){
		userService.saveUser(username, password);
	}
}
