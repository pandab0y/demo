package com.example.demo.controller;


import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Msg;


@Controller
public class HomeController {

	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@RequestMapping("/home")
    public String index(Model model, Principal principal){
		System.out.println(principal.getName());
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
	

}
