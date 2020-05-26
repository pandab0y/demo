package com.example.demo.service.db1.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.userMapper.UserMapper;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
@Service
public class CustomUserService implements UserDetailsService {//自定义UserDetailsService 接口

	@Autowired
    private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) {//重写loadUserByUsername 方法获得 userdetails 类型用户
		 SysUser user = userMapper.findByUserName(username);
	        if(user == null){
	            throw new UsernameNotFoundException("用户名不存在");
	        }
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
	        for(SysRole role:user.getRoles()) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsername(),
	                user.getPassword(), authorities);

	}

}