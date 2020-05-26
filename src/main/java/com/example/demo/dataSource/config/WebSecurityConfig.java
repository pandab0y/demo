package com.example.demo.dataSource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.db1.serviceImpl.CustomUserService;

/***
 * 用户权限控制
 * @author wangjingpei
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 	@Bean
	 	UserDetailsService customUserService(){ //注册UserDetailsService 的bean
	        return new CustomUserService();
	    }
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder()); //user Details Service验证
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	        		// 所有用户均可访问的资源
            		.antMatchers("/css/**", "/js/**","/images/**", "**/favicon.ico", "/user/**").permitAll()
	                .anyRequest().authenticated() //任何请求,登录后可以访问
	                .and()
	                .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/home")
	                .failureUrl("/login?error")
	                .permitAll() //登录页面用户任意访问
	                .and()
	                .logout().permitAll(); //注销行为任意访问


	    }

}
