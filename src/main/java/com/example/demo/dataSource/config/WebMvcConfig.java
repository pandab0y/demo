package com.example.demo.dataSource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 跳转到login.html
 * @author wangjingpei
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
	
}
